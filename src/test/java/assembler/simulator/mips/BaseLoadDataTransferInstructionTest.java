package assembler.simulator.mips;

import java.lang.reflect.Constructor;

import org.junit.Test;

import assembler.simulator.BaseMockAssemblerTest;
import assembler.simulator.Register;

public abstract class BaseLoadDataTransferInstructionTest<T extends DataTransferInstruction>
    extends BaseMockAssemblerTest
{
    @Test
    public void testExecuteLoadsMemoryValueIntoRegister()
    {
        final int baseOfArray = 4;
        final int memoryValue = -288440326;
        // 11101110 11001110 10111111 11111010
        final int offset = 8; // This should get us ARRAY[2] since a word is 4
                              // bytes

        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, baseOfArray);

        setValueInMemory(baseOfArray + offset, memoryValue);

        Constructor<T> ctr;
        T instruction = null;
        try
        {
            ctr = getInstructionClass().getConstructor(String.class,
                Register.class, int.class, Register.class);
            instruction = ctr.newInstance("<test>", DESTINATION, offset,
                OPERAND_ONE);
        }
        catch (Exception e)
        {
            throw new IllegalStateException(e);
        }

        instruction.execute(assembler);

        // Now check that the value was moved into the register
        checkLoadedData(memoryValue);
    }

    protected abstract Class<T> getInstructionClass();

    protected abstract void checkLoadedData(int memoryValue);

    protected abstract void setValueInMemory(int memoryAddress, int value);
}
