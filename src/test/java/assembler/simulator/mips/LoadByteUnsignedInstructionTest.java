package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

public class LoadByteUnsignedInstructionTest extends
    BaseLoadDataTransferInstructionTest<LoadByteUnsignedInstruction>
{
    public static final String INSTRUCTION = "lbu $t0, 32($s0)";

    @Override
    protected void setValueInMemory(int memoryAddress, int value)
    {
        byte truncatedValue = (byte) value;
        assembler.setByteInMemory(memoryAddress, truncatedValue);
    }

    @Override
    protected void checkLoadedData(int originalValue)
    {
        assertEquals(250, assembler.getRegisterValue(DESTINATION));
    }

    @Override
    protected Class<LoadByteUnsignedInstruction> getInstructionClass()
    {
        return LoadByteUnsignedInstruction.class;
    }
}
