package assembler.simulator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import assembler.simulator.Assembler;
import assembler.simulator.Register;
import assembler.simulator.RegisterViewMode;

class RegistersPanel extends JPanel {

	private static final int REGISTER_NAME_COLUMN_MAX_WIDTH = 75;
	private static final long serialVersionUID = 630698208081371351L;

	RegistersPanel(Assembler assembler) {
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);

		this.setLayout(layout);
		this.add(getRegisterViewingModePanel(assembler));
		this.add(getRegistersTable(assembler));
		this.add(getButtonsPanel(assembler));
	}

	private JPanel getButtonsPanel(final Assembler assembler) {
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(getResetButton(assembler));

		return buttonsPanel;
	}

	static JButton getResetButton(final Assembler assembler) {
		JButton button = new JButton("Reset");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				assembler.resetRegisters();
			}
		});

		return button;
	}

	static JTable getRegistersTable(Assembler assembler) {
		final RegisterViewMode registerViewMode = assembler
				.getRegisterViewMode();
		Collection<Register> registers = assembler.getRegisters();
		Object[][] data = new Object[registers.size()][];

		int idx = 0;
		for (Register register : registers) {
			data[idx++] = new Object[] {
					register.getName(),
					registerViewMode.format(assembler
							.getRegisterValue(register)) };
		}

		JTable table = new JTable(new RegistersTableModel(data, new Object[] {
				"One", "Two" }));
		table.getColumnModel().getColumn(0)
				.setMaxWidth(REGISTER_NAME_COLUMN_MAX_WIDTH);
		table.setDefaultRenderer(table.getColumnClass(1),
				new RightAlignRenderer());
		return table;
	}

	static JPanel getRegisterViewingModePanel(Assembler assembler) {
		JPanel panel = new JPanel();
		ButtonGroup buttonGroup = new ButtonGroup();

		Collection<RegisterViewMode> registerViewModes = assembler
				.getRegisterViewModes();
		int numberOfViewModes = registerViewModes.size();
		Iterator<RegisterViewMode> iter = registerViewModes.iterator();
		JRadioButton[] viewModeButtons = new JRadioButton[numberOfViewModes];
		for (int i = 0; i < numberOfViewModes; i++) {
			RegisterViewMode viewMode = iter.next();
			viewModeButtons[i] = getRegisterViewingModeButton(viewMode,
					assembler);
		}

		for (JRadioButton viewModeButton : viewModeButtons) {
			buttonGroup.add(viewModeButton);
			panel.add(viewModeButton);
		}

		return panel;
	}

	private static JRadioButton getRegisterViewingModeButton(
			final RegisterViewMode registerViewMode, final Assembler assembler) {
		JRadioButton button = new JRadioButton(
				registerViewMode.getDisplayString());
		button.setName(registerViewMode.toString());
		button.setSelected(assembler.getRegisterViewMode().toString() == button
				.getName());
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				assembler.setRegisterViewMode(registerViewMode);
			}
		});

		return button;
	}

	private static final class RegistersTableModel extends DefaultTableModel {
		private static final long serialVersionUID = 6460552400283552175L;

		private RegistersTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			if (columnIndex == 1) {
				return String.class;
			} else {
				return super.getColumnClass(columnIndex);
			}
		}
	}

	private static class RightAlignRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1602309732512602495L;

		{
			this.setHorizontalAlignment(RIGHT);
		}
	}
}
