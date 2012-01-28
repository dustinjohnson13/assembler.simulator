package assembler.simulator.mips;

import assembler.simulator.Assembler;
import assembler.simulator.Register;

public class LoadByteUnsignedInstruction extends DataTransferInstruction {
	private static final int BITS_IN_THREE_QUARTERS_WORD = 24;
	private static final String OPERATION = "lbu";

	public LoadByteUnsignedInstruction(String instruction,
			Register dataRegister, int addressOffset, Register addressRegister) {
		super(OPERATION, instruction, dataRegister, addressOffset,
				addressRegister);
	}

	@Override
	public void execute(Assembler assembler) {
		int memoryAddress = assembler.getRegisterValue(this
				.getAddressRegister());
		memoryAddress += this.getAddressOffset();

		byte aByte = assembler.getByteFromMemory(memoryAddress);
		int unsignedValue = aByte << BITS_IN_THREE_QUARTERS_WORD >>> BITS_IN_THREE_QUARTERS_WORD;

		assembler.setRegisterValue(this.getDataRegister(), unsignedValue);
	}

	public static String getOperationKey() {
		return OPERATION;
	}
}