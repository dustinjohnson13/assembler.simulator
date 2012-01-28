package assembler.simulator.mips;

import assembler.simulator.Assembler;

public interface OverflowStrategy
{
    void checkForOverflow(Assembler assembler, long result);

    IgnoreOverflowStrategy IGNORE = new IgnoreOverflowStrategy();
}
