package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OneRegisterDestinationWithImmediateParsingStrategyTest
{
    private static final OneRegisterDestinationWithImmediateParsingStrategy PARSER = new OneRegisterDestinationWithImmediateParsingStrategy();

    private static final String INSTRUCTION = "lui $s0, 7";

    @Test
    public void testParsingInstruction()
    {
        LoadUpperImmediateInstruction instruction = PARSER.parseInstruction(
            LoadUpperImmediateInstruction.class, INSTRUCTION);

        assertEquals(LoadUpperImmediateInstruction.getOperationKey(),
            instruction.getOperation());
        assertEquals(MipsRegister.S0, instruction.getDestination());
        assertEquals(7, instruction.getImmediate());
    }

    @Test
    public void testParsingInstructionWithoutSpaces()
    {
        LoadUpperImmediateInstruction instruction = PARSER.parseInstruction(
            LoadUpperImmediateInstruction.class, "lui $s0,7");

        assertEquals(LoadUpperImmediateInstruction.getOperationKey(),
            instruction.getOperation());
        assertEquals(MipsRegister.S0, instruction.getDestination());
        assertEquals(7, instruction.getImmediate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailedParseThrowsException()
    {
        PARSER.parseInstruction(LoadUpperImmediateInstruction.class, "<test>");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParsingInstructionWithIncorrectOperationThrowsException()
    {
        PARSER.parseInstruction(OrImmediateInstruction.class, INSTRUCTION);
    }
}
