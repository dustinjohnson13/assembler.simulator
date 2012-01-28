package assembler.simulator.mips;

import assembler.simulator.Register;

abstract class ThreeRegisterInstruction extends
    TwoRegisterArgumentInstruction
{
    private final Register destination;

    ThreeRegisterInstruction(String opcode, String instruction,
        Register operandOne, Register operandTwo, Register destination)
    {
        super(opcode, instruction, operandOne, operandTwo);

        this.destination = destination;
    }

    Register getDestination()
    {
        return destination;
    }
}
