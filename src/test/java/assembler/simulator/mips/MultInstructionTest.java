package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import assembler.simulator.BaseMockAssemblerTest;
import assembler.simulator.MockBaseAssembler;
import assembler.simulator.MockInstructionFactory;

public class MultInstructionTest extends BaseMockAssemblerTest
{
    public static final String INSTRUCTION = "mult $s0, $t0";

    private static final MultInstruction MULT_INSTRUCTION = new MultInstruction(
        "<test>", OPERAND_ONE, OPERAND_TWO, null);

    @Test
    public void testExecuteChangesRegisterValueOfDestination()
    {

        assembler = new MockBaseAssembler(MipsRegister.values(),
            new MockInstructionFactory());
        assembler.setRegisterValue(OPERAND_ONE, 8);
        assembler.setRegisterValue(OPERAND_TWO, 4);

        MULT_INSTRUCTION.execute(assembler);

        assertEquals(0, assembler.getRegisterValue(MipsRegister.HI));
        assertEquals(32, assembler.getRegisterValue(MipsRegister.LO));
    }

    @Test
    public void testExecuteChangesRegisterValueOfDestinationWithNegativeNumber()
    {
        assembler = new MockBaseAssembler(MipsRegister.values(),
            new MockInstructionFactory());
        assembler.setRegisterValue(OPERAND_ONE, -8);
        assembler.setRegisterValue(OPERAND_TWO, 4);

        MULT_INSTRUCTION.execute(assembler);

        assertEquals(-1, assembler.getRegisterValue(MipsRegister.HI));
        assertEquals(-32, assembler.getRegisterValue(MipsRegister.LO));
    }

    @Test
    public void testNegativeNumberToSignedInt()
    {
        assembler = new MockBaseAssembler(MipsRegister.values(),
            new MockInstructionFactory());
        assembler.setRegisterValue(OPERAND_ONE, -1294967296);
        assembler.setRegisterValue(OPERAND_TWO, 4);

        MULT_INSTRUCTION.execute(assembler);

        // -5179869184 decimal is the result which equals 64-bit
        // 11111111 11111111 11111111 11111110 = -2
        // 11001011 01000001 01111000 00000000 = -884901888

        assertEquals(-2, assembler.getRegisterValue(MipsRegister.HI));
        assertEquals(-884901888, assembler.getRegisterValue(MipsRegister.LO));
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
        MULT_INSTRUCTION.execute(assembler);

        assertEquals(268435472, assembler.getRegisterValue(MipsRegister.HI));
        assertEquals(-2147482559, assembler.getRegisterValue(MipsRegister.LO));
    }
}
