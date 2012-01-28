package assembler.simulator.mips;

import assembler.simulator.Register;

class DivInstruction extends BaseDivInstruction
{
    private static final String OPERATION = "div";

    public DivInstruction(String instruction, Register operandOne,
        Register operandTwo, Register destination)
    {
        super(OPERATION, instruction, operandOne, operandTwo, destination);
    }

    @Override
    protected long[] calculateResults(long firstOperand, long secondOperand)
    {
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
