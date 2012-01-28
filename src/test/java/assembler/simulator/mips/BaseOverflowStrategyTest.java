package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import assembler.simulator.MockBaseAssembler;
import assembler.simulator.Register;
import assembler.simulator.mips.OverflowStrategy;

@Ignore
public abstract class BaseOverflowStrategyTest<T extends OverflowStrategy>
{
    protected final T strategy = getStrategyInstance();

    protected final MockBaseAssembler assembler = new MockBaseAssembler(
        new Register[0], null);

    @Test
    public abstract void testPositiveResultOverflow();

    @Test
    public abstract void testNegativeResultOverflow();

    @Test
    public abstract void testPositiveResultNonOverflow();

    @Test
    public abstract void testNegativeResultNonOverflow();

    protected void testOverflowScenario(long result,
        boolean shouldOverflowBeSignaled)
    {
        strategy.checkForOverflow(assembler, result);

        String emptyOrNot = (shouldOverflowBeSignaled) ? "" : "not";
        assertEquals(
            "Overflow should " + emptyOrNot + " have been signaled!",
            shouldOverflowBeSignaled, assembler.isOverflowOccurred());

    }

    protected abstract T getStrategyInstance();
}
