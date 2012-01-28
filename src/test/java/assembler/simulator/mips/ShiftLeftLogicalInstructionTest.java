package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

public class ShiftLeftLogicalInstructionTest extends
    BaseIInstructionTest<ShiftLeftLogicalInstruction>
{

    public static final String INSTRUCTION = "sll $t0, $s0, 4";

    private static final ShiftLeftLogicalInstruction SLL_INSTRUCTION = new ShiftLeftLogicalInstruction(
        "<test>", DESTINATION, 2, OPERAND_ONE);

    @Override
    public void testExecuteChangesRegisterValueOfDestination()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, 11);

        SLL_INSTRUCTION.execute(assembler);

        // 00000000000000000000000000001011
        // << 2
        // ================================
        // 00000000000000000000000000101100
        assertEquals("The operation did not set the correct value!", 44,
            assembler.getRegisterValue(DESTINATION));
    }

    @Override
    protected ShiftLeftLogicalInstruction getInstructionWithImmediate(
        int immediate)
    {
        return new ShiftLeftLogicalInstruction("<test>", DESTINATION,
            immediate, OPERAND_ONE);
    }

    @Override
    protected boolean isNotifyOnOverflow()
    {
        return false;
    }
}
