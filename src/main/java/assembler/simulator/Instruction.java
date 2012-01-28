package assembler.simulator;

public interface Instruction
{
    String getOperation();

    void execute(Assembler assembler);

    String getLabel();

    @Override
    String toString();
}
