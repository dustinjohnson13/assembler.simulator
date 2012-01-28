package assembler.simulator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import assembler.simulator.Assembler.Bytes;
import assembler.simulator.mips.AddImmediateInstructionTest;
import assembler.simulator.mips.LoadWordInstructionTest;
import assembler.simulator.mips.MipsAssembler;
import assembler.simulator.mips.SetOnLessThanImmediateInstructionTest;
import assembler.simulator.mips.StoreWordInstructionTest;

@RunWith(JMock.class)
public class BaseAssemblerTest
{
    private final Mockery mockContext = new JUnit4Mockery();

    private final Register mockRegister1 = new MockRegister(0);

    private final Register mockRegister2 = new MockRegister(1);

    private final Register mockRegister3 = new MockRegister(2);

    private BaseAssembler assembler = new MockBaseAssembler(new Register[]
    { mockRegister1, mockRegister2, mockRegister3 },
        new MockInstructionFactory());

    @Test
    public void testSetAndGetValueInRegister()
    {
        assembler.setRegisterValue(mockRegister1, 4);
        assembler.setRegisterValue(mockRegister2, 8);
        assertEquals(4, assembler.getRegisterValue(mockRegister1));
        assertEquals(8, assembler.getRegisterValue(mockRegister2));
    }

    @Test
    public void testParseInstructionCallsThroughToInstructionFactory()
    {
        final InstructionFactory mockInstructionFactory = mockContext.mock(InstructionFactory.class);

        assembler = new MockBaseAssembler(new Register[3],
            mockInstructionFactory);

        final String instructionLine = "someline";

        mockContext.checking(new Expectations() {
            {
                oneOf(mockInstructionFactory).parseInstruction(
                    instructionLine);
            }
        });

        assembler.parseInstruction(instructionLine);
    }

    @Test
    public void testInstructionsAreAddedToListInOrderEntered()
    {
        MockInstruction mock1 = new MockInstruction();
        MockInstruction mock2 = new MockInstruction();

        assembler = new MockBaseAssembler(new Register[3],
            new MockInstructionFactory(mock1, mock2));

        assembler.processLine(AddImmediateInstructionTest.INSTRUCTION);
        assembler.processLine(SetOnLessThanImmediateInstructionTest.INSTRUCTION);

        Iterator<Instruction> instructionIter = assembler.instructionList.iterator();
        assertEquals(mock1, instructionIter.next());
        assertEquals(mock2, instructionIter.next());
    }

    @Test
    public void testInstructionsAreRunWhenRunInstructionsIsCalled()
    {
        final Instruction instruction1 = mockContext.mock(Instruction.class,
            "instruction1");
        final Instruction instruction2 = mockContext.mock(Instruction.class,
            "instruction2");

        assembler.instructionList.add(instruction1);
        assembler.instructionList.add(instruction2);

        mockContext.checking(new Expectations() {
            {
                oneOf(instruction1).execute(with(assembler));
                oneOf(instruction2).execute(with(assembler));
            }
        });

        assembler.runInstructions();
    }

    @Test
    public void testRunInstructionRunsASingleInstruction()
    {
        final Instruction instruction1 = mockContext.mock(Instruction.class,
            "instruction1");
        final Instruction instruction2 = mockContext.mock(Instruction.class,
            "instruction2");

        assembler.instructionList.add(instruction1);
        assembler.instructionList.add(instruction2);

        mockContext.checking(new Expectations() {
            {
                oneOf(instruction1).execute(with(assembler));
                never(instruction2).execute(with(assembler));
            }
        });

        assembler.runInstruction();

    }

    @Test
    public void testGetProgramCounterReturnsCorrectValue()
    {
        assembler.processLine("use a mock instruction from the mock factory");

        assertEquals(0, assembler.getProgramCounter());
        assembler.runInstruction();
        assertEquals(1, assembler.getProgramCounter());
    }

    @Test
    public void testSetProgramCounterSetsCorrectValue()
    {
        assembler.processLine("use a mock instruction from the mock factory");

        assertEquals(0, assembler.getProgramCounter());
        assembler.setProgramCounter(1);
        assertEquals(1, assembler.getProgramCounter());
    }

    @Test
    public void testInstructionsWithLabelHaveLabelToIndexMappingPlacedInInstructionMap()
    {
        final Instruction mock1 = new MockInstruction("Label1");
        final Instruction mock2 = new MockInstruction();
        final Instruction mock3 = new MockInstruction("Label2");

        assembler = new MockBaseAssembler(new Register[3],
            new MockInstructionFactory(mock1, mock2, mock3));

        // returns mock1
        assembler.processLine(AddImmediateInstructionTest.INSTRUCTION);
        // returns mock2
        assembler.processLine(LoadWordInstructionTest.INSTRUCTION);
        // returns mock3
        assembler.processLine(SetOnLessThanImmediateInstructionTest.INSTRUCTION);

        assertEquals(new Integer(2),
            assembler.labelToInstructionIndex.get("Label2"));
        assertEquals(new Integer(0),
            assembler.labelToInstructionIndex.get("Label1"));
    }

    @Test
    public void testExecuteInstructionCallsThroughToInstructionWithRegisterValues()
    {
        final Instruction mockInstruction = mockContext.mock(Instruction.class);

        mockContext.checking(new Expectations() {
            {
                oneOf(mockInstruction).execute(with(assembler));
            }
        });

        assembler.executeInstruction(mockInstruction);
    }

