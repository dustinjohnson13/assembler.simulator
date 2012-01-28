package assembler.simulator.mips;

import assembler.simulator.mips.TrapOverflowStrategy;

public class TrapOverflowStrategyTest extends
    BaseOverflowStrategyTest<TrapOverflowStrategy>
{
    private static final TrapOverflowStrategy INSTANCE = new TrapOverflowStrategy();

    @Override
    public void testPositiveResultOverflow()
    {
        final long anOverflow = Integer.MAX_VALUE + 1L;
        testOverflowScenario(anOverflow, true);
    }

    @Override
    public void testNegativeResultOverflow()
    {
        final long anOverflow = Integer.MIN_VALUE - 1L;
        testOverflowScenario(anOverflow, true);
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
    protected TrapOverflowStrategy getStrategyInstance()
    {
        return INSTANCE;
    }
}
