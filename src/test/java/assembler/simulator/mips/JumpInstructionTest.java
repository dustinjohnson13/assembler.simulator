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
public class JumpInstructionTest extends BaseMockAssemblerTest
{
    static final String TARGET = "target";

    private final Mockery mockContext = new JUnit4Mockery();

    public static final String INSTRUCTION = "j " + TARGET;

    @Test
    public void testExecuteTellsAssemblerToJump()
    {
        final Assembler mockAssembler = mockContext.mock(Assembler.class);
        JumpInstruction instruction = new JumpInstruction("<test>", TARGET);

        mockContext.checking(new Expectations() {
            {
                oneOf(mockAssembler).jump(with(TARGET));
            }
        });

        instruction.execute(mockAssembler);
    }
}
