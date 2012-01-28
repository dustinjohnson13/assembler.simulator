package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DataTransferInstructionParsingStrategyTest
{
    private static final DataTransferInstructionParsingStrategy PARSER = new DataTransferInstructionParsingStrategy();

    private static final String INSTRUCTION = "lw $t0, 32($s0)";

    @Test
    public void testParsingInstruction()
    {
        LoadWordInstruction instruction = PARSER.parseInstruction(
            LoadWordInstruction.class, INSTRUCTION);

        assertEquals(LoadWordInstruction.getOperationKey(),
            instruction.getOperation());
        assertEquals(MipsRegister.T0, instruction.getDataRegister());
        assertEquals(MipsRegister.S0, instruction.getAddressRegister());
        assertEquals(32, instruction.getAddressOffset());
    }

    @Test
    public void testParsingInstructionWithoutSpaces()
    {
        LoadWordInstruction instruction = PARSER.parseInstruction(
            LoadWordInstruction.class, "lw $t0,32($s0)");

        assertEquals(LoadWordInstruction.getOperationKey(),
            instruction.getOperation());
        assertEquals(MipsRegister.T0, instruction.getDataRegister());
        assertEquals(MipsRegister.S0, instruction.getAddressRegister());
        assertEquals(32, instruction.getAddressOffset());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailedParseThrowsException()
    {
        PARSER.parseInstruction(LoadWordInstruction.class, "<test>");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParsingInstructionWithIncorrectOperationThrowsException()
    {
        PARSER.parseInstruction(LoadByteInstruction.class, INSTRUCTION);
    }
}
