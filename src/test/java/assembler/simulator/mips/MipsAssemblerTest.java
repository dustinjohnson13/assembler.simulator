package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class MipsAssemblerTest
{
    private final MipsAssembler assembler = new MipsAssembler();

    @Test
    public void testGetNumberOfRegistersReturnsCorrectAmount()
    {
        assertEquals(MipsRegister.values().length,
            assembler.getNumberOfRegisters());
    }

    @Test
    public void testGetInstructionFromInstructionLineRemovesComments()
    {
        String instruction = assembler.getInstructionStringWithoutComments(AddInstructionTest.INSTRUCTION
            + " #This is a comment");
        assertEquals("Did not find the instruction without the comment!",
            AddInstructionTest.INSTRUCTION, instruction);
    }

    @Test
    public void testGetInstructionFromInstructionLineReturnsSameLineWhenThereAreNoComments()
    {
        String instruction = assembler.getInstructionStringWithoutComments(AddInstructionTest.INSTRUCTION);
        assertEquals("Did not find the instruction!",
            AddInstructionTest.INSTRUCTION, instruction);
    }

    @Test
    public void testGetInstructionFromInstructionLineReturnsNullWhenThereAreOnlyComments()
    {
        final String comment = "# This is a comment";
        String instruction = assembler.getInstructionStringWithoutComments(comment);
        assertNull("Should not have found an instruction!", instruction);
    }

    @Test
    public void testOverflowOccurredSetsEpcToPc()
    {
        assembler.setRegisterValue(MipsRegister.EPC, 0);
        final int addressCausingException = 2;
        assembler.setProgramCounter(addressCausingException);
        assembler.overflowOccurred();
        assertEquals("EPC was not set to the value of PC!",
            addressCausingException,
            assembler.getRegisterValue(MipsRegister.EPC));
    }
}
