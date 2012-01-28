package assembler.simulator.mips;

import java.lang.reflect.Constructor;

import org.junit.Test;

import assembler.simulator.BaseMockAssemblerTest;
import assembler.simulator.Register;

public abstract class BaseStoreDataTransferInstructionTest<T extends DataTransferInstruction>
    extends BaseMockAssemblerTest
{
    @Test
    public void testExecuteStoresRegisterValueIntoMemory()
    {
        final int baseOfArray = 4;
        final int offset = 8; // This should get us ARRAY[2] since a word is 4
                              // bytes

        setRegisterValues();
        assembler.setRegisterValue(OPERAND_ONE, baseOfArray);

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

        assertMemoryValues(baseOfArray + offset);
    }

    protected abstract Class<T> getInstructionClass();

    protected abstract void assertMemoryValues(int memoryAddress);

    protected abstract void setRegisterValues();
}
