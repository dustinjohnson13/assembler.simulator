package assembler.simulator;

import org.junit.Ignore;

@Ignore
public class MockBaseAssembler extends BaseAssembler
{
    public static RegisterViewMode DEFAULT_REGISTER_VIEW_MODE = RegisterViewModeImpl.UNSIGNED_INTEGER;

    private boolean overflowOccured;

    public MockBaseAssembler(Register[] registers,
        InstructionFactory instructionFactory)
    {
        super(registers, instructionFactory, 32);
        setRegisterViewMode(DEFAULT_REGISTER_VIEW_MODE);
    }

    @Override
    protected String getInstructionStringWithoutComments(String line)
    {
        return line;
    }

    @Override
    public void overflowOccurred()
    {
        overflowOccured = true;
    }

    public boolean isOverflowOccurred()
    {
        return overflowOccured;
    }
}
