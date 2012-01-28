package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OneRegisterArgumentWithImmediateParsingStrategyTest
{
    private static final OneRegisterArgumentWithImmediateParsingStrategy<Integer> PARSER = OneRegisterArgumentWithImmediateParsingStrategy.INTEGER;

    private static final String INSTRUCTION = "andi $t0, $s0, 4";

    @Test
    public void testParsingInstruction()
    {
        AndImmediateInstruction instruction = PARSER.parseInstruction(
            AndImmediateInstruction.class, INSTRUCTION);

        assertEquals(AndImmediateInstruction.getOperationKey(),
            instruction.getOperation());
        assertEquals(MipsRegister.T0, instruction.getDestination());
        assertEquals(MipsRegister.S0, instruction.getOperandOne());
        assertEquals(new Integer(4), instruction.getOperandTwo());
    }

    @Test
    public void testParsingInstructionWithoutSpaces()
    {
        AndImmediateInstruction instruction = PARSER.parseInstruction(
            AndImmediateInstruction.class, "andi $t0,$s0,4");

        assertEquals(AndImmediateInstruction.getOperationKey(),
            instruction.getOperation());
        assertEquals(MipsRegister.T0, instruction.getDestination());
        assertEquals(MipsRegister.S0, instruction.getOperandOne());
        assertEquals(new Integer(4), instruction.getOperandTwo());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailedParseThrowsException()
    {
        PARSER.parseInstruction(AndImmediateInstruction.class, "<test>");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParsingInstructionWithIncorrectOperationThrowsException()
    {
        PARSER.parseInstruction(OrImmediateInstruction.class, INSTRUCTION);
    }
}
