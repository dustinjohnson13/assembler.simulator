package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import assembler.simulator.BaseMockAssemblerTest;
import assembler.simulator.MockBaseAssembler;
import assembler.simulator.MockInstructionFactory;

public class MultUnsignedInstructionTest extends BaseMockAssemblerTest
{
    public static final String INSTRUCTION = "multu $s0, $t0";

    private static final MultUnsignedInstruction MULT_UNSIGNED_INSTRUCTION = new MultUnsignedInstruction(
        "<test>", OPERAND_ONE, OPERAND_TWO, null);

    @Test
    public void testExecuteChangesRegisterValueOfDestination()
    {
        assembler = new MockBaseAssembler(MipsRegister.values(),
            new MockInstructionFactory());
        assembler.setRegisterValue(OPERAND_ONE, 8);
        assembler.setRegisterValue(OPERAND_TWO, 4);

        MULT_UNSIGNED_INSTRUCTION.execute(assembler);

        assertEquals(0, assembler.getRegisterValue(MipsRegister.HI));
        assertEquals(32, assembler.getRegisterValue(MipsRegister.LO));
    }

    @Test
    public void testExecuteChangesRegisterValueOfDestinationWithNegativeNumber()
    {
        testNegativeNumberToUnsignedInt((int) 3000000000L, 4);
    }

    @Test
    public void testExecuteChangesRegisterValueOfDestinationWithNegativeNumberReverseOperands()
    {
        testNegativeNumberToUnsignedInt(4, (int) 3000000000L);
    }

    private void testNegativeNumberToUnsignedInt(int operandOne,
        int operandTwo)
    {
        assembler = new MockBaseAssembler(MipsRegister.values(),
            new MockInstructionFactory());
        // 3,000,000,000 = -1294967296 bitwise
        assembler.setRegisterValue(OPERAND_ONE, operandOne);
        assembler.setRegisterValue(OPERAND_TWO, operandTwo);

        MULT_UNSIGNED_INSTRUCTION.execute(assembler);

        // 12000000000 decimal is the result which equals 64-bit
        // 00000000 00000000 00000000 00000010 = 2
        // 11001011 01000001 01111000 00000000 = 3410065408 unsigned
        assertEquals(2, assembler.getRegisterValue(MipsRegister.HI));
        assertEquals((int) 3410065408L,
            assembler.getRegisterValue(MipsRegister.LO));
    }

    @Test
    public void testExecutePlacesLargeValueInBothHiAndLo()
    {
        assembler = new MockBaseAssembler(MipsRegister.values(),
            new MockInstructionFactory());
        assembler.setRegisterValue(OPERAND_ONE, 1073741857);
        assembler.setRegisterValue(OPERAND_TWO, 1073741857);

        // Result should be 1152921575473808449
        // Bytes: 16 0 0 16 -128 0 4 65
        // Bits: 00010000 00000000 00000000 00010000 = 268435472 (2^28 + 2^4)
        // decimal
        // 10000000 00000000 00000100 01000001 = -2147482559 decimal
        // 01111111 11111111 11111011 10111111 = 2147482559
        // 10000000 00000000 00000100 01000000 = 1's complement
        // 10000000 00000000 00000100 01000001 = 2's complement
        MULT_UNSIGNED_INSTRUCTION.execute(assembler);

        assertEquals(268435472, assembler.getRegisterValue(MipsRegister.HI));
        assertEquals(-2147482559, assembler.getRegisterValue(MipsRegister.LO));
    }
}
