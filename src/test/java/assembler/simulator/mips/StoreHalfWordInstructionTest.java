package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

public class StoreHalfWordInstructionTest extends
    BaseStoreDataTransferInstructionTest<StoreHalfWordInstruction>
{
    public static final String INSTRUCTION = "sh $t0, 32($s0)";

    @Override
    protected Class<StoreHalfWordInstruction> getInstructionClass()
    {
        return StoreHalfWordInstruction.class;
    }

    @Override
    protected void assertMemoryValues(int memoryAddress)
    {
        // -------- -------- 01000000 00000110 =
        assertEquals(16390, assembler.getHalfWordFromMemory(memoryAddress));
    }

    @Override
    protected void setRegisterValues()
    {
        // 00010001 00110001 01000000 00000110
        assembler.setRegisterValue(DESTINATION, 288440326);
    }

}
