package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Ignore;
import org.junit.Test;

import assembler.simulator.BaseMockAssemblerTest;

@Ignore
public abstract class BaseAddImmediateInstructionTest<T extends BaseAddImmediateInstruction>
    extends BaseMockAssemblerTest
{
    private final T instruction = getInstruction();

    // @Test
    // public void testParsingAddInstruction()
    // {
    // T instruction = getParsedInstruction();
    //
    // assertEquals(getExpectedOpcode(), instruction.getOperation());
    // assertEquals(MipsRegister.T0, instruction.getDestination());
    // assertEquals(MipsRegister.S0, instruction.getOperandOne());
    // assertEquals(new Integer(4), instruction.getOperandTwo());
    // }
    //
    // @Test
    // public void testParsingAddInstructionWithNoSpaces()
    // {
    // T instruction = getParsedInstructionWithoutSpaces();
    //
    // assertEquals(getExpectedOpcode(), instruction.getOperation());
    // assertEquals(MipsRegister.T0, instruction.getDestination());
    // assertEquals(MipsRegister.S0, instruction.getOperandOne());
    // assertEquals(new Integer(4), instruction.getOperandTwo());
    // }

    @Test
    public void testExecuteChangesRegisterValueOfDestination()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, 3);

        instruction.execute(assembler);

        assertEquals("Did not find the correct value in the register!", 7,
            assembler.getRegisterValue(DESTINATION));
    }

    @Test
    public void testExecuteWithPositiveOverflow()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, Integer.MAX_VALUE);

        instruction.execute(assembler);

        boolean notifyOnOverflow = isNotifyOnOverflow();
        String emptyOrNot = notifyOnOverflow ? "" : " not";
        assertEquals("Overflow should" + emptyOrNot + " have been detected!",
            notifyOnOverflow, assembler.isOverflowOccurred());
    }

    @Test
    public void testExecuteWithNegativeOverflow()
    {
        final T overrodeInstruction = getInstructionWithImmediate(-1);

        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, Integer.MIN_VALUE);

        overrodeInstruction.execute(assembler);

        boolean notifyOnOverflow = isNotifyOnOverflow();
        String emptyOrNot = notifyOnOverflow ? "" : " not";
        assertEquals("Overflow should" + emptyOrNot + " have been detected!",
            notifyOnOverflow, assembler.isOverflowOccurred());
    }

    @Test
    public void testExecuteWithoutPositiveOverflow()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, 0);

        instruction.execute(assembler);

        assertFalse("Overflow should not have been detected!",
            assembler.isOverflowOccurred());
    }

    @Test
    public void testExecuteWithoutNegativeOverflow()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, Integer.MIN_VALUE);

        instruction.execute(assembler);

        assertFalse("Overflow should not have been detected!",
            assembler.isOverflowOccurred());
    }

    protected abstract String getExpectedOpcode();

    protected abstract T getInstruction();

    // protected abstract T getParsedInstruction();
    //
    // protected abstract T getParsedInstructionWithoutSpaces();

    protected abstract T getInstructionWithImmediate(int immediate);

    protected abstract boolean isNotifyOnOverflow();
}
