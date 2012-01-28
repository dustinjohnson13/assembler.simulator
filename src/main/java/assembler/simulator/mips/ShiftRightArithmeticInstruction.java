package assembler.simulator.mips;

import assembler.simulator.Register;

public class ShiftRightArithmeticInstruction extends
    OneRegisterArithmeticInstruction
{
    private static final String OPERATION = "sra";

    public ShiftRightArithmeticInstruction(String instruction,
        Register destination, int immediate, Register operandOne)
    {
        super(OPERATION, instruction, destination, immediate, operandOne,
            OverflowStrategy.IGNORE);
    }

    @Override
    protected long calculateResult(long firstOperand, long secondOperand)
    {
        int first = (int) firstOperand;
        int numberToShift = (int) secondOperand;

        return first >> numberToShift;
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }
}
