package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

public class NorInstructionTest extends
    BaseThreeRegisterInstructionTest<NorInstruction>
{
    public static final String INSTRUCTION = "nor $t0, $s0, $s1";

    private static final NorInstruction NOR_INSTRUCTION = new NorInstruction(
        "<test>", OPERAND_ONE, OPERAND_TWO, DESTINATION);

    @Override
    public void testExecuteChangesRegisterValueOfDestination()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, -4523234);
        assembler.setRegisterValue(OPERAND_TWO, 11);

        NOR_INSTRUCTION.execute(assembler);

        // 11111111101110101111101100011110
        // 00000000000000000000000000001011
        // ================================
        // 00000000010001010000010011100000
        assertEquals("The operation did not set the correct value!", 4523232,
            assembler.getRegisterValue(DESTINATION));
    }
}
