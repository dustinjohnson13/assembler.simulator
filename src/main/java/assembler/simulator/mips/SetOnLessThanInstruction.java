package assembler.simulator.mips;

import assembler.simulator.Register;

class SetOnLessThanInstruction extends TwoRegisterArithmeticInstruction
{
    private static final String OPERATION = "slt";

    public SetOnLessThanInstruction(String instruction,
        Register operandOne, Register operandTwo, Register destination)
    {
        super(OPERATION, instruction, operandOne, operandTwo,
            destination, OverflowStrategy.IGNORE);
    }

    @Override
    protected long calculateResult(long firstOperand, long secondOperand)
    {
        return firstOperand < secondOperand ? 1 : 0;
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }
}