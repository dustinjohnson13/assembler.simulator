package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import assembler.simulator.mips.BaseAddInstruction;

public abstract class BaseAddInstructionTest<T extends BaseAddInstruction>
    extends BaseRArithmeticInstructionTest<T>
{
    private final T INSTRUCTION = getInstruction();

    @Override
    public void testExecuteChangesRegisterValueOfDestination()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, 3);
        assembler.setRegisterValue(OPERAND_TWO, 2);

        INSTRUCTION.execute(assembler);

        assertEquals("Did not find the correct value in the register!", 5,
            assembler.getRegisterValue(DESTINATION));
    }

    @Override
    public void testExecuteNegativeOverflow()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, Integer.MIN_VALUE);
        assembler.setRegisterValue(OPERAND_TWO, -1);

        INSTRUCTION.execute(assembler);

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
        assembler.setRegisterValue(OPERAND_TWO, 1);

        INSTRUCTION.execute(assembler);

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

        INSTRUCTION.execute(assembler);

        assertFalse("Overflow should not have been detected!",
            assembler.isOverflowOccurred());
    }

    @Override
    public void testExecuteWithoutPositiveOverflow()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, Integer.MAX_VALUE);
        assembler.setRegisterValue(OPERAND_TWO, 0);

        INSTRUCTION.execute(assembler);

        assertFalse("Overflow should not have been detected!",
            assembler.isOverflowOccurred());
    }

    protected abstract boolean isNotifyOnOverflow();
}
