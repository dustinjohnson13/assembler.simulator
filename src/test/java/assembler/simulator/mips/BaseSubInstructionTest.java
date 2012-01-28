package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import assembler.simulator.mips.BaseSubInstruction;

public abstract class BaseSubInstructionTest<T extends BaseSubInstruction>
    extends BaseRArithmeticInstructionTest<T>
{
    private final T instruction = getInstruction();

    @Override
    public void testExecuteChangesRegisterValueOfDestination()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, 3);
        assembler.setRegisterValue(OPERAND_TWO, 2);

        instruction.execute(assembler);

        assertEquals("Did not find the correct value in the register!", 1,
            assembler.getRegisterValue(DESTINATION));
    }

    @Override
    public void testExecuteNegativeOverflow()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, Integer.MIN_VALUE);
        assembler.setRegisterValue(OPERAND_TWO, 1);

        instruction.execute(assembler);

        boolean notifyOnOverflow = isNotifyOnOverflow();
        String emptyOrNot = notifyOnOverflow ? "" : " not";
        assertEquals("Overflow should" + emptyOrNot + " have been detected!",
            notifyOnOverflow, assembler.isOverflowOccurred());
    }

    @Override
    public void testExecutePositiveOverflow()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, Integer.MAX_VALUE);
        assembler.setRegisterValue(OPERAND_TWO, -1);

        instruction.execute(assembler);

        boolean notifyOnOverflow = isNotifyOnOverflow();
        String emptyOrNot = notifyOnOverflow ? "" : " not";
        assertEquals("Overflow should" + emptyOrNot + " have been detected!",
            notifyOnOverflow, assembler.isOverflowOccurred());
    }

    @Override
    public void testExecuteWithoutNegativeOverflow()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, Integer.MIN_VALUE);
        assembler.setRegisterValue(OPERAND_TWO, 0);

        instruction.execute(assembler);

        assertFalse("Overflow should not have been detected!",
            assembler.isOverflowOccurred());
    }

    @Override
    public void testExecuteWithoutPositiveOverflow()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, Integer.MAX_VALUE);
        assembler.setRegisterValue(OPERAND_TWO, 0);

        instruction.execute(assembler);

        assertFalse("Overflow should not have been detected!",
            assembler.isOverflowOccurred());
    }

    protected abstract boolean isNotifyOnOverflow();
}
