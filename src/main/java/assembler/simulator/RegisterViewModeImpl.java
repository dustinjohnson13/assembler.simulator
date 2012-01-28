package assembler.simulator;

enum RegisterViewModeImpl implements RegisterViewMode
{
    SIGNED_INTEGER("Signed Integer"), UNSIGNED_INTEGER("Unsigned Integer"), HEX(
        "Hex"), BIT("Bit");

    private static final long MASK_TOP_32_BITS = 0xFFFFFFFFL;
	private final String displayString;

    private RegisterViewModeImpl(String displayString)
    {
        this.displayString = displayString;
    }

    @Override
    public String format(int registerValue)
    {
        switch (this)
        {
            case SIGNED_INTEGER:
                return String.format("%,d", registerValue);
            case UNSIGNED_INTEGER:
                long registerValueAsLong = registerValue;
                // Mask the upper 32-bits
                registerValueAsLong &= MASK_TOP_32_BITS;
                return String.format("%,d", registerValueAsLong);
            case HEX:
                return String.format("%08X", registerValue);
            case BIT:
                return String.format("%1$#32s",
                    Integer.toBinaryString(registerValue)).replaceAll(" ",
                    "0");
            default:
                throw new IllegalArgumentException(
                    "Unhandled enumeration value: " + this.toString());
        }
    }

    @Override
    public String getDisplayString()
    {
        return displayString;
    }
}
