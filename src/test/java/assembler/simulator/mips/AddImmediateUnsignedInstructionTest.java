package assembler.simulator.mips;

public class AddImmediateUnsignedInstructionTest extends
    BaseAddImmediateInstructionTest<AddImmediateUnsignedInstruction>
{
    public static final String INSTRUCTION = "addiu $t0, $s0, 4";

    private static final AddImmediateUnsignedInstruction ADD_INSTRUCTION = new AddImmediateUnsignedInstruction(
        "<test>", DESTINATION, 4, OPERAND_ONE);

    @Override
    protected AddImmediateUnsignedInstruction getInstruction()
    {
        return ADD_INSTRUCTION;
    }

    @Override
    protected AddImmediateUnsignedInstruction getInstructionWithImmediate(
        int immediate)
    {
        return new AddImmediateUnsignedInstruction("<test>", DESTINATION,
            immediate, OPERAND_ONE);
    }

    @Override
    protected boolean isNotifyOnOverflow()
    {
        return false;
    }

    @Override
    protected String getExpectedOpcode()
    {
        return AddImmediateUnsignedInstruction.getOperationKey();
    }

}
