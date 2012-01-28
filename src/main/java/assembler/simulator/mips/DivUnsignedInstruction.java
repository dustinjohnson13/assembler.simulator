package assembler.simulator.mips;

import assembler.simulator.Register;

class DivUnsignedInstruction extends BaseDivInstruction
{
    private static final long UNSIGNED_INT_BITMASK = 0xFFFFFFFFL;

    private static final String OPERATION = "divu";

    public DivUnsignedInstruction(String instruction,
        Register operandOne, Register operandTwo, Register destination)
    {
        super(OPERATION, instruction, operandOne, operandTwo, destination);
    }

    @Override
    protected long[] calculateResults(long firstOperand, long secondOperand)
    {
        firstOperand &= UNSIGNED_INT_BITMASK;
        secondOperand &= UNSIGNED_INT_BITMASK;

        long[] results = new long[2];
        results[0] = firstOperand / secondOperand;
        results[1] = firstOperand % secondOperand;

        return results;
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }

}
