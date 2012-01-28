package assembler.simulator.mips;

import assembler.simulator.Assembler;
import assembler.simulator.Register;

public class MoveFromLowInstruction extends OneRegisterDestinationInstruction
{
    private static final String OPERATION = "mflo";

    public MoveFromLowInstruction(String instruction, Register destination)
    {
        super(OPERATION, instruction, destination);
    }

    @Override
    public void execute(Assembler assembler)
    {
        assembler.setRegisterValue(getDestination(),
            assembler.getRegisterValue(MipsRegister.LO));
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }

}
