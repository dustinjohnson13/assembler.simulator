package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TwoRegisterArgumentWithImmediateInstructionParsingStrategyTest
{
    private static final TwoRegisterArgumentWithImmediateInstructionParsingStrategy PARSER = new TwoRegisterArgumentWithImmediateInstructionParsingStrategy();

    private static final String INSTRUCTION = "bne $t0, $s0, loop";

    @Test
    public void testParsingInstruction()
    {
        BranchOnNotEqualInstruction instruction = PARSER.parseInstruction(
            BranchOnNotEqualInstruction.class, INSTRUCTION);

        assertEquals(BranchOnNotEqualInstruction.getOperationKey(),
            instruction.getOperation());
        assertEquals(MipsRegister.T0, instruction.getOperandOne());
        assertEquals(MipsRegister.S0, instruction.getOperandTwo());
        assertEquals("loop", instruction.getImmediate());
    }

    @Test
    public void testParsingInstructionWithoutSpaces()
    {
        BranchOnNotEqualInstruction instruction = PARSER.parseInstruction(
            BranchOnNotEqualInstruction.class, "bne $t0,$s0,loop");

        assertEquals(BranchOnNotEqualInstruction.getOperationKey(),
            instruction.getOperation());
        assertEquals(MipsRegister.T0, instruction.getOperandOne());
        assertEquals(MipsRegister.S0, instruction.getOperandTwo());
        assertEquals("loop", instruction.getImmediate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailedParseThrowsException()
    {
        PARSER.parseInstruction(BranchOnNotEqualInstruction.class, "<test>");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParsingInstructionWithIncorrectOperationThrowsException()
    {
        PARSER.parseInstruction(BranchOnEqualInstruction.class, INSTRUCTION);
    }
}
