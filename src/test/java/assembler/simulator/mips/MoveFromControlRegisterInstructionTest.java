package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import assembler.simulator.BaseMockAssemblerTest;

public class MoveFromControlRegisterInstructionTest extends
    BaseMockAssemblerTest
{
    public static final String INSTRUCTION = "mfc0 $s0, $t0";

    private static final MoveFromControlRegisterInstruction MFC_INSTRUCTION = new MoveFromControlRegisterInstruction(
        "<test>", OPERAND_ONE, OPERAND_TWO, null);;

    @Test
    public void testExecuteChangesRegisterValueOfDestination()
    {
        assembler.setRegisterValue(OPERAND_ONE, 0);
        assembler.setRegisterValue(OPERAND_TWO, 10);

        MFC_INSTRUCTION.execute(assembler);

        assertEquals(10, assembler.getRegisterValue(OPERAND_ONE));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCallingCalculateResultThrowsException()
    {
        MFC_INSTRUCTION.calculateResult(0, 0);
    }
}
