package assembler.simulator.mips;

import assembler.simulator.Assembler;
import assembler.simulator.Register;

public class LoadUpperImmediateInstruction extends
    OneRegisterDestinationInstruction
{
    private static final int BITS_IN_HALF_WORD = 16;

	private static final String OPERATION = "lui";

    private final int immediate;

    public LoadUpperImmediateInstruction(String instruction,
        Register destination, int immediate)
    {
        super(OPERATION, instruction, destination);
        this.immediate = immediate;
    }

    @Override
    public void execute(Assembler assembler)
    {
        short valueAsSixteenBits = (short) getImmediate();
        int asHighIntBits = valueAsSixteenBits << BITS_IN_HALF_WORD;

        assembler.setRegisterValue(getDestination(), asHighIntBits);
    }

    int getImmediate()
    {
        return immediate;
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }

}
