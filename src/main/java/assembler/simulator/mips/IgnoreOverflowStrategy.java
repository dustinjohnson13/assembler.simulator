package assembler.simulator.mips;

import assembler.simulator.Assembler;

class IgnoreOverflowStrategy implements OverflowStrategy
{
    @Override
    public void checkForOverflow(Assembler assembler, long result)
    {
        // No overflow notifications period in this strategy
    }
}
