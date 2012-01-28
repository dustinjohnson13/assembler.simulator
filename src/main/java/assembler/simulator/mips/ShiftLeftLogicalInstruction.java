package assembler.simulator.mips;

import assembler.simulator.Register;

class ShiftLeftLogicalInstruction extends OneRegisterArithmeticInstruction
{
    private static final String OPERATION = "sll";

    public ShiftLeftLogicalInstruction(String instruction, Register destination,
        int immediate, Register operandOne)
    {
        super(OPERATION, instruction, destination, immediate, operandOne,
            OverflowStrategy.IGNORE);
    }

    @Override
    protected long calculateResult(long firstOperand, long secondOperand)
    {
        return firstOperand << secondOperand;
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }

}
