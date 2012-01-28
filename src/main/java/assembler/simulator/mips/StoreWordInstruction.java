package assembler.simulator.mips;

import assembler.simulator.Assembler;
import assembler.simulator.Register;

class StoreWordInstruction extends DataTransferInstruction
{
    private static final String OPERATION = "sw";

    public StoreWordInstruction(String instruction,
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

        assembler.setWordInMemory(memoryAddress, registerValue);
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }

}
