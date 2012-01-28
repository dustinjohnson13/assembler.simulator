package assembler.simulator.mips;

import assembler.simulator.Register;

abstract class TwoRegisterArgumentWithImmediateInstruction extends
    TwoRegisterArgumentInstruction
{
    private final String immediate;

    TwoRegisterArgumentWithImmediateInstruction(String operation,
        String instruction, Register operandTwo, String immediate,
        Register operandOne)
    {
        super(operation, instruction, operandOne, operandTwo);
        this.immediate = immediate;
    }

    String getImmediate()
    {
        return immediate;
    }
}
