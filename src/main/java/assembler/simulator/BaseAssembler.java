package assembler.simulator;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseAssembler implements Assembler {
	private static final int BYTES_IN_INT = 4;
	// 64 MB
	private static final int MEMORY_IN_BYTES = 67108864;
	private static final int MAXIMUM_REGISTERS_ALLOWED = 64;

	private static final RegisterViewMode DEFAULT_REGISTER_VIEW_MODE = RegisterViewModeImpl.SIGNED_INTEGER;

	private static final Collection<RegisterViewMode> REGISTER_VIEW_MODES;

	static {
		REGISTER_VIEW_MODES = new ArrayList<RegisterViewMode>();

		for (RegisterViewModeImpl impl : RegisterViewModeImpl.values()) {
			REGISTER_VIEW_MODES.add(impl);
		}
	}

	final int[] registerValues;

	final Register[] registers;

	final byte[] memory;

	private final InstructionFactory instructionFactory;

	private final Collection<Observer> observers = new ArrayList<Observer>();

	// @GuardedBy instructionLock
	List<Instruction> instructionList = new ArrayList<Instruction>();

	// @GuardedBy instructionLock
	Map<String, Integer> labelToInstructionIndex = new HashMap<String, Integer>();

	// @GuardedBy instructionLock
	int programCounter = 0;

	private final Object instructionLock = new Object();

	private RegisterViewMode registerViewMode = DEFAULT_REGISTER_VIEW_MODE;

	protected BaseAssembler(Register[] registers,
			InstructionFactory instructionFactory) {
		this(registers, instructionFactory, MEMORY_IN_BYTES);
	}

	/**
	 * Used from MockBaseAssembler to create an Assembler with really low
	 * memory.
	 * 
	 * @param registers
	 *            registers
	 * @param instructionFactory
	 *            instructionFactory
	 * @param memorySizeInBytes
	 *            memorySizeInBytes
	 */
	BaseAssembler(Register[] registers, InstructionFactory instructionFactory,
			int memorySizeInBytes) {

		if (registers.length > MAXIMUM_REGISTERS_ALLOWED) {
			throw new IllegalArgumentException("Unable to support more than "
					+ MAXIMUM_REGISTERS_ALLOWED + "at this time!");
		} else {
			this.registers = new Register[registers.length];
			System.arraycopy(registers, 0, this.registers, 0, registers.length);
		}

		registerValues = new int[registers.length];
		this.instructionFactory = instructionFactory;
		this.memory = new byte[memorySizeInBytes];
	}

	@Override
	public int getNumberOfRegisters() {
		return registerValues.length;
	}

	@Override
	public Collection<Register> getRegisters() {
		return Collections.unmodifiableList(Arrays.asList(registers));
	}

	@Override
	public void setRegisterValue(Register register, int value) {
		registerValues[register.getNumericValue()] = value;
	}

	@Override
	public void setRegisterValue(Register register, long value, Bytes bytes) {
		byte[] allBytes = ByteArrayConverter.convertToByteArray(value);
		byte[] intBytes = new byte[BYTES_IN_INT];

		final int startIndex = (Bytes.HIGH == bytes) ? 0 : BYTES_IN_INT;

		System.arraycopy(allBytes, startIndex, intBytes, 0, BYTES_IN_INT);
		setRegisterValue(register,
				ByteArrayConverter.intFromByteArray(intBytes));
	}

	@Override
	public int getRegisterValue(Register register) {
		return registerValues[register.getNumericValue()];
	}

	protected Instruction parseInstruction(String instructionLine) {
		return instructionFactory.parseInstruction(instructionLine);
	}

	protected void executeInstruction(Instruction instruction) {
		instruction.execute(this);

		updateObservers();
	}

	@Override
	public void processLine(String line) {
		String cleanedLine = getInstructionStringWithoutComments(line);

		if (cleanedLine != null) {
			Instruction instruction = instructionFactory
					.parseInstruction(cleanedLine);

			synchronized (instructionLock) {
				instructionList.add(instruction);

				String instructionLabel = instruction.getLabel();
				if (instructionLabel != null) {
					labelToInstructionIndex.put(instructionLabel,
							instructionList.size() - 1);
				}
			}

			updateObservers();
		}
	}

	@Override
	public void runInstructions() {
		boolean keepRunning = false;
		do {
			keepRunning = runInstruction();
		} while (keepRunning);
	}

	@Override
	public int getProgramCounter() {
		synchronized (instructionLock) {
			return programCounter;
		}
	}

	@Override
	public void setProgramCounter(int index) {
		synchronized (instructionLock) {
			programCounter = index;
		}
	}

	@Override
	public Collection<Instruction> getInstructions() {
		synchronized (instructionLock) {
			return Collections.unmodifiableList(instructionList);
		}
	}

	@Override
	public void jump(String label) {
		synchronized (instructionLock) {
			// One before the actual index because it will be incremented
			this.programCounter = labelToInstructionIndex.get(label);
		}
	}

	@Override
	public int getWordFromMemory(int memoryAddress) {
		byte[] bytesFromMemory = getBytesFromMemory(memoryAddress, Integer.SIZE
				/ Byte.SIZE);
		return ByteArrayConverter.intFromByteArray(bytesFromMemory);
	}

	@Override
	public void setWordInMemory(int memoryAddress, int memoryValue) {
		byte[] bytesForMemory = ByteArrayConverter
				.convertToByteArray(memoryValue);
		placeBytesInMemory(memoryAddress, bytesForMemory);
	}

	@Override
	public long getDoubleWordFromMemory(int memoryAddress) {
		byte[] bytesFromMemory = getBytesFromMemory(memoryAddress, Long.SIZE
				/ Byte.SIZE);
		return ByteArrayConverter.longFromByteArray(bytesFromMemory);
	}

	@Override
	public void setDoubleWordInMemory(int memoryAddress, long value) {
		byte[] bytesForMemory = ByteArrayConverter.convertToByteArray(value);
		placeBytesInMemory(memoryAddress, bytesForMemory);
	}

	@Override
	public short getHalfWordFromMemory(int memoryAddress) {
		byte[] bytesFromMemory = getBytesFromMemory(memoryAddress, Short.SIZE
				/ Byte.SIZE);
		return ByteArrayConverter.shortFromByteArray(bytesFromMemory);
	}

	@Override
	public void setHalfWordInMemory(int memoryAddress, short value) {
		byte[] bytesForMemory = ByteArrayConverter.convertToByteArray(value);
		placeBytesInMemory(memoryAddress, bytesForMemory);
	}

	@Override
	public byte getByteFromMemory(int memoryAddress) {
		byte[] bytesFromMemory = getBytesFromMemory(memoryAddress, 1);
		return bytesFromMemory[0];
	}

	@Override
	public void setByteInMemory(int memoryAddress, byte value) {
		placeBytesInMemory(memoryAddress, new byte[] { value });
	}

	private byte[] getBytesFromMemory(int memoryAddress, int numberOfBytes) {
		byte[] bytes = new byte[numberOfBytes];
		System.arraycopy(memory, memoryAddress, bytes, 0, numberOfBytes);
		return bytes;
	}

	private void placeBytesInMemory(int memoryAddress, byte[] bytesForMemory) {
		// Place the data in memory
		for (int i = 0, j = memoryAddress; i < bytesForMemory.length; i++, j++) {
			memory[j] = bytesForMemory[i];
		}
	}

	@Override
	public RegisterViewMode getRegisterViewMode() {
		return registerViewMode;
	}

	@Override
	public Collection<RegisterViewMode> getRegisterViewModes() {
		return REGISTER_VIEW_MODES;
	}

	@Override
	public void setRegisterViewMode(RegisterViewMode registerViewMode) {
		this.registerViewMode = registerViewMode;

		updateObservers();

	}

	@Override
	public void resetRegisters() {
		for (Register register : registers) {
			setRegisterValue(register, Assembler.INITIAL_REGISTER_VALUE);
		}

		updateObservers();
	}

	@Override
	public void clearInstructions() {
		synchronized (instructionLock) {
			instructionList.clear();
			programCounter = 0;
		}

		updateObservers();
	}

	@Override
	public void loadInstructions(File file) {
		synchronized (instructionLock) {
			clearInstructions();

			for (String line : getFileLines(file)) {
				processLine(line);
			}
		}

		updateObservers();
	}

	@Override
	public void registerObserver(Observer observer) {
		if (!observers.contains(observer)) {
			observers.add(observer);
			observer.update();
		}
	}

	protected void updateObservers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}

	protected abstract String getInstructionStringWithoutComments(String line);

	@Override
	public boolean runInstruction() {
		synchronized (instructionLock) {
			if (programCounter < instructionList.size()) {
				// The increment must happen here, because the instruction
				// could perform a jump
				Instruction instruction = instructionList.get(programCounter++);
				executeInstruction(instruction);
				return true;
			}
			return false;
		}
	}

	List<String> getFileLines(File file) {
		List<String> lines = new ArrayList<String>();

		BufferedReader br = null;
		try {
			FileReader fileReader = new FileReader(file);
			br = new BufferedReader(fileReader);
			String line = null;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			close(br);
		}
		return lines;
	}

	void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				// Closing anyways
			}
		}
	}
}
