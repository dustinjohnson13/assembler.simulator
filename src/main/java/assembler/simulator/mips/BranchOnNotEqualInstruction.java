package assembler.simulator.mips;

import assembler.simulator.Assembler;
import assembler.simulator.Register;

class BranchOnNotEqualInstruction extends
    TwoRegisterArgumentWithImmediateInstruction
{
    private static final String OPERATION = "bne";

    public BranchOnNotEqualInstruction(String instruction,
        Register operandTwo, String immediate, Register operandOne)
    {
        super(OPERATION, instruction, operandTwo, immediate, operandOne);
    }

    @Override
    public void execute(Assembler assembler)
    {
        int operandOne = assembler.getRegisterValue(getOperandOne());
        int operandTwo = assembler.getRegisterValue(getOperandTwo());

        if (operandOne != operandTwo)
        {
            assembler.jump(getImmediate());
        }
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }

}
