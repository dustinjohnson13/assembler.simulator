package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

public class XorInstructionTest extends
    BaseThreeRegisterInstructionTest<XorInstruction>
{
    public static final String INSTRUCTION = "xor $t0, $s0, $s1";

    private static final XorInstruction XOR_INSTRUCTION = new XorInstruction(
        "<test>", OPERAND_ONE, OPERAND_TWO, DESTINATION);

    @Override
    public void testExecuteChangesRegisterValueOfDestination()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, -2);
        assembler.setRegisterValue(OPERAND_TWO, 11);

        XOR_INSTRUCTION.execute(assembler);

        // 11111111111111111111111111111110
        // 00000000000000000000000000001011
        // ================================
        // 11111111111111111111111111110101
        assertEquals("The operation did not set the correct value!", -11,
            assembler.getRegisterValue(DESTINATION));
    }
}
