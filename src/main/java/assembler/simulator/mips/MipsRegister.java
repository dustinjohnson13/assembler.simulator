package assembler.simulator.mips;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import assembler.simulator.Register;

public enum MipsRegister implements Register
{
    ZERO(0, "$zero"), AT(1, "$at"), V0(2, "$v0"), V1(3, "$v1"), A0(4, "$a0"), A1(
        5, "$a1"), A2(6, "$a2"), A3(7, "$a3"), T0(8, "$t0"), T1(9, "$t1"), T2(
        10, "$t2"), T3(11, "$t3"), T4(12, "$t4"), T5(13, "$t5"), T6(14, "$t6"), T7(
        15, "$t7"), S0(16, "$s0"), S1(17, "$s1"), S2(18, "$s2"), S3(19, "$s3"), S4(
        20, "$s4"), S5(21, "$s5"), S6(22, "$s6"), S7(23, "$s7"), T8(24, "$t8"), T9(
        25, "$t9"), K0(26, "$k0"), K1(27, "$k1"), GP(28, "$gp"), SP(29, "$sp"), FP(
        30, "$fp"), RA(31, "$ra"), EPC(32, "EPC"), HI(33, "HI"), LO(34, "LO");

    private static final Pattern REPLACE_FIRST_DOLLAR_PATTERN = Pattern.compile("^\\$");

    private final int numericValue;

    private final String name;

    private MipsRegister(int numericValue, String name)
    {
        this.numericValue = numericValue;
        this.name = name;
    }

    @Override
    public int getNumericValue()
    {
        return numericValue;
    }

    @Override
    public String getName()
    {
        return name;
    }

    public static MipsRegister fromName(String name)
    {
        Matcher m = REPLACE_FIRST_DOLLAR_PATTERN.matcher(name);
        String nameWithoutDollarSignAndUpperCase = m.replaceFirst("").toUpperCase();

        return MipsRegister.valueOf(nameWithoutDollarSignAndUpperCase);
    }
}
