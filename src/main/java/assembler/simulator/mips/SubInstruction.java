package assembler.simulator.mips;

import assembler.simulator.Register;

class SubInstruction extends BaseSubInstruction
{
    private static final String OPERATION = "sub";

    public SubInstruction(String instruction, Register operandOne,
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
