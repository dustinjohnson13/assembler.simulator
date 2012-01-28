package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import assembler.simulator.BaseMockAssemblerTest;
import assembler.simulator.MockBaseAssembler;
import assembler.simulator.MockInstructionFactory;

public class DivUnsignedInstructionTest extends BaseMockAssemblerTest
{
    public static final String INSTRUCTION = "divu $s0, $t0";

    private static final DivUnsignedInstruction DIV_INSTRUCTION = new DivUnsignedInstruction(
        "<test>", OPERAND_ONE, OPERAND_TWO, null);

    @Test
    public void testExecuteChangesRegisterValueOfDestination()
    {
        assembler = new MockBaseAssembler(MipsRegister.values(),
            new MockInstructionFactory());
        assembler.setRegisterValue(OPERAND_ONE, 10);
        assembler.setRegisterValue(OPERAND_TWO, 3);

        DIV_INSTRUCTION.execute(assembler);

        assertEquals(3, assembler.getRegisterValue(MipsRegister.LO));
        assertEquals(1, assembler.getRegisterValue(MipsRegister.HI));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCallingCalculateResultThrowsException()
    {
        DivUnsignedInstruction instruction = new DivUnsignedInstruction(
            "<test>", OPERAND_ONE, OPERAND_TWO, null);
        instruction.calculateResult(1L, 1L);
    }

    @Test
    public void testExecuteChangesRegisterValueOfDestinationWithNegativeNumbers()
    {
        assembler = new MockBaseAssembler(MipsRegister.values(),
            new MockInstructionFactory());
        // 3,000,000,000 = -1294967296 bitwise
        assembler.setRegisterValue(OPERAND_ONE, (int) 3000000000L);
        assembler.setRegisterValue(OPERAND_TWO, 7);

        DIV_INSTRUCTION.execute(assembler);

        assertEquals(428571428, assembler.getRegisterValue(MipsRegister.LO));
        assertEquals(4, assembler.getRegisterValue(MipsRegister.HI));
    }

    @Test
    public void testExecuteChangesRegisterValueOfDestinationWithBothNegativeNumbers()
    {
        assembler = new MockBaseAssembler(MipsRegister.values(),
            new MockInstructionFactory());
        // 3,000,000,000 = -1294967296 bitwise
        assembler.setRegisterValue(OPERAND_ONE, (int) 3000000000L);
        assembler.setRegisterValue(OPERAND_TWO, (int) 3000000000L);

        DIV_INSTRUCTION.execute(assembler);

        assertEquals(1, assembler.getRegisterValue(MipsRegister.LO));
        assertEquals(0, assembler.getRegisterValue(MipsRegister.HI));
    }
}