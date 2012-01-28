package assembler.simulator.mips;

import assembler.simulator.Assembler;
import assembler.simulator.Register;

public class LoadByteInstruction extends DataTransferInstruction
{
    private static final String OPERATION = "lb";

    public LoadByteInstruction(String instruction, Register dataRegister,
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

        byte aByte = assembler.getByteFromMemory(memoryAddress);

        assembler.setRegisterValue(this.getDataRegister(), aByte);
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }
}
