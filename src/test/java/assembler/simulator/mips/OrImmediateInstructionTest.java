package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

public class OrImmediateInstructionTest extends
    BaseIInstructionTest<OrImmediateInstruction>
{
    public static final String INSTRUCTION = "ori $t0, $s0, 4";

    private static final OrImmediateInstruction OR_INSTRUCTION = new OrImmediateInstruction(
        "<test>", DESTINATION, -2, OPERAND_ONE);

    @Override
    public void testExecuteChangesRegisterValueOfDestination()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, 11);

        OR_INSTRUCTION.execute(assembler);

        // 11111111111111111111111111111110
        // 00000000000000000000000000001011
        // ================================
        // 00000000000000000000000000001010
        assertEquals("The and operation did not set the correct value!", -1,
            assembler.getRegisterValue(DESTINATION));
    }

    @Override
    protected OrImmediateInstruction getInstructionWithImmediate(int immediate)
    {
        return new OrImmediateInstruction("<test>", DESTINATION, immediate,
            OPERAND_ONE);
    }

    @Override
    protected boolean isNotifyOnOverflow()
    {
        return false;
    }
}
