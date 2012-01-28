package assembler.simulator.mips;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class JumpInstructionParsingStrategy extends BaseParsingStrategy
{
    private static final Pattern PARSE_PATTERN = Pattern.compile("(.+)?\\s(.+)");

    private static final Class<?>[] CONSTRUCTOR_ARGUMENT_TYPES = new Class<?>[]
    { String.class, String.class };

    private static final List<Class<? extends BaseInstruction>> PARSEABLE_CLASSES = new ArrayList<Class<? extends BaseInstruction>>();
    static
    {
        PARSEABLE_CLASSES.add(JumpAndLinkInstruction.class);
        PARSEABLE_CLASSES.add(JumpRegisterInstruction.class);
        PARSEABLE_CLASSES.add(JumpInstruction.class);
    }

    @Override
    Object[] getConstructorArguments(Matcher matcher, String instruction)
    {
        String address = matcher.group(2);
        return new Object[]
        { instruction, address };
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
