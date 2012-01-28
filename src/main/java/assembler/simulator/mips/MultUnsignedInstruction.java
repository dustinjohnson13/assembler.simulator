package assembler.simulator.mips;

import assembler.simulator.Register;

class MultUnsignedInstruction extends BaseMultInstruction
{
    private static final long UNSIGNED_INT_BITMASK = 0xFFFFFFFFL;

    private static final String OPERATION = "multu";

    public MultUnsignedInstruction(String instruction, Register operandOne,
        Register operandTwo, Register destination)
    {
        super(OPERATION, instruction, operandOne, operandTwo, destination);
    }

    @Override
    protected long calculateResult(long firstOperand, long secondOperand)
    {
        firstOperand &= UNSIGNED_INT_BITMASK;
        secondOperand &= UNSIGNED_INT_BITMASK;

        return firstOperand * secondOperand;
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }
}
