package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

public class StoreDoubleWordInstructionTest extends
    BaseStoreDataTransferInstructionTest<StoreDoubleWordInstruction>
{
    public static final String INSTRUCTION = "sd $t0, 32($s0)";

    @Override
    protected Class<StoreDoubleWordInstruction> getInstructionClass()
    {
        return StoreDoubleWordInstruction.class;
    }

    @Override
    protected void assertMemoryValues(int memoryAddress)
    {
        long value = assembler.getDoubleWordFromMemory(memoryAddress);
        assertEquals(17823762763234L, value);
    }

    @Override
    protected void setRegisterValues()
    {
        assembler.setRegisterValue(DESTINATION, 4149);
        assembler.setRegisterValue(FOUR, -351515166);
    }
}