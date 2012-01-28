package assembler.simulator.mips;

import assembler.simulator.Register;

class XorInstruction extends TwoRegisterArithmeticInstruction
{
    private static final String OPERATION = "xor";

    public XorInstruction(String instruction, Register operandOne,
        Register operandTwo, Register destination)
    {
        super(OPERATION, instruction, operandOne, operandTwo,
            destination, OverflowStrategy.IGNORE);
    }

    @Override
    protected long calculateResult(long firstOperand, long secondOperand)
    {
        return firstOperand ^= secondOperand;
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }
}
