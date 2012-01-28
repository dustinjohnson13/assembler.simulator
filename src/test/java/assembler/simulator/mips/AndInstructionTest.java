package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

public class AndInstructionTest extends
    BaseThreeRegisterInstructionTest<AndInstruction>
{
    public static final String INSTRUCTION = "and $t0, $s0, $s1";

    private static final AndInstruction AND_INSTRUCTION = new AndInstruction(
        "<test>", OPERAND_ONE, OPERAND_TWO, DESTINATION);

    @Override
    public void testExecuteChangesRegisterValueOfDestination()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, -2);
        assembler.setRegisterValue(OPERAND_TWO, 11);

        AND_INSTRUCTION.execute(assembler);

        // 11111111111111111111111111111110
        // 00000000000000000000000000001011
        // ================================
        // 00000000000000000000000000001010
        assertEquals("The and operation did not set the correct value!", 10,
            assembler.getRegisterValue(DESTINATION));
    }
}
