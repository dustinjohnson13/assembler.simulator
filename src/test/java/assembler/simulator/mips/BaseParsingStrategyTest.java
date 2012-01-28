package assembler.simulator.mips;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class BaseParsingStrategyTest
{
    @Test(expected = IllegalStateException.class)
    public void testExceptionsAreWrappedInAppropriateTypeOfRuntimeException()
    {
        new BaseParsingStrategy() {

            @Override
            Pattern getParsePattern()
            {
                return Pattern.compile(".*");
            }

            @Override
            Object[] getConstructorArguments(Matcher matcher,
                String instructionLine)
            {
                return new Object[0];
            }

            @Override
            Class<?>[] getConstructorArgumentTypes()
            {
                return new Class<?>[0];
            }

            @Override
            public Collection<Class<? extends BaseInstruction>> getParseableClasses()
            {
                return Collections.emptyList();
            }
        }.parseInstruction(AddInstruction.class, "fdsafsdaf");
    }
}
