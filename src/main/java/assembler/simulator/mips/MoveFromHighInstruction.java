package assembler.simulator.mips;

import assembler.simulator.Assembler;
import assembler.simulator.Register;

public class MoveFromHighInstruction extends
    OneRegisterDestinationInstruction
{
    private static final String OPERATION = "mfhi";

    public MoveFromHighInstruction(String instruction,
        Register destination)
    {
        super(OPERATION, instruction, destination);
    }

    @Override
    public void execute(Assembler assembler)
    {
        assembler.setRegisterValue(getDestination(),
            assembler.getRegisterValue(MipsRegister.HI));
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }

}
