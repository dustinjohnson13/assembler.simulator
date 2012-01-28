package assembler.simulator.mips;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import assembler.simulator.Register;

abstract class OneRegisterArgumentWithImmediateParsingStrategy<OPERAND_TWO_TYPE>
    extends BaseParsingStrategy
{
    static final OneRegisterArgumentWithImmediateParsingStrategy<Integer> INTEGER = new IntegerImmediateParsingStrategy();

    static final OneRegisterArgumentWithImmediateParsingStrategy<String> STRING = new StringImmediateParsingStrategy();

    private static final int DESTINATION_GROUP = 2;

    private static final int OPERAND_ONE_GROUP = 3;

    private static final int IMMEDIATE_OPERAND_GROUP = 4;

    private final Pattern parsePattern;

    OneRegisterArgumentWithImmediateParsingStrategy(Pattern parsePattern)
    {
        this.parsePattern = parsePattern;
    }

    @Override
    Object[] getConstructorArguments(Matcher matcher, String instruction)
    {
        Register destination = MipsRegister.fromName(matcher.group(DESTINATION_GROUP));
        Register operandOne = MipsRegister.fromName(matcher.group(OPERAND_ONE_GROUP));
        OPERAND_TWO_TYPE operandTwo = parseOperandTwo(matcher.group(IMMEDIATE_OPERAND_GROUP));

        return new Object[]
        { instruction, destination, operandTwo, operandOne };
    }

    @Override
    Pattern getParsePattern()
    {
        return parsePattern;
    }

    protected abstract OPERAND_TWO_TYPE parseOperandTwo(String string);

    private static final class IntegerImmediateParsingStrategy extends
        OneRegisterArgumentWithImmediateParsingStrategy<Integer>
    {
        private static final String FINISH_GROUP_COMMA_SPACES_OPEN_GROUP = "),\\s*(";

		private static final Pattern INTEGER_PARSE_PATTERN = Pattern.compile("(.+?)"
            + "\\s("
            + REGISTER_REGEX
            + FINISH_GROUP_COMMA_SPACES_OPEN_GROUP
            + REGISTER_REGEX
            + FINISH_GROUP_COMMA_SPACES_OPEN_GROUP + "-?\\d+" + ")");

        protected static final Class<?>[] CONSTRUCTOR_ARGUMENT_TYPES = new Class<?>[]
        { String.class, Register.class, int.class, Register.class };

        private static final List<Class<? extends BaseInstruction>> PARSEABLE_CLASSES = new ArrayList<Class<? extends BaseInstruction>>();
        static
        {
            PARSEABLE_CLASSES.add(AddImmediateInstruction.class);
            PARSEABLE_CLASSES.add(AddImmediateUnsignedInstruction.class);
            PARSEABLE_CLASSES.add(SetOnLessThanImmediateInstruction.class);
            PARSEABLE_CLASSES.add(AndImmediateInstruction.class);
            PARSEABLE_CLASSES.add(OrImmediateInstruction.class);
            PARSEABLE_CLASSES.add(ShiftLeftLogicalInstruction.class);
            PARSEABLE_CLASSES.add(ShiftRightLogicalInstruction.class);
            PARSEABLE_CLASSES.add(ShiftRightArithmeticInstruction.class);
        }

        IntegerImmediateParsingStrategy()
        {
            super(INTEGER_PARSE_PATTERN);
        }

        @Override
        protected Integer parseOperandTwo(String string)
        {
            return Integer.parseInt(string);
        }

        @Override
        Class<?>[] getConstructorArgumentTypes()
        {
            return CONSTRUCTOR_ARGUMENT_TYPES;
        }

        @Override
        public Collection<Class<? extends BaseInstruction>> getParseableClasses()
        {
            return PARSEABLE_CLASSES;
        }
    };

    private static final class StringImmediateParsingStrategy extends
        OneRegisterArgumentWithImmediateParsingStrategy<String>
    {
        private static final Pattern STRING_PARSE_PATTERN = Pattern.compile("(.+?)"
            + "\\s("
            + REGISTER_REGEX
            + "),\\s*("
            + REGISTER_REGEX
            + "),\\s*(" + "-?\\w+" + ")");

        protected static final Class<?>[] CONSTRUCTOR_ARGUMENT_TYPES = new Class<?>[]
        { String.class, Register.class, String.class, Register.class };

        private static final List<Class<? extends BaseInstruction>> PARSEABLE_CLASSES = new ArrayList<Class<? extends BaseInstruction>>();

        StringImmediateParsingStrategy()
        {
            super(STRING_PARSE_PATTERN);
        }

        @Override
        protected String parseOperandTwo(String string)
        {
            return string;
        }

        @Override
        Class<?>[] getConstructorArgumentTypes()
        {
            return CONSTRUCTOR_ARGUMENT_TYPES;
        }

        @Override
        public Collection<Class<? extends BaseInstruction>> getParseableClasses()
        {
            return PARSEABLE_CLASSES;
        }
    };

}
