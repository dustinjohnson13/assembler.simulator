package assembler.simulator.mips;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RegisterTest
{
    @Test
    public void testZeroRegisterNumberValue()
    {
        assertEquals(0, MipsRegister.ZERO.getNumericValue());
    }

    @Test
    public void testZeroRegisterName()
    {
        assertEquals("$zero", MipsRegister.ZERO.getName());
    }

    @Test
    public void testAssemblerTemporaryRegisterNumberValue()
    {
        assertEquals(1, MipsRegister.AT.getNumericValue());
    }

    @Test
    public void testAssemblerTemporaryRegisterName()
    {
        assertEquals("$at", MipsRegister.AT.getName());
    }

    @Test
    public void testV0RegisterNumberValue()
    {
        assertEquals(2, MipsRegister.V0.getNumericValue());
    }

    @Test
    public void testV0RegisterName()
    {
        assertEquals("$v0", MipsRegister.V0.getName());
    }

    @Test
    public void testV1RegisterNumberValue()
    {
        assertEquals(3, MipsRegister.V1.getNumericValue());
    }

    @Test
    public void testV1RegisterName()
    {
        assertEquals("$v1", MipsRegister.V1.getName());
    }

    @Test
    public void testA0RegisterNumberValue()
    {
        assertEquals(4, MipsRegister.A0.getNumericValue());
    }

    @Test
    public void testA0RegisterName()
    {
        assertEquals("$a0", MipsRegister.A0.getName());
    }

    @Test
    public void testA1RegisterNumberValue()
    {
        assertEquals(5, MipsRegister.A1.getNumericValue());
    }

    @Test
    public void testA1RegisterName()
    {
        assertEquals("$a1", MipsRegister.A1.getName());
    }

    @Test
    public void testA2RegisterNumberValue()
    {
        assertEquals(6, MipsRegister.A2.getNumericValue());
    }

    @Test
    public void testA2RegisterName()
    {
        assertEquals("$a2", MipsRegister.A2.getName());
    }

    @Test
    public void testA3RegisterNumberValue()
    {
        assertEquals(7, MipsRegister.A3.getNumericValue());
    }

    @Test
    public void testA3RegisterName()
    {
        assertEquals("$a3", MipsRegister.A3.getName());
    }

    @Test
    public void testGetNumericRegisterByName()
    {
        assertEquals(MipsRegister.S0, MipsRegister.fromName("$s0"));
    }

    @Test
    public void testGetAssemblerTemporaryRegisterByName()
    {
        assertEquals(MipsRegister.AT, MipsRegister.fromName("$at"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionThrownWhenInvalidRegisterNamePassedIn()
    {
        MipsRegister.fromName("invalid");
    }
}
