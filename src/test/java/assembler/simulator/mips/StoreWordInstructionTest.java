package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

public class StoreWordInstructionTest extends
    BaseStoreDataTransferInstructionTest<StoreWordInstruction>
{
    public static final String INSTRUCTION = "sw $t0, 32($s0)";

    @Override
    protected Class<StoreWordInstruction> getInstructionClass()
    {
        return StoreWordInstruction.class;
    }

    @Override
    protected void assertMemoryValues(int memoryAddress)
    {
        assertEquals(133, assembler.getWordFromMemory(memoryAddress));
    }

    @Override
    protected void setRegisterValues()
    {
        assembler.setRegisterValue(DESTINATION, 133);
    }

}
