package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import assembler.simulator.BaseMockAssemblerTest;

public class LoadUpperImmediateInstructionTest extends BaseMockAssemblerTest
{
    public static final String INSTRUCTION = "lui $s0, 7";

    private static final LoadUpperImmediateInstruction LUI_INSTRUCTION = new LoadUpperImmediateInstruction(
        "<test>", DESTINATION, 7);

    @Test
    public void testExecuteChangesValueOfRegister()
    {
        assembler.setRegisterValue(DESTINATION, -1);

        LUI_INSTRUCTION.execute(assembler);

        // Expecting 00000000 00000111 becomes 00000000 00000111 00000000
        // 00000000
        assertEquals(458752, assembler.getRegisterValue(DESTINATION));
    }
}
