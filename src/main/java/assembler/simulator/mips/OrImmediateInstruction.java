package assembler.simulator.mips;

import assembler.simulator.Register;

class OrImmediateInstruction extends OneRegisterArithmeticInstruction
{
    private static final String OPERATION = "ori";

    public OrImmediateInstruction(String instruction,
        Register destination, int immediate, Register operandOne)
    {
        super(OPERATION, instruction, destination, immediate, operandOne,
            OverflowStrategy.IGNORE);
    }

    @Override
    protected long calculateResult(long firstOperand, long secondOperand)
    {
        return firstOperand |= secondOperand;
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }
}
