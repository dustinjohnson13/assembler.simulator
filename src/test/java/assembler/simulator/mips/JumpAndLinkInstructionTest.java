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
public class JumpAndLinkInstructionTest extends BaseMockAssemblerTest
{
    private static final String TARGET = "target";

    private final Mockery mockContext = new JUnit4Mockery();

    public static final String INSTRUCTION = "jal " + TARGET;

    @Test
    public void testExecuteTellsAssemblerToJump()
    {
        final Assembler mockAssembler = mockContext.mock(Assembler.class);
        JumpAndLinkInstruction instruction = new JumpAndLinkInstruction(
            "<test>", TARGET);

        mockContext.checking(new Expectations() {
            {
                allowing(mockAssembler).getProgramCounter();
                allowing(mockAssembler).setProgramCounter(
                    with(any(int.class)));
                oneOf(mockAssembler).jump(with(TARGET));
            }
        });

        instruction.execute(mockAssembler);
    }

    @Test
    public void testExecuteSetsReturnAddressRegisterToNextInstructionAddress()
    {
        final int currentInstruction = 4;
        final Assembler mockAssembler = mockContext.mock(Assembler.class);
        JumpAndLinkInstruction instruction = new JumpAndLinkInstruction(
            "<test>", TARGET);

        mockContext.checking(new Expectations() {
            {
                allowing(mockAssembler).jump(with(any(String.class)));
                oneOf(mockAssembler).getProgramCounter();
                will(returnValue(currentInstruction));
                oneOf(mockAssembler).setProgramCounter(currentInstruction + 1);
            }
        });

        instruction.execute(mockAssembler);
    }
}
