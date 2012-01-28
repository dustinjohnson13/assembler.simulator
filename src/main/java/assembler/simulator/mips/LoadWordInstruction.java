package assembler.simulator.mips;

import assembler.simulator.Assembler;
import assembler.simulator.Register;

class LoadWordInstruction extends DataTransferInstruction
{
    private static final String OPERATION = "lw";

    public LoadWordInstruction(String instruction, Register dataRegister,
        int addressOffset, Register addressRegister)
    {
        super(OPERATION, instruction, dataRegister, addressOffset,
            addressRegister);
    }

    @Override
    public void execute(Assembler assembler)
    {
        int memoryAddress = assembler.getRegisterValue(this.getAddressRegister());
        memoryAddress += this.getAddressOffset();

        int word = assembler.getWordFromMemory(memoryAddress);

        assembler.setRegisterValue(this.getDataRegister(), word);
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }
}
