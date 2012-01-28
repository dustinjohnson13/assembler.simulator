package assembler.simulator.mips;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import assembler.simulator.Register;

public class DataTransferInstructionParsingStrategy extends
    BaseParsingStrategy
{
    private static final Pattern PARSE_PATTERN = Pattern.compile("(.+?)"
        + "\\s(" + REGISTER_REGEX + "),\\s*(\\d+)\\((" + REGISTER_REGEX
        + ")\\)");

    private static final int DATA_REGISTER_GROUP = 2;

    private static final int ADDRESS_OFFSET_GROUP = 3;

    private static final int ADDRESS_REGISTER_GROUP = 4;

    private static final Class<?>[] CONSTRUCTOR_ARGUMENT_TYPES = new Class<?>[]
    { String.class, Register.class, int.class, Register.class };

    private static final List<Class<? extends BaseInstruction>> PARSEABLE_CLASSES = new ArrayList<Class<? extends BaseInstruction>>();
    static
    {
        PARSEABLE_CLASSES.add(LoadWordInstruction.class);
        PARSEABLE_CLASSES.add(LoadDoubleWordInstruction.class);
        PARSEABLE_CLASSES.add(StoreDoubleWordInstruction.class);
        PARSEABLE_CLASSES.add(StoreHalfWordInstruction.class);
        PARSEABLE_CLASSES.add(StoreByteInstruction.class);
        PARSEABLE_CLASSES.add(LoadHalfWordUnsignedInstruction.class);
        PARSEABLE_CLASSES.add(LoadHalfWordInstruction.class);
        PARSEABLE_CLASSES.add(LoadByteUnsignedInstruction.class);
        PARSEABLE_CLASSES.add(LoadByteInstruction.class);
        PARSEABLE_CLASSES.add(StoreWordInstruction.class);
    }

    @Override
    Object[] getConstructorArguments(Matcher matcher, String instruction)
    {
        Register dataRegister = MipsRegister.fromName(matcher.group(DATA_REGISTER_GROUP));
        Register addressRegister = MipsRegister.fromName(matcher.group(ADDRESS_REGISTER_GROUP));
        int addressOffset = Integer.parseInt(matcher.group(ADDRESS_OFFSET_GROUP));

        return new Object[]
        { instruction, dataRegister, addressOffset, addressRegister };
    }

    @Override
    Class<?>[] getConstructorArgumentTypes()
    {
        return CONSTRUCTOR_ARGUMENT_TYPES;
    }

    @Override
    Pattern getParsePattern()
    {
        return PARSE_PATTERN;
    }

    @Override
    public List<Class<? extends BaseInstruction>> getParseableClasses()
    {
        return PARSEABLE_CLASSES;
    }
}
