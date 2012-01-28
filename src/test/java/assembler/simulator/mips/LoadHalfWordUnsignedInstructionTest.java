package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

public class LoadHalfWordUnsignedInstructionTest extends
    BaseLoadDataTransferInstructionTest<LoadHalfWordUnsignedInstruction>
{
    public static final String INSTRUCTION = "lhu $t0, 32($s0)";

    @Override
    protected void setValueInMemory(int memoryAddress, int value)
    {
        short truncatedValue = (short) value;
        assembler.setHalfWordInMemory(memoryAddress, truncatedValue);
    }

    @Override
    protected void checkLoadedData(int originalValue)
    {
        // -16390 in 16-bits = unsigned 49146
        assertEquals(49146, assembler.getRegisterValue(DESTINATION));
    }

    @Override
    protected Class<LoadHalfWordUnsignedInstruction> getInstructionClass()
    {
        return LoadHalfWordUnsignedInstruction.class;
    }
}
