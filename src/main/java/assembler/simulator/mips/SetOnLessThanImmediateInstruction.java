package assembler.simulator.mips;

import assembler.simulator.Assembler;
import assembler.simulator.Register;

class SetOnLessThanImmediateInstruction extends
    OneRegisterArgumentWithImmediateInstruction<Integer>
{
    private static final String OPERATION = "slti";

    public SetOnLessThanImmediateInstruction(String instruction,
        Register destination, int immediate, Register operandOne)
    {
        super(OPERATION, instruction, destination, immediate, operandOne);
    }

    @Override
    public void execute(Assembler assembler)
    {
        int value = assembler.getRegisterValue(this.getOperandOne()) < this.getOperandTwo() ? 1
            : 0;
        assembler.setRegisterValue(this.getDestination(), value);
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }
}
