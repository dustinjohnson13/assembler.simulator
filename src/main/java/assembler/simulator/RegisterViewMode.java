package assembler.simulator;

public interface RegisterViewMode
{
    String format(int registerValue);

    String getDisplayString();
}
