package assembler.simulator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ByteArrayConverterTest
{
    @Test
    public void testConvertIntBackAndForth()
    {
        final int testValue = Integer.MIN_VALUE;
        assertEquals(
            testValue,
            ByteArrayConverter.intFromByteArray(ByteArrayConverter.convertToByteArray(testValue)));
    }

    @Test
    public void testConvertLongBackAndForth()
    {
        final long testValue = Long.MIN_VALUE;
        assertEquals(
            testValue,
            ByteArrayConverter.longFromByteArray(ByteArrayConverter.convertToByteArray(testValue)));
    }

    @Test
    public void testSplitDoubleWordIntoTwoWords()
    {
        final long doubleWord = 17823762763234L;
        // 00000000000000000001000000110101 (upper bits) = 4149
        // 11101011000011000100110111100010 (lower bits) = -351515166
        // 00010100111100111011001000011110 (two's complement) = 351515166

        int[] results = ByteArrayConverter.splitDoubleWordIntoTwoWords(doubleWord);
        assertEquals("Incorrect number of results returned!", 2,
            results.length);
        assertEquals(4149, results[0]);
        assertEquals(-351515166, results[1]);
    }

    @Test
    public void testCombineTwoWordsIntoDoubleWord()
    {
        assertEquals(
            17823762763234L,
            ByteArrayConverter.combineTwoWordsIntoDoubleWord(4149, -351515166));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSplitByteArrayIntoMultipleThrowsExceptionWhenNotEvenlyDivisible()
    {
        ByteArrayConverter.splitByteArrayIntoMultiple(new byte[6], 4);
    }
}
