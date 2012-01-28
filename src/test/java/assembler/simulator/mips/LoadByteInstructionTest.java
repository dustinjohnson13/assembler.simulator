package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

public class LoadByteInstructionTest extends
    BaseLoadDataTransferInstructionTest<LoadByteInstruction>
{
    public static final String INSTRUCTION = "lb $t0, 32($s0)";

    @Override
    protected void setValueInMemory(int memoryAddress, int value)
    {
        byte truncatedValue = (byte) value;
        assembler.setByteInMemory(memoryAddress, truncatedValue);
    }

    @Override
    protected void checkLoadedData(int originalValue)
    {
        assertEquals(-6, assembler.getRegisterValue(DESTINATION));
    }

    @Override
    protected Class<LoadByteInstruction> getInstructionClass()
    {
        return LoadByteInstruction.class;
    }
}
