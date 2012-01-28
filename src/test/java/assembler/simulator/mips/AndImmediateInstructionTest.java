package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

public class AndImmediateInstructionTest extends
    BaseIInstructionTest<AndImmediateInstruction>
{
    public static final String INSTRUCTION = "andi $t0, $s0, 4";

    private static final AndImmediateInstruction AND_INSTRUCTION = new AndImmediateInstruction(
        "<test>", DESTINATION, -2, OPERAND_ONE);

    @Override
    public void testExecuteChangesRegisterValueOfDestination()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, 11);

        AND_INSTRUCTION.execute(assembler);

        // 11111111111111111111111111111110
        // 00000000000000000000000000001011
        // ================================
        // 00000000000000000000000000001010
        assertEquals("The and operation did not set the correct value!", 10,
            assembler.getRegisterValue(DESTINATION));
    }

    @Override
    protected AndImmediateInstruction getInstructionWithImmediate(
        int immediate)
    {
        return new AndImmediateInstruction("<test>", DESTINATION, immediate,
            OPERAND_ONE);
    }

    @Override
    protected boolean isNotifyOnOverflow()
    {
        return false;
    }
}
