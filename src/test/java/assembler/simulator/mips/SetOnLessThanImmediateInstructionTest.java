package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import assembler.simulator.BaseMockAssemblerTest;

public class SetOnLessThanImmediateInstructionTest extends BaseMockAssemblerTest
{
    public static final String INSTRUCTION = "slti $t0, $s0, 4";

    @Test
    public void testExecuteChangesRegisterValueOfDestinationTo1IfLessThanIsTrue()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, 3);

        SetOnLessThanImmediateInstruction instruction = getInstructionWithImmediate(4);

        instruction.execute(assembler);

        assertEquals("Did not find the correct value in the register!", 1,
            assembler.getRegisterValue(DESTINATION));
    }

    @Test
    public void testNegativeImmediateRegisterIsLessThan()
    {
        assembler.setRegisterValue(OPERAND_ONE, -6);

        SetOnLessThanImmediateInstruction instruction = getInstructionWithImmediate(-4);
        instruction.execute(assembler);

        assertEquals("Incorrect result found in the register!", 1,
            assembler.getRegisterValue(DESTINATION));
    }

    @Test
    public void testNegativeImmediateRegisterIsNotLessThan()
    {
        assembler.setRegisterValue(OPERAND_ONE, -2);

        SetOnLessThanImmediateInstruction instruction = getInstructionWithImmediate(-4);

        instruction.execute(assembler);

        assertEquals("Incorrect result found in the register!", 0,
            assembler.getRegisterValue(DESTINATION));
    }

    @Test
    public void testExecuteChangesRegisterValueOfDestinationTo0IfLessThanIsFalse()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, 3);

        SetOnLessThanImmediateInstruction instruction = getInstructionWithImmediate(2);

        instruction.execute(assembler);

        assertEquals("Did not find the correct value in the register!", 0,
            assembler.getRegisterValue(DESTINATION));
    }

    @Test
    public void testExecuteChangesRegisterValueTo0IfOperandsAreEqual()
    {
        assembler.setRegisterValue(DESTINATION, 0);
        assembler.setRegisterValue(OPERAND_ONE, 3);

        SetOnLessThanImmediateInstruction instruction = getInstructionWithImmediate(3);

        instruction.execute(assembler);

        assertEquals("Did not find the correct value in the register!", 0,
            assembler.getRegisterValue(DESTINATION));
    }

    private SetOnLessThanImmediateInstruction getInstructionWithImmediate(
        int immediate)
    {
        return new SetOnLessThanImmediateInstruction("<test>", DESTINATION,
            immediate, OPERAND_ONE);
    }

}
