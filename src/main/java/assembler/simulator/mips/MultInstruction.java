package assembler.simulator.mips;

import assembler.simulator.Register;

class MultInstruction extends BaseMultInstruction
{
    private static final String OPERATION = "mult";

    public MultInstruction(String instruction, Register operandOne,
        Register operandTwo, Register destination)
    {
        super(OPERATION, instruction, operandOne, operandTwo, destination);
    }

    @Override
    protected long calculateResult(long firstOperand, long secondOperand)
    {
        return firstOperand * secondOperand;
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }
}
