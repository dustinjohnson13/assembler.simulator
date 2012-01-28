package assembler.simulator.mips;

import org.junit.Ignore;
import org.junit.Test;

import assembler.simulator.BaseMockAssemblerTest;

@Ignore
public abstract class BaseIInstructionTest<T extends OneRegisterArithmeticInstruction>
    extends BaseMockAssemblerTest
{
    @Test
    public abstract void testExecuteChangesRegisterValueOfDestination();

    protected abstract T getInstructionWithImmediate(int immediate);

    protected abstract boolean isNotifyOnOverflow();
}
