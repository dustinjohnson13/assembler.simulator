package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SetOnLessThanInstructionTest extends
    BaseThreeRegisterInstructionTest<SetOnLessThanInstruction>
{
    public static final String INSTRUCTION = "slt $t0, $s0, $s1";

    private static final SetOnLessThanInstruction SLT_INSTRUCTION = new SetOnLessThanInstruction(
        "<test>", OPERAND_ONE, OPERAND_TWO, DESTINATION);

    @Override
    public void testExecuteChangesRegisterValueOfDestination()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, -2);
        assembler.setRegisterValue(OPERAND_TWO, 11);

        SLT_INSTRUCTION.execute(assembler);

        // -2 < 11 so the result should be 1
        assertEquals("The operation did not set the correct value!", 1,
            assembler.getRegisterValue(DESTINATION));
    }

    @Test
    public void testExecuteChangesRegisterValueTo0IfOperandsAreEqual()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, -2);
        assembler.setRegisterValue(OPERAND_TWO, -2);

        SLT_INSTRUCTION.execute(assembler);

        // -2 not less than -2 so the result should be 0
        assertEquals("The operation did not set the correct value!", 0,
            assembler.getRegisterValue(DESTINATION));
    }

    @Test
    public void testExecuteChangesRegisterValueTo0IfOperandOneNotLessThan()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, 2);
        assembler.setRegisterValue(OPERAND_TWO, -2);

        SLT_INSTRUCTION.execute(assembler);

        // 2 not less than -2 so the result should be 0
        assertEquals("The operation did not set the correct value!", 0,
            assembler.getRegisterValue(DESTINATION));
    }
}
