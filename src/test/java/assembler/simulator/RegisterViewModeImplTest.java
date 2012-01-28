package assembler.simulator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RegisterViewModeImplTest
{
    private static final int SMALL_POSITIVE = 5;
    private static final int LARGE_POSITIVE = 12385432;
    private static final int SMALL_NEGATIVE = -10;
    private static final int LARGE_NEGATIVE = -12385432;

    @Test
    public void testHexValueForSmallPositive()
    {
        testRegisterViewModeFormattedValues(RegisterViewModeImpl.HEX,
            "00000005", SMALL_POSITIVE);
    }

    @Test
    public void testHexValueForLargePositive()
    {
        testRegisterViewModeFormattedValues(RegisterViewModeImpl.HEX,
            "00BCFC98", LARGE_POSITIVE);
    }

    @Test
    public void testHexValueForSmallNegative()
    {
        testRegisterViewModeFormattedValues(RegisterViewModeImpl.HEX,
            "FFFFFFF6", SMALL_NEGATIVE);
    }

    @Test
    public void testHexValueForLargeNegative()
    {
        testRegisterViewModeFormattedValues(RegisterViewModeImpl.HEX,
            "FF430368", LARGE_NEGATIVE);
    }

    @Test
    public void testBitValueForSmallPositive()
    {
        testRegisterViewModeFormattedValues(RegisterViewModeImpl.BIT,
            "00000000000000000000000000000101", SMALL_POSITIVE);
    }

    @Test
    public void testBitValueForLargePositive()
    {
        testRegisterViewModeFormattedValues(RegisterViewModeImpl.BIT,
            "00000000101111001111110010011000", LARGE_POSITIVE);
    }

    @Test
    public void testBitValueForSmallNegative()
    {
        testRegisterViewModeFormattedValues(RegisterViewModeImpl.BIT,
            "11111111111111111111111111110110", SMALL_NEGATIVE);
    }

    @Test
    public void testBitValueForLargeNegative()
    {
        testRegisterViewModeFormattedValues(RegisterViewModeImpl.BIT,
            "11111111010000110000001101101000", LARGE_NEGATIVE);
    }

    @Test
    public void testSignedIntegerValueForSmallPositive()
    {
        testRegisterViewModeFormattedValues(
            RegisterViewModeImpl.SIGNED_INTEGER, "5", SMALL_POSITIVE);
    }

    @Test
    public void testSignedIntegerValueForLargePositive()
    {
        testRegisterViewModeFormattedValues(
            RegisterViewModeImpl.SIGNED_INTEGER, "12,385,432", LARGE_POSITIVE);
    }

    @Test
    public void testSignedIntegerValueForSmallNegative()
    {
        testRegisterViewModeFormattedValues(
            RegisterViewModeImpl.SIGNED_INTEGER, "-10", SMALL_NEGATIVE);
    }

    @Test
    public void testSignedIntegerValueForLargeNegative()
    {
        testRegisterViewModeFormattedValues(
            RegisterViewModeImpl.SIGNED_INTEGER, "-12,385,432",
            LARGE_NEGATIVE);
    }

    @Test
    public void testUnsignedIntegerValueForSmallPositive()
    {
        testRegisterViewModeFormattedValues(
            RegisterViewModeImpl.UNSIGNED_INTEGER, "5", SMALL_POSITIVE);
    }

    @Test
    public void testUnsignedIntegerValueForLargePositive()
    {
        testRegisterViewModeFormattedValues(
            RegisterViewModeImpl.UNSIGNED_INTEGER, "12,385,432",
            LARGE_POSITIVE);
    }

    @Test
    public void testUnsignedIntegerValueForSmallNegative()
    {
        testRegisterViewModeFormattedValues(
            RegisterViewModeImpl.UNSIGNED_INTEGER, "4,294,967,286",
            SMALL_NEGATIVE);
    }

    @Test
    public void testUnsignedIntegerValueForLargeNegative()
    {
        testRegisterViewModeFormattedValues(
            RegisterViewModeImpl.UNSIGNED_INTEGER, "4,282,581,864",
            LARGE_NEGATIVE);
    }

    private void testRegisterViewModeFormattedValues(
        RegisterViewMode viewMode, String expectedOutput, int valueToFormat)
    {
        assertEquals("The formatted output did not match!", expectedOutput,
            viewMode.format(valueToFormat));
    }

}
