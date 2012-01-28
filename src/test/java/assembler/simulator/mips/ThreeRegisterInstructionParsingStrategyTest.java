package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ThreeRegisterInstructionParsingStrategyTest
{
    private static final ThreeRegisterInstructionParsingStrategy PARSER = new ThreeRegisterInstructionParsingStrategy();

    private static final String INSTRUCTION = "and $t0, $s0, $s1";

    @Test
    public void testParsingInstruction()
    {
        AndInstruction instruction = PARSER.parseInstruction(
            AndInstruction.class, INSTRUCTION);

        assertEquals(AndInstruction.getOperationKey(),
            instruction.getOperation());
        assertEquals(MipsRegister.T0, instruction.getDestination());
        assertEquals(MipsRegister.S0, instruction.getOperandOne());
        assertEquals(MipsRegister.S1, instruction.getOperandTwo());
    }

    @Test
    public void testParsingInstructionWithoutSpaces()
    {
        AndInstruction instruction = PARSER.parseInstruction(
            AndInstruction.class, "and $t0,$s0,$s1");

        assertEquals(AndInstruction.getOperationKey(),
            instruction.getOperation());
        assertEquals(MipsRegister.T0, instruction.getDestination());
        assertEquals(MipsRegister.S0, instruction.getOperandOne());
        assertEquals(MipsRegister.S1, instruction.getOperandTwo());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailedParseThrowsException()
    {
        PARSER.parseInstruction(AndInstruction.class, "<test>");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParsingInstructionWithIncorrectOperationThrowsException()
    {
        PARSER.parseInstruction(OrInstruction.class, INSTRUCTION);
    }
}
