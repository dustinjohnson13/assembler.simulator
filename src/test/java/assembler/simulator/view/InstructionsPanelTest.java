package assembler.simulator.view;

import static org.junit.Assert.assertEquals;

import java.awt.Component;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import assembler.simulator.Assembler;
import assembler.simulator.MockInstruction;
import assembler.simulator.view.InstructionsPanel.FileChooserStrategy;

@RunWith(JMock.class)
public class InstructionsPanelTest
{
    private final Mockery mockContext = new JUnit4Mockery();

    private final Assembler mockAssembler = mockContext.mock(Assembler.class);

    @Test
    public void testRunButtonCallsRunOnAssembler()
    {
        JButton runButton = InstructionsPanel.getRunButton(mockAssembler);

        mockContext.checking(new Expectations() {
            {
                oneOf(mockAssembler).runInstructions();
            }
        });

        runButton.doClick();
    }

    @Test
    public void testStepButtonCallsRunInstructionOnAssembler()
    {
        JButton stepButton = InstructionsPanel.getStepButton(mockAssembler);

        mockContext.checking(new Expectations() {
            {
                oneOf(mockAssembler).runInstruction();
            }
        });

        stepButton.doClick();
    }

    @Test
    public void testClearButtonCallsClearInstructionsOnAssembler()
    {
        JButton clearButton = InstructionsPanel.getClearButton(mockAssembler);

        mockContext.checking(new Expectations() {
            {
                oneOf(mockAssembler).clearInstructions();
            }
        });

        clearButton.doClick();
    }

    @Test
    public void testInstructionsListContainsAllInstructions()
    {
        final Collection<MockInstruction> instructions = Arrays.asList(
            new MockInstruction(), new MockInstruction());
        mockContext.checking(new Expectations() {
            {
                oneOf(mockAssembler).getInstructions();
                will(returnValue(instructions));
                allowing(mockAssembler).getProgramCounter();
            }
        });

        JList list = InstructionsPanel.getInstructionsList(mockAssembler);
        assertEquals(2, list.getModel().getSize());

        Iterator<MockInstruction> iterator = instructions.iterator();
        assertEquals(iterator.next(), list.getModel().getElementAt(0));
        assertEquals(iterator.next(), list.getModel().getElementAt(1));
    }

    @Test
    public void testInstructionsListSetsSelectedItemToProgramCounter()
    {
        final int programCounter = 1;
        final Collection<MockInstruction> instructions = Arrays.asList(
            new MockInstruction(), new MockInstruction());
        mockContext.checking(new Expectations() {
            {
                allowing(mockAssembler).getInstructions();
                will(returnValue(instructions));
                oneOf(mockAssembler).getProgramCounter();
                will(returnValue(programCounter));
            }
        });

        JList list = InstructionsPanel.getInstructionsList(mockAssembler);
        assertEquals(programCounter, list.getSelectedIndex());
    }

    @Test
    public void testInstructionsListSelectionChangedSetsProgramCounter()
    {
        final int programCounter = 1;
        final Collection<MockInstruction> instructions = Arrays.asList(
            new MockInstruction(), new MockInstruction());
        mockContext.checking(new Expectations() {
            {
                allowing(mockAssembler).getInstructions();
                will(returnValue(instructions));
                allowing(mockAssembler).getProgramCounter();
                oneOf(mockAssembler).setProgramCounter(programCounter);
            }
        });

        JList list = InstructionsPanel.getInstructionsList(mockAssembler);
        list.setSelectedIndex(programCounter);
    }

    @Test
    public void testInstructionsListIsPlacedInScrollPane()
    {
        mockContext.checking(new Expectations() {
            {
                allowing(mockAssembler);
            }
        });

        InstructionsPanel panel = new InstructionsPanel(mockAssembler);

        JScrollPane scrollPane = SwingTestUtils.getChildNamed(panel,
            InstructionsPanel.INSTRUCTIONS_LIST_SCROLL_PANE,
            JScrollPane.class);

        SwingTestUtils.getChildNamed(scrollPane,
            InstructionsPanel.INSTRUCTIONS_LIST, JList.class);
    }

    @Test
    public void testAddInstructionsPanelContainsTextField()
    {
        JPanel panel = InstructionsPanel.getAddInstructionPanel(mockAssembler);
        SwingTestUtils.getChildNamed(panel,
            InstructionsPanel.ADD_INSTRUCTION_TEXT_FIELD, JTextField.class);
    }

