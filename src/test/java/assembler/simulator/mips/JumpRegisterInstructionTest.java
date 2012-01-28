package assembler.simulator.mips;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import assembler.simulator.Assembler;
import assembler.simulator.BaseMockAssemblerTest;

@RunWith(JMock.class)
public class JumpRegisterInstructionTest extends BaseMockAssemblerTest

{
    private static final String TARGET = "$s0";

    private static final MipsRegister REGISTER = MipsRegister.fromName(TARGET);

    private static final int ADDRESS = 7;

    private final Mockery mockContext = new JUnit4Mockery();

    public static final String INSTRUCTION = "jr " + TARGET;

    @Test
    public void testExecuteTellsAssemblerToJump()
    {
        final Assembler mockAssembler = mockContext.mock(Assembler.class);
        JumpRegisterInstruction instruction = new JumpRegisterInstruction(
            "<test>", TARGET);

        mockContext.checking(new Expectations() {
            {
                oneOf(mockAssembler).getRegisterValue(REGISTER);
                will(returnValue(ADDRESS));
                oneOf(mockAssembler).setProgramCounter(with(ADDRESS));
            }
        });

        instruction.execute(mockAssembler);
    }
}
