package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OneRegisterDestinationParsingStrategyTest
{
    private static final OneRegisterDestinationParsingStrategy PARSER = new OneRegisterDestinationParsingStrategy();

    private static final String INSTRUCTION = "mfhi $s0";

    @Test
    public void testParsingInstruction()
    {
        MoveFromHighInstruction instruction = PARSER.parseInstruction(
            MoveFromHighInstruction.class, INSTRUCTION);

        assertEquals(MoveFromHighInstruction.getOperationKey(),
            instruction.getOperation());
        assertEquals(MipsRegister.S0, instruction.getDestination());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailedParseThrowsException()
    {
        PARSER.parseInstruction(MoveFromHighInstruction.class, "<test>");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParsingInstructionWithIncorrectOperationThrowsException()
    {
        PARSER.parseInstruction(MoveFromLowInstruction.class, INSTRUCTION);
    }
}
