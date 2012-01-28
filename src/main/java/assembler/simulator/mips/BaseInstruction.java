package assembler.simulator.mips;

import assembler.simulator.Instruction;

public abstract class BaseInstruction implements Instruction
{
    protected final String operation;

    protected final String originalLine;

    protected String label;

    protected <T extends BaseInstruction> BaseInstruction(String operation,
        String originalLine)
    {
        this.operation = operation;
        this.originalLine = originalLine;
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

    @Override
    public String toString()
    {
        return originalLine;
    }

    @Override
    public String getOperation()
    {
        return operation;
    }
}
