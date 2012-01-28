package assembler.simulator.view;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import assembler.simulator.Assembler;

public class SimulatorView implements assembler.simulator.Observer
{
    private final Assembler assembler;

    private final JFrame frame;

    public static SimulatorView newInstance(Assembler assembler)
    {
        SimulatorView simulatorView = new SimulatorView(assembler);
        assembler.registerObserver(simulatorView);
        return simulatorView;
    }

    private SimulatorView(Assembler assembler)
    {
        this(assembler, new JFrame("Assembler Simulator"));

    }

    SimulatorView(Assembler assembler, JFrame frame)
    {
        this.assembler = assembler;
        this.frame = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void update()
    {
        Container contentPane = frame.getContentPane();

        contentPane.removeAll();
        contentPane.setLayout(new FlowLayout());
        contentPane.add(new RegistersPanel(assembler));
        contentPane.add(new InstructionsPanel(assembler));

        frame.pack();
        frame.setVisible(true);
    }

}
