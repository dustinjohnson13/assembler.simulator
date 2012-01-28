package assembler.simulator.mips;

import assembler.simulator.Assembler;

public class TrapOverflowStrategy implements OverflowStrategy
{
    @Override
    public void checkForOverflow(Assembler assembler, long result)
    {
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
        {
            assembler.overflowOccurred();
        }
    }
}
