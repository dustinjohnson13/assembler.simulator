package assembler.simulator.mips;

import assembler.simulator.Assembler;
import assembler.simulator.Register;

public class LoadHalfWordInstruction extends DataTransferInstruction
{
    private static final String OPERATION = "lh";

    public LoadHalfWordInstruction(String instruction,
        Register dataRegister, int addressOffset, Register addressRegister)
    {
        super(OPERATION, instruction, dataRegister, addressOffset,
            addressRegister);
    }

    @Override
    public void execute(Assembler assembler)
    {
        int memoryAddress = assembler.getRegisterValue(this.getAddressRegister());
        memoryAddress += this.getAddressOffset();

        short halfWord = assembler.getHalfWordFromMemory(memoryAddress);

        assembler.setRegisterValue(this.getDataRegister(), halfWord);
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }
}
