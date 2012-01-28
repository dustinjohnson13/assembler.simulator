package assembler.simulator.mips;

public abstract class BaseJumpInstruction extends BaseInstruction
{
    protected final String address;

    protected BaseJumpInstruction(String operation, String instruction,
        String address)
    {
        super(operation, instruction);

        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }

}
