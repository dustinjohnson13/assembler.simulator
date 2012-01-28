package assembler.simulator.mips;

import assembler.simulator.Register;

public abstract class OneRegisterDestinationInstruction extends
    BaseInstruction
{
    private final Register destination;

    OneRegisterDestinationInstruction(String operation, String originalLine,
        Register destination)
    {
        super(operation, originalLine);
        this.destination = destination;
    }

    Register getDestination()
    {
        return destination;
    }
}
