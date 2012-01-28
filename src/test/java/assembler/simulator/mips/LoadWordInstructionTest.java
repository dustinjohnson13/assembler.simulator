package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

public class LoadWordInstructionTest extends
    BaseLoadDataTransferInstructionTest<LoadWordInstruction>
{
    public static final String INSTRUCTION = "lw $t0, 32($s0)";

    @Override
    protected void setValueInMemory(int memoryAddress, int value)
    {
        assembler.setWordInMemory(memoryAddress, value);
    }

    @Override
    protected void checkLoadedData(int memoryValue)
    {
        assertEquals(memoryValue, assembler.getRegisterValue(DESTINATION));
    }

    @Override
    protected Class<LoadWordInstruction> getInstructionClass()
    {
        return LoadWordInstruction.class;
    }
}
