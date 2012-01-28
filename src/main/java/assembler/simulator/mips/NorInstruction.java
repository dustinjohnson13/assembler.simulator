package assembler.simulator.mips;

import assembler.simulator.Register;

class NorInstruction extends TwoRegisterArithmeticInstruction
{
    private static final String OPERATION = "nor";

    public NorInstruction(String instruction, Register operandOne,
        Register operandTwo, Register destination)
    {
        super(OPERATION, instruction, operandOne, operandTwo,
            destination, OverflowStrategy.IGNORE);
    }

    @Override
    protected long calculateResult(long firstOperand, long secondOperand)
    {
        return ~(firstOperand |= secondOperand);
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }
}