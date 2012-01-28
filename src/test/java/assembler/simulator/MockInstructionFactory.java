package assembler.simulator;

import org.junit.Ignore;

@Ignore
public class MockInstructionFactory implements InstructionFactory
{
    public final static MockInstruction INSTRUCTION = new MockInstruction();

    private final Instruction[] instructionsToReturn;

    private int indexToReturn;

    public MockInstructionFactory()
    {
        this(INSTRUCTION);
    }

    public MockInstructionFactory(Instruction... instructions)
    {
        this.instructionsToReturn = instructions;
    }

    @Override
    public Instruction parseInstruction(String instructionLine)
    {
        return instructionsToReturn[indexToReturn++];
    }
}
