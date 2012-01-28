package assembler.simulator.mips;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import assembler.simulator.Instruction;
import assembler.simulator.InstructionFactory;

class MipsInstructionFactory implements InstructionFactory {
	private static final Map<String, Class<? extends BaseInstruction>> OPERATION_KEY_TO_PARSEABLE_CLASS;
	private static final Map<String, ParsingStrategy> PARSEABLE_CLASS_TO_PARSER;
	static {
		ParsingStrategy[] parsers = new ParsingStrategy[] {
				new JumpInstructionParsingStrategy(),
				new DataTransferInstructionParsingStrategy(),
				new OneRegisterDestinationParsingStrategy(),
				new OneRegisterDestinationWithImmediateParsingStrategy(),
				new TwoRegisterArgumentWithImmediateInstructionParsingStrategy(),
				new ThreeRegisterInstructionParsingStrategy(),
				OneRegisterArgumentWithImmediateParsingStrategy.INTEGER,
				OneRegisterArgumentWithImmediateParsingStrategy.STRING };

		Map<String, Class<? extends BaseInstruction>> operationKeyToParseableClass = new HashMap<String, Class<? extends BaseInstruction>>();
		Map<String, ParsingStrategy> parseableClassNameToParser = new HashMap<String, ParsingStrategy>();
		for (ParsingStrategy parsingStrategy : parsers) {
			for (Class<? extends BaseInstruction> parseableClass : parsingStrategy
					.getParseableClasses()) {
				parseableClassNameToParser.put(parseableClass.getName(),
						parsingStrategy);
				operationKeyToParseableClass.put(
						getOperationKey(parseableClass), parseableClass);
			}
		}

		PARSEABLE_CLASS_TO_PARSER = Collections
				.unmodifiableMap(parseableClassNameToParser);
		OPERATION_KEY_TO_PARSEABLE_CLASS = Collections
				.unmodifiableMap(operationKeyToParseableClass);
	}

	@Override
	public Instruction parseInstruction(String lineToParse) {
		InstructionFields fields = new InstructionFields(lineToParse);
		String instruction = fields.wholeInstructionLine;
		String label = fields.label;

		Class<? extends BaseInstruction> parseClass = OPERATION_KEY_TO_PARSEABLE_CLASS
				.get(fields.operationKey);

		BaseInstruction parsedInstruction = PARSEABLE_CLASS_TO_PARSER.get(
				parseClass.getName()).parseInstruction(parseClass, instruction);

		parsedInstruction.setLabel(label);

		return parsedInstruction;
	}

	private static String getOperationKey(Class<?> parseableClass) {
		try {
			Method operationKeyMethod = parseableClass
					.getMethod("getOperationKey");
			String operationKey = (String) operationKeyMethod.invoke(null,
					(Object[]) null);
			return operationKey;
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	private static final class InstructionFields {
		private static final int WHOLE_INSTRUCTION_LINE_GROUP = 3;

		private static final int OPERATION_KEY_GROUP = 4;

		private static final Pattern INSTRUCTION_PATTERN = Pattern
				.compile("((.+):)?\\s*((.+?)\\d?\\s.*)");

		private final String label;

		private final String wholeInstructionLine;

		private final String operationKey;

		private InstructionFields(String line) {
			Matcher matcher = INSTRUCTION_PATTERN.matcher(line);

			if (matcher.find()) {
				label = matcher.group(2);
				wholeInstructionLine = matcher.group(
						WHOLE_INSTRUCTION_LINE_GROUP).trim();
				operationKey = matcher.group(OPERATION_KEY_GROUP);
			} else {
				throw new IllegalArgumentException(
						"Unable to parse the instruction line [" + line + "]!");
			}
		}
	}
}
