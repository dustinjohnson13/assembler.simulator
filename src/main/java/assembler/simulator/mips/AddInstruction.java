package assembler.simulator.mips;

import assembler.simulator.Register;

class AddInstruction extends BaseAddInstruction
{
    private static final String OPERATION = "add";

    public AddInstruction(String instruction, Register operandOne,
        Register operandTwo, Register destination)
    {
        super(OPERATION, instruction, operandOne, operandTwo,
            destination, new TrapOverflowStrategy());
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }
}
