package assembler.simulator.mips;

import assembler.simulator.Assembler;
import assembler.simulator.Register;

public class MoveFromControlRegisterInstruction extends
    TwoRegisterArithmeticInstruction
{
    private static final String OPERATION = "mfc";

    public MoveFromControlRegisterInstruction(String instruction,
        Register operandOne, Register operandTwo, Register destination)
    {
        super(OPERATION, instruction, operandOne, operandTwo, null,
            OverflowStrategy.IGNORE);
    }

    @Override
    public void execute(Assembler assembler)
    {
        assembler.setRegisterValue(getOperandOne(),
            assembler.getRegisterValue(getOperandTwo()));
    }

    @Override
    protected long calculateResult(long firstOperand, long secondOperand)
    {
        throw new UnsupportedOperationException(
            "Should not be used with this instruction!");
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }
}
