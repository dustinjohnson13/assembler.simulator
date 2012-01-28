package assembler.simulator.mips;

import java.util.Collection;

public interface ParsingStrategy {
	String REGISTER_REGEX = "\\$\\w\\w";

	String NUMBER_ARGUMENT = "\\d+";

	<T extends BaseInstruction> T parseInstruction(Class<T> instructionClass,
			String instruction);

	Collection<Class<? extends BaseInstruction>> getParseableClasses();
}
