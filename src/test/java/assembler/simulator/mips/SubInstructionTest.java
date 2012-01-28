package assembler.simulator.mips;

public class SubInstructionTest extends
    BaseSubInstructionTest<SubInstruction>
{
    public static final String INSTRUCTION = "sub $t0, $s0, $s1";

    private static final SubInstruction SUB_INSTRUCTION = new SubInstruction(
        "<test>", OPERAND_ONE, OPERAND_TWO, DESTINATION);

    @Override
    protected boolean isNotifyOnOverflow()
    {
        return true;
    }

    @Override
    protected SubInstruction getInstruction()
    {
        return SUB_INSTRUCTION;
    }

}
