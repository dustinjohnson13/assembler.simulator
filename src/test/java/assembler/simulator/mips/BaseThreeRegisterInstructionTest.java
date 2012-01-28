package assembler.simulator.mips;

import org.junit.Ignore;
import org.junit.Test;

import assembler.simulator.BaseMockAssemblerTest;

@Ignore
public abstract class BaseThreeRegisterInstructionTest<T extends TwoRegisterArithmeticInstruction>
    extends BaseMockAssemblerTest
{
    @Test
    public abstract void testExecuteChangesRegisterValueOfDestination();
}
