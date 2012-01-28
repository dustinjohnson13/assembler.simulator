package assembler.simulator;

import org.junit.Ignore;

@Ignore
public class MockRegister implements Register
{
    private final int number;

    public MockRegister(int i)
    {
        this.number = i;
    }

    @Override
    public int getNumericValue()
    {
        return number;
    }

    @Override
    public String getName()
    {
        return "Register" + number;
    }
}
