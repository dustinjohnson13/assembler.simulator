package assembler.simulator.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import assembler.simulator.Assembler;
import assembler.simulator.Instruction;

class InstructionsPanel extends JPanel {
	private static final long serialVersionUID = 2610254313890644268L;

	static final String RUN_BUTTON = "runButton";

	static final String STEP_BUTTON = "stepButton";

	static final String CLEAR_BUTTON = "clearButton";

	static final String INSTRUCTIONS_LIST_SCROLL_PANE = "instructionsListScrollPane";

	static final String INSTRUCTIONS_LIST = "instructionsList";

	static final String ADD_INSTRUCTION_TEXT_FIELD = "addInstructionTextField";

	static final String ADD_INSTRUCTION_BUTTON = "addInstructionButton";

	static final String LOAD_BUTTON = "loadInstructionsButton";

	static FileChooserStrategy fileChooserStrategy = new PopupFileChooserStrategy();

	InstructionsPanel(Assembler assembler) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(getInstructionsListScrollPane(assembler));
		this.add(getButtonsPanel(assembler));
		this.add(getAddInstructionPanel(assembler));
	}

	static JPanel getAddInstructionPanel(final Assembler assembler) {
		JPanel panel = new JPanel();

		final JTextField textField = new JTextField(20);
		textField.setName(ADD_INSTRUCTION_TEXT_FIELD);
		panel.add(textField);

		final JButton addInstructionButton = new JButton("Add");
		addInstructionButton.setName(ADD_INSTRUCTION_BUTTON);
		addInstructionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				assembler.processLine(textField.getText());
				textField.setText("");
			}
		});
		panel.add(addInstructionButton);

		return panel;
	}

	static JPanel getButtonsPanel(Assembler assembler) {
		JPanel panel = new JPanel();
		panel.add(getClearButton(assembler));
		panel.add(getRunButton(assembler));
		panel.add(getStepButton(assembler));
		panel.add(getLoadButton(assembler));
		return panel;
	}

	static JButton getStepButton(final Assembler assembler) {
		JButton stepButton = new JButton("Step");
		stepButton.setName(STEP_BUTTON);
		stepButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				assembler.runInstruction();
			}
		});

		return stepButton;
	}

	static JButton getRunButton(final Assembler assembler) {
		JButton runButton = new JButton("Run");
		runButton.setName(RUN_BUTTON);
		runButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				assembler.runInstructions();
			}
		});

		return runButton;
	}

	static JButton getClearButton(final Assembler assembler) {
		JButton clearButton = new JButton("Clear");
		clearButton.setName(CLEAR_BUTTON);
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				assembler.clearInstructions();
			}
		});

		return clearButton;
	}

	static JButton getLoadButton(final Assembler assembler) {
		final JButton loadButton = new JButton("Load");
		loadButton.setName(LOAD_BUTTON);
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File selectedFile = fileChooserStrategy.getFile(loadButton);

				if (selectedFile != null) {
					assembler.loadInstructions(selectedFile);
				}
			}
		});

		return loadButton;
	}

	private JScrollPane getInstructionsListScrollPane(final Assembler assembler) {
		JList list = getInstructionsList(assembler);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setName(INSTRUCTIONS_LIST_SCROLL_PANE);

		return listScroller;
	}

	static JList getInstructionsList(final Assembler assembler) {
		Collection<Instruction> instructions = assembler.getInstructions();
		int size = instructions.size();
		JList list = new JList(instructions.toArray(new Instruction[size]));

		list.setName(INSTRUCTIONS_LIST);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setSelectedIndex(assembler.getProgramCounter());
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				assembler.setProgramCounter(arg0.getLastIndex());
			}
		});
		return list;
	}

	interface FileChooserStrategy {
		File getFile(Component component);
	}

	static class PopupFileChooserStrategy implements FileChooserStrategy {
		@Override
		public File getFile(Component component) {
			final JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(component);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				return fc.getSelectedFile();
			}

			return null;
		}
	}
}
