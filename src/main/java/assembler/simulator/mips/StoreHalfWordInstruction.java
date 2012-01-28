package assembler.simulator.mips;

import assembler.simulator.Assembler;
import assembler.simulator.Register;

public class StoreHalfWordInstruction extends DataTransferInstruction
{
    private static final String OPERATION = "sh";

    public StoreHalfWordInstruction(String instruction,
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
        assembler.setHalfWordInMemory(memoryAddress, (short) registerValue);
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }

}