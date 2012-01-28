package assembler.simulator.mips;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import assembler.simulator.Register;

public class TwoRegisterArgumentWithImmediateInstructionParsingStrategy
    extends BaseParsingStrategy
{
    private static final Pattern PARSE_PATTERN = Pattern.compile("(.+?)"
        + "\\s(" + REGISTER_REGEX + "),\\s*(" + REGISTER_REGEX + "),\\s*("
        + "\\w+" + ")");

    private static final Class<?>[] CONSTRUCTOR_ARGUMENT_TYPES = new Class<?>[]
    { String.class, Register.class, String.class, Register.class };

    private static final int OPERAND_ONE_GROUP = 2;

    private static final int IMMEDIATE_GROUP = 4;

    private static final int OPERAND_TWO_GROUP = 3;

    private static final List<Class<? extends BaseInstruction>> PARSEABLE_CLASSES = new ArrayList<Class<? extends BaseInstruction>>();
    static
    {
        PARSEABLE_CLASSES.add(BranchOnEqualInstruction.class);
        PARSEABLE_CLASSES.add(BranchOnNotEqualInstruction.class);
    }

    @Override
    Object[] getConstructorArguments(Matcher matcher, String instruction)
    {
        Register operandOne = MipsRegister.fromName(matcher.group(OPERAND_ONE_GROUP));
        Register operandTwo = MipsRegister.fromName(matcher.group(OPERAND_TWO_GROUP));
        String immediate = matcher.group(IMMEDIATE_GROUP);
        return new Object[]
        { instruction, operandTwo, immediate, operandOne };
    }

    @Override
    Pattern getParsePattern()
    {
        return PARSE_PATTERN;
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
}
