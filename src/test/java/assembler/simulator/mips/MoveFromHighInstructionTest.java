package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import assembler.simulator.BaseMockAssemblerTest;
import assembler.simulator.MockBaseAssembler;
import assembler.simulator.MockInstructionFactory;

public class MoveFromHighInstructionTest extends BaseMockAssemblerTest
{
    public static final String INSTRUCTION = "mfhi $s0";

    @Test
    public void testExecuteChangesValueOfRegister()
    {
        final int value = 39898;

        assembler = new MockBaseAssembler(MipsRegister.values(),
            new MockInstructionFactory());
        assembler.setRegisterValue(MipsRegister.S0, 0);
        assembler.setRegisterValue(MipsRegister.HI, value);
        assembler.setRegisterValue(MipsRegister.LO, 0);

        MoveFromHighInstruction instruction = new MoveFromHighInstruction(
            "<test>", MipsRegister.S0);
        instruction.execute(assembler);

        assertEquals(value, assembler.getRegisterValue(MipsRegister.S0));
    }
}