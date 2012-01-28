package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

public class StoreByteInstructionTest extends
    BaseStoreDataTransferInstructionTest<StoreByteInstruction>
{
    public static final String INSTRUCTION = "sb $t0, 32($s0)";

    @Override
    protected Class<StoreByteInstruction> getInstructionClass()
    {
        return StoreByteInstruction.class;
    }

    @Override
    protected void assertMemoryValues(int memoryAddress)
    {
        // -------- -------- -------- 00000110 =
        assertEquals(6, assembler.getHalfWordFromMemory(memoryAddress));
    }

    @Override
    protected void setRegisterValues()
    {
        // 00010001 00110001 01000000 00000110
        assembler.setRegisterValue(DESTINATION, 288440326);
    }

}
