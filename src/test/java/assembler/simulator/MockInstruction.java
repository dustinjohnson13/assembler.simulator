package assembler.simulator;

import org.junit.Ignore;

@Ignore
public class MockInstruction implements Instruction
{
    public MockInstruction()
    {
    }

    public MockInstruction(String label)
    {
        this.label = label;
    }

    private String label;

    @Override
    public String getOperation()
    {
        return "mock";
    }

    @Override
    public void execute(Assembler assembler)
    {
        // Do nothing
    }

    @Override
    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }
}
