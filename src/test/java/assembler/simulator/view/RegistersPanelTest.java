package assembler.simulator.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import assembler.simulator.Assembler;
import assembler.simulator.BaseMockAssemblerTest;
import assembler.simulator.MockBaseAssembler;
import assembler.simulator.RegisterViewMode;

@RunWith(JMock.class)
public class RegistersPanelTest extends BaseMockAssemblerTest
{
    private final Mockery mockContext = new JUnit4Mockery();

    private final Assembler mockAssembler = mockContext.mock(Assembler.class);

    @Test
    public void testResetButtonCallsResetOnAssembler()
    {
        JButton button = RegistersPanel.getResetButton(mockAssembler);

        mockContext.checking(new Expectations() {
            {
                oneOf(mockAssembler).resetRegisters();
            }
        });

        button.doClick();
    }

    @Test
    public void testRegistersTableHasCorrectNumberOfColumns()
    {
        final JTable table = RegistersPanel.getRegistersTable(assembler);
        assertEquals("Incorrect number of columns for the table!", 2,
            table.getModel().getColumnCount());
    }

    @Test
    public void testRegistersTableShowsRegistersInOrder()
    {
        // Set the index as the register value
        for (int i = 0; i < REGISTERS.length; i++)
        {
            assembler.setRegisterValue(REGISTERS[i], i);
        }

        final JTable table = RegistersPanel.getRegistersTable(assembler);
        assertEquals(
            "Incorrect number of registers were added to the table!",
            table.getModel().getRowCount(), REGISTERS.length);

        for (int i = 0; i < REGISTERS.length; i++)
        {
            assertEquals("The table did not show the correct register name!",
                REGISTERS[i].getName(), table.getModel().getValueAt(i, 0));

            assertEquals(
                "The table value column did not match the value in the register!",
                assembler.getRegisterValue(REGISTERS[i]) + "",
                table.getModel().getValueAt(i, 1));
        }
    }

    @Test
    public void testRegistersTableUsesRegisterViewModeToDisplayRegisterValues()
    {
        final RegisterViewMode mockRegisterViewMode = mockContext.mock(RegisterViewMode.class);
        assembler.setRegisterViewMode(mockRegisterViewMode);

        final int[] registerValue = new int[REGISTERS.length];

        // Set the index as the register value
        for (int i = 0; i < REGISTERS.length; i++)
        {
            registerValue[i] = i;
            assembler.setRegisterValue(REGISTERS[i], registerValue[i]);
        }

        for (int i = 0; i < REGISTERS.length; i++)
        {
            final int j = i;
            mockContext.checking(new Expectations() {
                {
                    oneOf(mockRegisterViewMode).format(registerValue[j]);
                }
            });
        }

        RegistersPanel.getRegistersTable(assembler);
    }

    @Test
    public void testRegistersTableHasRadioButtonsForEachRegisterViewMode()
    {
        JPanel panel = RegistersPanel.getRegisterViewingModePanel(assembler);

        for (RegisterViewMode viewMode : assembler.getRegisterViewModes())
        {
            SwingTestUtils.getChildNamed(panel, viewMode.toString(),
                JRadioButton.class);
        }
    }

    @Test
    public void testRegisterViewRadioButtonIsSetSelected()
    {
        JPanel panel = RegistersPanel.getRegisterViewingModePanel(assembler);
        JRadioButton button = SwingTestUtils.getChildNamed(panel,
            MockBaseAssembler.DEFAULT_REGISTER_VIEW_MODE.toString(),
            JRadioButton.class);

        assertTrue("The radio button should have been selected!",
            button.isSelected());
    }

    @Test
    public void testRegisterViewRadioButtonSetSelectionSetsViewModeOnAssembler()
    {
        final RegisterViewMode defaultViewMode = mockContext.mock(
            RegisterViewMode.class, "defaultViewMode");
        final RegisterViewMode nonDefaultViewMode = mockContext.mock(
            RegisterViewMode.class, "nonDefaultViewMode");
        mockContext.checking(new Expectations() {
            {
                allowing(defaultViewMode);
                allowing(nonDefaultViewMode);
                allowing(mockAssembler).getRegisterViewModes();
                will(returnValue(Arrays.asList(defaultViewMode,
                    nonDefaultViewMode)));
                allowing(mockAssembler).getRegisterViewMode();
                will(returnValue(defaultViewMode));
                oneOf(mockAssembler).setRegisterViewMode(nonDefaultViewMode);
            }
        });

        JPanel panel = RegistersPanel.getRegisterViewingModePanel(mockAssembler);
        JRadioButton button = SwingTestUtils.getChildNamed(panel,
            nonDefaultViewMode.toString(), JRadioButton.class);
        button.doClick();
    }

    @Test
    public void testRegisterViewRadioButtonsCanOnlyBeSelectedOneAtATime()
    {
        Collection<RegisterViewMode> registerViewModes = assembler.getRegisterViewModes();
        for (RegisterViewMode viewMode : registerViewModes)
        {
            JPanel panel = RegistersPanel.getRegisterViewingModePanel(assembler);
            JRadioButton button = SwingTestUtils.getChildNamed(panel,
                viewMode.toString(), JRadioButton.class);
            button.setSelected(true);

            for (RegisterViewMode anotherViewMode : registerViewModes)
            {
                if (anotherViewMode == viewMode)
                {
                    continue;
                }

                JRadioButton anotherButton = SwingTestUtils.getChildNamed(
                    panel, anotherViewMode.toString(), JRadioButton.class);
                assertFalse(
                    "Only one button should be able to be selected at a time!",
                    anotherButton.isSelected());
            }
        }
    }
}
