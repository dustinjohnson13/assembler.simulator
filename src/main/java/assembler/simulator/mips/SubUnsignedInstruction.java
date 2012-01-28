package assembler.simulator.mips;

import assembler.simulator.Register;

class SubUnsignedInstruction extends BaseSubInstruction
{
    private static final String OPERATION = "subu";

    public SubUnsignedInstruction(String instruction,
        Register operandOne, Register operandTwo, Register destination)
    {
        super(OPERATION, instruction, operandOne, operandTwo,
            destination, OverflowStrategy.IGNORE);
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }

    @Override
    protected long calculateResult(long firstOperand, long secondOperand)
    {
        return firstOperand - secondOperand;
    }
}
