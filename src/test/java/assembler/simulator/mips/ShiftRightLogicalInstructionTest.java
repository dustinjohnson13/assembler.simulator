package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ShiftRightLogicalInstructionTest extends
    BaseIInstructionTest<ShiftRightLogicalInstruction>
{

    public static final String INSTRUCTION = "srl $t0, $s0, 4";

    private static final ShiftRightLogicalInstruction SRL_INSTRUCTION = new ShiftRightLogicalInstruction(
        "<test>", DESTINATION, 2, OPERAND_ONE);

    @Override
    public void testExecuteChangesRegisterValueOfDestination()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, 11);

        SRL_INSTRUCTION.execute(assembler);

        // 00000000000000000000000000001011
        // >> 2
        // ================================
        // 00000000000000000000000000000010
        assertEquals("The operation did not set the correct value!", 2,
            assembler.getRegisterValue(DESTINATION));
    }

    @Test
    public void testExecuteDoesNotRetainNegativeNumber()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, -4);

        SRL_INSTRUCTION.execute(assembler);

        // 11111111111111111111111111111100
        // >> 2
        // ================================
        // 00111111111111111111111111111111
        assertTrue("The destination value should not have stayed negative!",
            assembler.getRegisterValue(DESTINATION) > 0);
    }

    @Override
    protected ShiftRightLogicalInstruction getInstructionWithImmediate(
        int immediate)
    {
        return new ShiftRightLogicalInstruction("<test>", DESTINATION,
            immediate, OPERAND_ONE);
    }

    @Override
    protected boolean isNotifyOnOverflow()
    {
        return false;
    }
}