package assembler.simulator.mips;

import assembler.simulator.Register;

class AddImmediateInstruction extends BaseAddImmediateInstruction
{
    private static final String OPERATION = "addi";

    public AddImmediateInstruction(String instruction, Register destination,
        int immediate, Register operandOne)
    {
        super(OPERATION, instruction, destination, immediate, operandOne,
            new TrapOverflowStrategy());
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }
}
