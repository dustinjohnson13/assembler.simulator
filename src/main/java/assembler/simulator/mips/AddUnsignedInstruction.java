package assembler.simulator.mips;

import assembler.simulator.Register;

class AddUnsignedInstruction extends BaseAddInstruction
{
    private static final String OPERATION = "addu";

    public AddUnsignedInstruction(String instruction,
        Register operandOne, Register operandTwo, Register destination)
    {
        super(OPERATION, instruction, operandOne, operandTwo,
            destination, OverflowStrategy.IGNORE);
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }

}
