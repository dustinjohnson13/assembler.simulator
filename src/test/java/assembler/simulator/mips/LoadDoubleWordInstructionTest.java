package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LoadDoubleWordInstructionTest extends
    BaseLoadDataTransferInstructionTest<LoadDoubleWordInstruction>
{
    public static final String INSTRUCTION = "ld $t0, 32($s0)";

    @Test
    public void testExecuteLoadsMemoryValueIntoBothDataRegisters()
    {
        final int baseOfArray = 4;
        final long memoryValue = 17823762763234L;
        // 00000000000000000001000000110101 (upper bits) = 4149
        // 11101011000011000100110111100010 (lower bits) = -351515166
        // 00010100111100111011001000011110 (two's complement) = 351515166
        //
        final int offset = 8; // This should get us ARRAY[2] since a word is 4
                              // bytes

        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, baseOfArray);

        assembler.setDoubleWordInMemory(baseOfArray + offset, memoryValue);

        LoadDoubleWordInstruction instruction = new LoadDoubleWordInstruction(
            "<test>", DESTINATION, offset, OPERAND_ONE);
        instruction.execute(assembler);

        assertEquals(4149, assembler.getRegisterValue(DESTINATION));
        assertEquals(-351515166, assembler.getRegisterValue(FOUR));
    }

    @Override
    protected void checkLoadedData(int memoryValue)
    {
        assertEquals(-1, assembler.getRegisterValue(DESTINATION));
        assertEquals(memoryValue, assembler.getRegisterValue(FOUR));
    }

    @Override
    protected void setValueInMemory(int memoryAddress, int value)
    {
        assembler.setDoubleWordInMemory(memoryAddress, value);
    }

    @Override
    protected Class<LoadDoubleWordInstruction> getInstructionClass()
    {
        return LoadDoubleWordInstruction.class;
    }
}
