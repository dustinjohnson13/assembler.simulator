package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import assembler.simulator.BaseMockAssemblerTest;
import assembler.simulator.MockBaseAssembler;
import assembler.simulator.MockInstructionFactory;

public class DivInstructionTest extends BaseMockAssemblerTest
{
    public static final String INSTRUCTION = "div $s0, $t0";

    private static final DivInstruction DIV_INSTRUCTION = new DivInstruction(
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
        DivInstruction instruction = new DivInstruction("<test>",
            OPERAND_ONE, OPERAND_TWO, null);
        instruction.calculateResult(1L, 1L);
    }

    @Test
    public void testExecuteChangesRegisterValueOfDestinationWithNegativeNumbers()
    {

        assembler = new MockBaseAssembler(MipsRegister.values(),
            new MockInstructionFactory());
        assembler.setRegisterValue(OPERAND_ONE, -10);
        assembler.setRegisterValue(OPERAND_TWO, 3);

        DIV_INSTRUCTION.execute(assembler);

        assertEquals(-3, assembler.getRegisterValue(MipsRegister.LO));
        assertEquals(-1, assembler.getRegisterValue(MipsRegister.HI));
    }
}
