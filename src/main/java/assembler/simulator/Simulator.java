package assembler.simulator;

import assembler.simulator.mips.MipsAssembler;
import assembler.simulator.view.SimulatorView;

public final class Simulator {
	private static final Assembler ASSEMBLER = new MipsAssembler();

	private Simulator() {
		// No construction
	}

	public static void main(String[] args) {
		SimulatorView.newInstance(ASSEMBLER);
	}
}