    @Test
    public void testExecutingInstructionNotifiesObservers()
    {
        final Observer mockObserver = mockContext.mock(Observer.class);
        final Instruction mockInstruction = mockContext.mock(Instruction.class);

        mockContext.checking(new Expectations() {
            {
                allowing(mockInstruction);
                exactly(2).of(mockObserver).update();
            }
        });

        assembler.registerObserver(mockObserver);
        assembler.executeInstruction(mockInstruction);
    }

    @Test
    public void testRegisterObserverCallsUpdateOnTheObserver()
    {
        final Observer mockObserver = mockContext.mock(Observer.class);
        final Instruction mockInstruction = mockContext.mock(Instruction.class);

        mockContext.checking(new Expectations() {
            {
                allowing(mockInstruction);
                oneOf(mockObserver).update();
            }
        });

        assembler.registerObserver(mockObserver);
    }

    @Test
    public void testJumpBeingCalledChangesProgramCounterToThatInstructionMinusOne()
    {
        final Instruction mock1 = new MockInstruction();
        final Instruction mock2 = new MockInstruction();
        final Instruction mock3 = new MockInstruction("Label2");

        assembler = new MockBaseAssembler(new Register[3],
            new MockInstructionFactory(mock1, mock2, mock3));

        // returns mock1
        assembler.processLine(AddImmediateInstructionTest.INSTRUCTION);
        // returns mock2
        assembler.processLine(LoadWordInstructionTest.INSTRUCTION);
        // returns mock3
        assembler.processLine(SetOnLessThanImmediateInstructionTest.INSTRUCTION);

        // Verify the jump target was setup correctly
        assertEquals(new Integer(2),
            assembler.labelToInstructionIndex.get("Label2"));
        assertEquals(0, assembler.programCounter);

        assembler.jump("Label2");

        assertEquals(2, assembler.programCounter);
    }

    @Test
    public void testResettingRegistersSetsAllRegistersToZero()
    {
        assembler.setRegisterValue(mockRegister2, 2);

        assembler.resetRegisters();

        assertEquals(Assembler.INITIAL_REGISTER_VALUE,
            assembler.getRegisterValue(mockRegister2));
    }

    @Test
    public void testClearingInstructionsRemovesAllInstructions()
    {
        assembler.processLine(LoadWordInstructionTest.INSTRUCTION);
        assertEquals(1, assembler.getInstructions().size());

        assembler.clearInstructions();
        assertEquals(0, assembler.getInstructions().size());
    }

    @Test
    public void testLoadingInstructionsClearsCurrentInstructions()
    {
        // Have to use a MipsAssembler to have access to its actual
        // instruction factory since we're parsing instructions
        assembler = new MipsAssembler();

        assembler.processLine(LoadWordInstructionTest.INSTRUCTION);
        assertEquals(1, assembler.getInstructions().size());

        File tempFile = createTempInstructionsFile(
            StoreWordInstructionTest.INSTRUCTION,
            AddImmediateInstructionTest.INSTRUCTION);

        assembler.loadInstructions(tempFile);
        assertEquals("It doesn't appear old instructions were cleared!", 2,
            assembler.getInstructions().size());
    }

    @Test
    public void testLoadingInstructionsLoadsAllFromFile()
    {
        // Have to use a MipsAssembler to have access to its actual
        // instruction factory since we're parsing instructions
        assembler = new MipsAssembler();

        assertTrue("The current list of instructions should be empty!",
            assembler.getInstructions().isEmpty());

        File tempFile = createTempInstructionsFile(
            StoreWordInstructionTest.INSTRUCTION,
            AddImmediateInstructionTest.INSTRUCTION);

        assembler.loadInstructions(tempFile);
        assertEquals("All instructions don't appear to have been loaded!", 2,
            assembler.getInstructions().size());
    }

    @Test(expected = IllegalStateException.class)
    public void testLoadingInstructionsFileThrowsExceptionOnMissingFile()
    {
        assembler.loadInstructions(new File("fake"));
    }

    @Test
    public void testClosingACloseableThrowingAnIOExceptionIsCaught()
        throws IOException
    {
        final Closeable closeable = mockContext.mock(Closeable.class);

        mockContext.checking(new Expectations() {
            {
                oneOf(closeable).close();
                will(throwException(new IOException("on purpose")));
            }
        });

        assembler.close(closeable);
    }

    @Test
    public void testPlacingHighBytesIntoRegister()
    {
        // generated bytes = 0 0 0 4 0 0 0 0
        final long longValue = 17179869184L;

        assembler.setRegisterValue(mockRegister1, longValue, Bytes.HIGH);

        assertEquals(4, assembler.getRegisterValue(mockRegister1));
    }

    @Test
    public void testPlacingLowBytesIntoRegister()
    {
        // generated bytes = 0 0 0 0 0 0 4 0
        final long longValue = 1024;

        assembler.setRegisterValue(mockRegister1, longValue, Bytes.LOW);

        assertEquals(1024, assembler.getRegisterValue(mockRegister1));
    }

    private File createTempInstructionsFile(String... instructions)
    {

        PrintWriter printWriter = null;
        try
        {
            File file = File.createTempFile("pref", "suf");
            printWriter = new PrintWriter(file);

            for (String instruction : instructions)
            {
                printWriter.println(instruction);
            }

            return file;
        }
        catch (IOException e)
        {
            throw new IllegalStateException(e);
        }
        finally
        {
            if (printWriter != null)
            {
                printWriter.close();
            }
        }
    }
}
