package assembler.simulator.mips;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import assembler.simulator.Register;

public class ThreeRegisterInstructionParsingStrategy extends
    BaseParsingStrategy
{
    private static final Pattern PARSE_PATTERN = Pattern.compile("(.+?"
        + ")\\d?\\s((" + REGISTER_REGEX + "),\\s*)?(" + REGISTER_REGEX
        + "),\\s*(" + REGISTER_REGEX + ")");

    private static final int DESTINATION_GROUP = 3;

    private static final int OPERAND_ONE_GROUP = 4;

    private static final int OPERAND_TWO_GROUP = 5;

    private static final Class<?>[] CONSTRUCTOR_ARGUMENT_TYPES = new Class<?>[]
    { String.class, Register.class, Register.class, Register.class };

    private static final List<Class<? extends BaseInstruction>> PARSEABLE_CLASSES = new ArrayList<Class<? extends BaseInstruction>>();
    static
    {
        PARSEABLE_CLASSES.add(AddUnsignedInstruction.class);
        PARSEABLE_CLASSES.add(AddInstruction.class);
        PARSEABLE_CLASSES.add(SubUnsignedInstruction.class);
        PARSEABLE_CLASSES.add(SubInstruction.class);
        PARSEABLE_CLASSES.add(SetOnLessThanInstruction.class);
        PARSEABLE_CLASSES.add(AndInstruction.class);
        PARSEABLE_CLASSES.add(OrInstruction.class);
        PARSEABLE_CLASSES.add(XorInstruction.class);
        PARSEABLE_CLASSES.add(NorInstruction.class);
        PARSEABLE_CLASSES.add(MultUnsignedInstruction.class);
        PARSEABLE_CLASSES.add(MultInstruction.class);
        PARSEABLE_CLASSES.add(DivUnsignedInstruction.class);
        PARSEABLE_CLASSES.add(DivInstruction.class);
        PARSEABLE_CLASSES.add(MoveFromControlRegisterInstruction.class);
    }

    @Override
    Object[] getConstructorArguments(Matcher matcher, String instruction)
    {
        Register destination = null;
        String destinationGroup = matcher.group(DESTINATION_GROUP);

        if (destinationGroup != null)
        {
            destination = MipsRegister.fromName(destinationGroup);
        }

        Register operandOne = MipsRegister.fromName(matcher.group(OPERAND_ONE_GROUP));
        Register operandTwo = MipsRegister.fromName(matcher.group(OPERAND_TWO_GROUP));

        return new Object[]
        { instruction, operandOne, operandTwo, destination };
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
