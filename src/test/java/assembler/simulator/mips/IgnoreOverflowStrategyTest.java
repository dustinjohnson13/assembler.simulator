package assembler.simulator.mips;

import assembler.simulator.mips.IgnoreOverflowStrategy;
import assembler.simulator.mips.OverflowStrategy;

public class IgnoreOverflowStrategyTest extends
    BaseOverflowStrategyTest<IgnoreOverflowStrategy>
{
    @Override
    public void testPositiveResultOverflow()
    {
        final long anOverflow = Integer.MAX_VALUE + 1L;
        testOverflowScenario(anOverflow, false);
    }

    @Override
    public void testNegativeResultOverflow()
    {
        final long anOverflow = Integer.MIN_VALUE - 1L;
        testOverflowScenario(anOverflow, false);
    }

    @Override
    public void testPositiveResultNonOverflow()
    {
        final long anOverflow = Integer.MAX_VALUE;
        testOverflowScenario(anOverflow, false);
    }

    @Override
    public void testNegativeResultNonOverflow()
    {
        final long anOverflow = Integer.MIN_VALUE;
        testOverflowScenario(anOverflow, false);
    }

    @Override
    protected IgnoreOverflowStrategy getStrategyInstance()
    {
        return OverflowStrategy.IGNORE;
    }
}
