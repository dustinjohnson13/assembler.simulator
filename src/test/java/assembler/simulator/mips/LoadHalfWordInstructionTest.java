package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

public class LoadHalfWordInstructionTest extends
    BaseLoadDataTransferInstructionTest<LoadHalfWordInstruction>
{
    public static final String INSTRUCTION = "lh $t0, 32($s0)";

    @Override
    protected void setValueInMemory(int memoryAddress, int value)
    {
        short truncatedValue = (short) value;
        assembler.setHalfWordInMemory(memoryAddress, truncatedValue);
    }

    @Override
    protected void checkLoadedData(int originalValue)
    {
        assertEquals(-16390, assembler.getRegisterValue(DESTINATION));
    }

    @Override
    protected Class<LoadHalfWordInstruction> getInstructionClass()
    {
        return LoadHalfWordInstruction.class;
    }
}
