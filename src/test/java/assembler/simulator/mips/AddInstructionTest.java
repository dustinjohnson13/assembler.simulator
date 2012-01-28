package assembler.simulator.mips;

public class AddInstructionTest extends
    BaseAddInstructionTest<AddInstruction>
{
    public static final String INSTRUCTION = "add $t0, $s0, $s1";

    private static final AddInstruction ADD_INSTRUCTION = new AddInstruction(
        "<test>", OPERAND_ONE, OPERAND_TWO, DESTINATION);

    @Override
    protected AddInstruction getInstruction()
    {
        return ADD_INSTRUCTION;
    }

    @Override
    protected boolean isNotifyOnOverflow()
    {
        return true;
    }
}
