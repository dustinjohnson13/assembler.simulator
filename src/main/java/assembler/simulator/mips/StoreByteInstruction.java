package assembler.simulator.mips;

import assembler.simulator.Assembler;
import assembler.simulator.Register;

public class StoreByteInstruction extends DataTransferInstruction
{
    private static final String OPERATION = "sb";

    public StoreByteInstruction(String instruction,
        Register dataRegister, int addressOffset, Register addressRegister)
    {
        super(OPERATION, instruction, dataRegister, addressOffset,
            addressRegister);
    }

    @Override
    public void execute(Assembler assembler)
    {
        int memoryAddress = assembler.getRegisterValue(getAddressRegister());
        memoryAddress += this.getAddressOffset();

        int registerValue = assembler.getRegisterValue(this.getDataRegister());

        // Truncate the int
        assembler.setHalfWordInMemory(memoryAddress, (byte) registerValue);
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }

}