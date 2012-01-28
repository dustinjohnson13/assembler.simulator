package assembler.simulator.mips;

import assembler.simulator.Assembler;
import assembler.simulator.Register;

public class LoadHalfWordUnsignedInstruction extends DataTransferInstruction {
	private static final int BITS_IN_HALF_WORD = 16;
	private static final String OPERATION = "lhu";

	public LoadHalfWordUnsignedInstruction(String instruction,
			Register dataRegister, int addressOffset, Register addressRegister) {
		super(OPERATION, instruction, dataRegister, addressOffset,
				addressRegister);
	}

	@Override
	public void execute(Assembler assembler) {
		int memoryAddress = assembler.getRegisterValue(this
				.getAddressRegister());
		memoryAddress += this.getAddressOffset();

		short halfWord = assembler.getHalfWordFromMemory(memoryAddress);
		int unsignedValue = halfWord << BITS_IN_HALF_WORD >>> BITS_IN_HALF_WORD;

		assembler.setRegisterValue(this.getDataRegister(), unsignedValue);
	}

	public static String getOperationKey() {
		return OPERATION;
	}
}
