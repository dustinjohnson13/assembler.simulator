package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AddImmediateInstructionTest extends
    BaseAddImmediateInstructionTest<AddImmediateInstruction>
{
    public static final String INSTRUCTION = "addi $t0, $s0, 4";

    private static final AddImmediateInstruction ADD_INSTRUCTION = new AddImmediateInstruction(
        "<text>", DESTINATION, 4, OPERAND_ONE);

    @Test
    public void testNegativeImmediate()
    {
        assembler.setRegisterValue(OPERAND_ONE, 6);

        AddImmediateInstruction instruction = getInstructionWithImmediate(-4);
        instruction.execute(assembler);

        assertEquals("Incorrect result found in the register!", 2,
            assembler.getRegisterValue(DESTINATION));
    }

    @Override
    protected AddImmediateInstruction getInstruction()
    {
        return ADD_INSTRUCTION;
    }

    // @Override
    // protected AddImmediateInstruction getParsedInstruction()
    // {
    // return new AddImmediateInstruction(INSTRUCTION);
    // }
    //
    // @Override
    // protected AddImmediateInstruction getParsedInstructionWithoutSpaces()
    // {
    // return new AddImmediateInstruction("addi $t0,$s0,4");
    // }
    //
    @Override
    protected AddImmediateInstruction getInstructionWithImmediate(
        int immediate)
    {
        return new AddImmediateInstruction("<test>", DESTINATION, immediate,
            OPERAND_ONE);
    }

    @Override
    protected boolean isNotifyOnOverflow()
    {
        return true;
    }

    @Override
    protected String getExpectedOpcode()
    {
        return AddImmediateInstruction.getOperationKey();
    }

}
