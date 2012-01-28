package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JumpInstructionParsingStrategyTest
{
    private static final JumpInstructionParsingStrategy PARSER = new JumpInstructionParsingStrategy();

    private static final String TARGET = "target";

    private static final String INSTRUCTION = "j " + TARGET;

    @Test
    public void testParsingInstruction()
    {
        JumpInstruction instruction = PARSER.parseInstruction(
            JumpInstruction.class, INSTRUCTION);

        assertEquals(JumpInstruction.getOperationKey(),
            instruction.getOperation());
        assertEquals(JumpInstructionTest.TARGET, instruction.getAddress());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailedParseThrowsException()
    {
        PARSER.parseInstruction(JumpInstruction.class, "<test>");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParsingInstructionWithIncorrectOperationThrowsException()
    {
        PARSER.parseInstruction(JumpAndLinkInstruction.class, INSTRUCTION);
    }
}
