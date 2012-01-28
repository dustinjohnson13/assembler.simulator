package assembler.simulator.mips;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class BaseParsingStrategy implements ParsingStrategy
{
    private static final int PARSED_OPERATION_GROUP = 1;

    @Override
    public final <T extends BaseInstruction> T parseInstruction(
        Class<T> instructionClass, String instruction)
    {
        Matcher matcher = getParsePattern().matcher(instruction);

        if (matcher.find())
        {
            try
            {
                String parsedOperation = matcher.group(PARSED_OPERATION_GROUP);

                Method expectedOperationMethod = instructionClass.getMethod("getOperationKey");
                Object expectedOperation = expectedOperationMethod.invoke(
                    null, (Object[]) null);

                if (!expectedOperation.equals(parsedOperation))
                {
                    throw new IllegalArgumentException("[" + instruction
                        + "] did not contain operation [" + expectedOperation
                        + "]!");
                }

                Object[] constructorArguments = getConstructorArguments(
                    matcher, instruction);
                Class<?>[] constructorArgumentTypes = getConstructorArgumentTypes();

                Constructor<T> ctr = instructionClass.getConstructor(constructorArgumentTypes);
                return ctr.newInstance(constructorArguments);
            }
            catch (IllegalArgumentException iae)
            {
                throw iae;
            }
            catch (Exception e)
            {
                throw new IllegalStateException(e);
            }
        }
        else
        {
            throw new IllegalArgumentException("Cannot parse ["
                + instruction + "]!");
        }
    }

    abstract Class<?>[] getConstructorArgumentTypes();

    abstract Object[] getConstructorArguments(Matcher matcher,
        String instruction);

    abstract Pattern getParsePattern();
}
