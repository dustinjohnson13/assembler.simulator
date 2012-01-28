package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import assembler.simulator.Instruction;

public class MipsInstructionFactoryTest
{
    private static final MipsInstructionFactory FACTORY = new MipsInstructionFactory();

    @Test
    public void testParsingInstructionWithLabelSetsLabel()
    {
        Instruction instruction = FACTORY.parseInstruction("MyLabel: "
            + AddInstructionTest.INSTRUCTION);
        assertEquals("MyLabel", instruction.getLabel());
    }

    @Test
    public void testParsingAddInstructionWithoutLabelDoesNotSetLabel()
    {
        Instruction instruction = FACTORY.parseInstruction(AddInstructionTest.INSTRUCTION);
        assertNull(instruction.getLabel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParsingBadInstructionThrowsException()
    {
        FACTORY.parseInstruction("invalid");
    }

    @Test
    public void testParsingAddUnsignedInstruction()
    {
        testParse(AddUnsignedInstruction.class);
    }

    @Test
    public void testParsingAddInstruction()
    {
        testParse(AddInstruction.class);
    }

    @Test
    public void testParsingSubInstruction()
    {
        testParse(SubInstruction.class);
    }

    @Test
    public void testParsingSubUnsignedInstruction()
    {
        testParse(SubUnsignedInstruction.class);
    }

    @Test
    public void testParsingLoadWordInstruction()
    {
        testParse(LoadWordInstruction.class);
    }

    @Test
    public void testParsingStoreWordInstruction()
    {
        testParse(StoreWordInstruction.class);
    }

    @Test
    public void testParsingAddImmediateInstruction()
    {
        testParse(AddImmediateInstruction.class);
    }

    @Test
    public void testParsingSetOnLessThanImmediateInstruction()
    {
        testParse(SetOnLessThanImmediateInstruction.class);
    }

    @Test
    public void testParsingJumpInstruction()
    {
        testParse(JumpInstruction.class);
    }

    @Test
    public void testParsingJumpAndLinkInstruction()
    {
        testParse(JumpAndLinkInstruction.class);
    }

    @Test
    public void testParsingJumpRegisterInstruction()
    {
        testParse(JumpRegisterInstruction.class);
    }

    @Test
    public void testParsingBranchOnEqualInstruction()
    {
        testParse(BranchOnEqualInstruction.class);
    }

    @Test
    public void testParsingBranchOnNotEqualInstruction()
    {
        testParse(BranchOnNotEqualInstruction.class);
    }

    @Test
    public void testParsingAddImmediateUnsignedInstruction()
    {
        testParse(AddImmediateUnsignedInstruction.class);
    }

    @Test
    public void testParsingMultInstruction()
    {
        testParse(MultInstruction.class);
    }

    @Test
    public void testParsingMultUnsignedInstruction()
    {
        testParse(MultUnsignedInstruction.class);
    }

    @Test
    public void testParsingDivInstruction()
    {
        testParse(DivInstruction.class);
    }

    @Test
    public void testParsingDivUnsignedInstruction()
    {
        testParse(DivUnsignedInstruction.class);
    }

    @Test
    public void testParsingAndInstruction()
    {
        testParse(AndInstruction.class);
    }

    @Test
    public void testParsingAndImmediateInstruction()
    {
        testParse(AndImmediateInstruction.class);
    }

    @Test
    public void testParsingOrInstruction()
    {
        testParse(OrInstruction.class);
    }

    @Test
    public void testParsingOrImmediateInstruction()
    {
        testParse(OrImmediateInstruction.class);
    }

    @Test
    public void testParsingXorInstruction()
    {
        testParse(XorInstruction.class);
    }

    @Test
    public void testParsingNorInstruction()
    {
        testParse(NorInstruction.class);
    }

    @Test
    public void testParsingSetOnLessThanInstruction()
    {
        testParse(SetOnLessThanInstruction.class);
    }

    @Test
    public void testParsingShiftLeftLogicalInstruction()
    {
        testParse(ShiftLeftLogicalInstruction.class);
    }

    @Test
    public void testParsingShiftRightLogicalInstruction()
    {
        testParse(ShiftRightLogicalInstruction.class);
    }

    @Test
    public void testParsingShiftRightArithmeticInstruction()
    {
        testParse(ShiftRightArithmeticInstruction.class);
    }

    @Test
    public void testParsingLoadDoubleWordInstruction()
    {
        testParse(LoadDoubleWordInstruction.class);
    }

    @Test
    public void testParsingStoreDoubleWordInstruction()
    {
        testParse(StoreDoubleWordInstruction.class);
    }

    @Test
    public void testParsingStoreHalfWordInstruction()
    {
        testParse(StoreHalfWordInstruction.class);
    }

    @Test
    public void testParsingStoreByteInstruction()
    {
        testParse(StoreByteInstruction.class);
    }

    @Test
    public void testParsingLoadHalfWordInstruction()
    {
        testParse(LoadHalfWordInstruction.class);
    }

    @Test
    public void testParsingLoadHalfWordUnsignedInstruction()
    {
        testParse(LoadHalfWordUnsignedInstruction.class);
    }

    @Test
    public void testParsingLoadByteInstruction()
    {
        testParse(LoadByteInstruction.class);
    }

    @Test
    public void testParsingLoadByteUnsignedInstruction()
    {
        testParse(LoadByteUnsignedInstruction.class);
    }

    @Test
    public void testParsingLoadUpperImmediateInstruction()
    {
        testParse(LoadUpperImmediateInstruction.class);
    }

    @Test
    public void testParsingMoveFromHighInstruction()
    {
        testParse(MoveFromHighInstruction.class);
    }

    @Test
    public void testParsingMoveFromLowInstruction()
    {
        testParse(MoveFromLowInstruction.class);
    }

    @Test
    public void testParsingMoveFromControlRegisterInstruction()
    {
        testParse(MoveFromControlRegisterInstruction.class);
    }

    private <T extends Instruction> void testParse(Class<T> clazz)
    {
        try
        {
            // Get the INSTRUCTION string each test class has
            Class<?> testClass = Class.forName(clazz.getName() + "Test");
            String instructionAsString = (String) testClass.getField(
                "INSTRUCTION").get(null);
            Instruction instruction = FACTORY.parseInstruction(instructionAsString);
            assertEquals("Incorrect instruction returned!", clazz,
                instruction.getClass());
        }
        catch (Exception e)
        {
            if (e instanceof IllegalArgumentException)
            {
                throw (IllegalArgumentException) e;
            }
            else
            {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