    @Test
    public void testAddInstructionsPanelContainsAddInstructionButton()
    {
        JPanel panel = InstructionsPanel.getAddInstructionPanel(mockAssembler);
        SwingTestUtils.getChildNamed(panel,
            InstructionsPanel.ADD_INSTRUCTION_BUTTON, JButton.class);
    }

    @Test
    public void testAddInstructionButtonClickClearsTextField()
    {
        mockContext.checking(new Expectations() {
            {
                allowing(mockAssembler);
            }
        });

        JPanel panel = InstructionsPanel.getAddInstructionPanel(mockAssembler);

        JTextField text = SwingTestUtils.getChildNamed(panel,
            InstructionsPanel.ADD_INSTRUCTION_TEXT_FIELD, JTextField.class);
        text.setText("this should be removed");

        JButton button = SwingTestUtils.getChildNamed(panel,
            InstructionsPanel.ADD_INSTRUCTION_BUTTON, JButton.class);
        button.doClick();

        assertEquals("The textfield should have been cleared!", "",
            text.getText());
    }

    @Test
    public void testAddInstructionButtonClickSendsInstructionToAssembler()
    {
        final String lineToProcess = "this should be removed";
        mockContext.checking(new Expectations() {
            {
                oneOf(mockAssembler).processLine(lineToProcess);
            }
        });

        JPanel panel = InstructionsPanel.getAddInstructionPanel(mockAssembler);

        JTextField text = SwingTestUtils.getChildNamed(panel,
            InstructionsPanel.ADD_INSTRUCTION_TEXT_FIELD, JTextField.class);
        text.setText(lineToProcess);

        JButton button = SwingTestUtils.getChildNamed(panel,
            InstructionsPanel.ADD_INSTRUCTION_BUTTON, JButton.class);
        button.doClick();
    }

    @Test
    public void testLoadButtonClickSendsFileNameToAssembler()
    {
        final File file = new File("someFile");
        final FileChooserStrategy mockFileChooserStrategy = mockContext.mock(FileChooserStrategy.class);
        mockContext.checking(new Expectations() {
            {
                oneOf(mockFileChooserStrategy).getFile(
                    with(any(Component.class)));
                will(returnValue(file));
                oneOf(mockAssembler).loadInstructions(file);
            }
        });

        InstructionsPanel.fileChooserStrategy = mockFileChooserStrategy;
        JPanel panel = InstructionsPanel.getButtonsPanel(mockAssembler);

        JButton button = SwingTestUtils.getChildNamed(panel,
            InstructionsPanel.LOAD_BUTTON, JButton.class);
        button.doClick();
    }

    @Test
    public void testLoadButtonClickDoesNotSendFileNameToAssemblerIfNull()
    {
        final FileChooserStrategy mockFileChooserStrategy = mockContext.mock(FileChooserStrategy.class);
        mockContext.checking(new Expectations() {
            {
                oneOf(mockFileChooserStrategy).getFile(
                    with(any(Component.class)));
                will(returnValue(null));
                never(mockAssembler).loadInstructions(with(any(File.class)));
            }
        });

        InstructionsPanel.fileChooserStrategy = mockFileChooserStrategy;
        JPanel panel = InstructionsPanel.getButtonsPanel(mockAssembler);

        JButton button = SwingTestUtils.getChildNamed(panel,
            InstructionsPanel.LOAD_BUTTON, JButton.class);
        button.doClick();
    }

    @Test
    public void testButtonsPanelContainsRunButton()
    {
        testButtonsPanelContainsButton(InstructionsPanel.RUN_BUTTON);
    }

    @Test
    public void testButtonsPanelContainsStepButton()
    {
        testButtonsPanelContainsButton(InstructionsPanel.STEP_BUTTON);
    }

    @Test
    public void testButtonsPanelContainsClearButton()
    {
        testButtonsPanelContainsButton(InstructionsPanel.CLEAR_BUTTON);
    }

    @Test
    public void testButtonsPanelContainsLoadButton()
    {
        testButtonsPanelContainsButton(InstructionsPanel.LOAD_BUTTON);
    }

    private void testButtonsPanelContainsButton(String buttonName)
    {
        JPanel panel = InstructionsPanel.getButtonsPanel(mockAssembler);

        SwingTestUtils.getChildNamed(panel, buttonName, JButton.class);
    }
}
