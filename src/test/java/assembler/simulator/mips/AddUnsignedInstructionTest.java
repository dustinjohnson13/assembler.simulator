package assembler.simulator.mips;

public class AddUnsignedInstructionTest extends
    BaseAddInstructionTest<AddUnsignedInstruction>
{
    public static final String INSTRUCTION = "addu $t0, $s0, $s1";

    private static final AddUnsignedInstruction ADD_UNSIGNED_INSTRUCTION = new AddUnsignedInstruction(
        "<test>", OPERAND_ONE, OPERAND_TWO, DESTINATION);

    @Override
    protected AddUnsignedInstruction getInstruction()
    {
        return ADD_UNSIGNED_INSTRUCTION;
    }

    @Override
    protected boolean isNotifyOnOverflow()
    {
        return false;
    }
}
