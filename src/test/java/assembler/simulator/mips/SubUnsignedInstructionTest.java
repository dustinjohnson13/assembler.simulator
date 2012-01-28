package assembler.simulator.mips;

public class SubUnsignedInstructionTest extends
    BaseSubInstructionTest<SubUnsignedInstruction>
{
    public static final String INSTRUCTION = "subu $t0, $s0, $s1";

    private static final SubUnsignedInstruction SUB_INSTRUCTION = new SubUnsignedInstruction(
        "<test>", OPERAND_ONE, OPERAND_TWO, DESTINATION);

    @Override
    protected SubUnsignedInstruction getInstruction()
    {
        return SUB_INSTRUCTION;
    }

    @Override
    protected boolean isNotifyOnOverflow()
    {
        return false;
    }
}
