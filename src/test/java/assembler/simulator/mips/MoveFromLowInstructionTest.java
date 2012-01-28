package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import assembler.simulator.BaseMockAssemblerTest;
import assembler.simulator.MockBaseAssembler;
import assembler.simulator.MockInstructionFactory;

public class MoveFromLowInstructionTest extends BaseMockAssemblerTest
{
    public static final String INSTRUCTION = "mflo $s0";

    @Test
    public void testExecuteChangesValueOfRegister()
    {
        final int value = 39898;

        assembler = new MockBaseAssembler(MipsRegister.values(),
            new MockInstructionFactory());
        assembler.setRegisterValue(MipsRegister.S0, 0);
        assembler.setRegisterValue(MipsRegister.HI, 0);
        assembler.setRegisterValue(MipsRegister.LO, value);

        MoveFromLowInstruction instruction = new MoveFromLowInstruction(
            "<test>", MipsRegister.S0);
        instruction.execute(assembler);

        assertEquals(value, assembler.getRegisterValue(MipsRegister.S0));
    }
}