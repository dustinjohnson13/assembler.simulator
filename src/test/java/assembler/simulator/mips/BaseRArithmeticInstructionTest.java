package assembler.simulator.mips;

import org.junit.Ignore;
import org.junit.Test;

import assembler.simulator.mips.TwoRegisterArithmeticInstruction;

@Ignore
public abstract class BaseRArithmeticInstructionTest<T extends TwoRegisterArithmeticInstruction>
    extends BaseThreeRegisterInstructionTest<T>
{
    @Test
    public abstract void testExecutePositiveOverflow();

    @Test
    public abstract void testExecuteNegativeOverflow();

    @Test
    public abstract void testExecuteWithoutNegativeOverflow();

    @Test
    public abstract void testExecuteWithoutPositiveOverflow();

    protected abstract T getInstruction();
}
