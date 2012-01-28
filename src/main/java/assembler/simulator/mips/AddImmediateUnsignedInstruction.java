package assembler.simulator.mips;

import assembler.simulator.Register;

class AddImmediateUnsignedInstruction extends BaseAddImmediateInstruction
{
    private static final String OPERATION = "addiu";

    public AddImmediateUnsignedInstruction(String instruction,
        Register destination, int immediate, Register operandOne)
    {
        super(OPERATION, instruction, destination, immediate, operandOne,
            OverflowStrategy.IGNORE);
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }
}
