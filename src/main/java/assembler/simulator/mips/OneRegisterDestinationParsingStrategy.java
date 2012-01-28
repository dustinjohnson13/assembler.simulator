package assembler.simulator.mips;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import assembler.simulator.Register;

public class OneRegisterDestinationParsingStrategy extends
    BaseParsingStrategy
{
    private static final Pattern PARSE_PATTERN = Pattern.compile("(.+?)\\d?"
        + "\\s(" + REGISTER_REGEX + ")");

    private static final int DESTINATION_GROUP = 2;

    private static final Class<?>[] CONSTRUCTOR_ARGUMENT_TYPES = new Class<?>[]
    { String.class, Register.class };

    private static final List<Class<? extends BaseInstruction>> PARSEABLE_CLASSES = new ArrayList<Class<? extends BaseInstruction>>();
    static
    {
        PARSEABLE_CLASSES.add(MoveFromHighInstruction.class);
        PARSEABLE_CLASSES.add(MoveFromLowInstruction.class);
    }

    @Override
    Class<?>[] getConstructorArgumentTypes()
    {
        return CONSTRUCTOR_ARGUMENT_TYPES;
    }

    @Override
    Object[] getConstructorArguments(Matcher matcher, String instruction)
    {
        Register destination = MipsRegister.fromName(matcher.group(DESTINATION_GROUP));
        return new Object[]
        { instruction, destination };
    }

    @Override
    Pattern getParsePattern()
    {
        return PARSE_PATTERN;
    }

    @Override
    public Collection<Class<? extends BaseInstruction>> getParseableClasses()
    {
        return PARSEABLE_CLASSES;
    }
}
