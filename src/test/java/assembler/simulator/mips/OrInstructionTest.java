package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

public class OrInstructionTest extends
    BaseThreeRegisterInstructionTest<OrInstruction>
{
    public static final String INSTRUCTION = "or $t0, $s0, $s1";

    private static final OrInstruction OR_INSTRUCTION = new OrInstruction(
        "<test>", OPERAND_ONE, OPERAND_TWO, DESTINATION);

    @Override
    public void testExecuteChangesRegisterValueOfDestination()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, -2);
        assembler.setRegisterValue(OPERAND_TWO, 11);

        OR_INSTRUCTION.execute(assembler);

        // 11111111111111111111111111111110
        // 00000000000000000000000000001011
        // ================================
        // 00000000000000000000000000001010
        assertEquals("The operation did not set the correct value!", -1,
            assembler.getRegisterValue(DESTINATION));
    }
}
