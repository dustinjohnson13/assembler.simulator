package assembler.simulator.mips;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import assembler.simulator.Assembler;
import assembler.simulator.BaseMockAssemblerTest;
import assembler.simulator.MockRegister;

@RunWith(JMock.class)
public class BranchOnNotEqualInstructionTest extends BaseMockAssemblerTest
{
    private static final String LABEL1 = "Label1";

    public static final String INSTRUCTION = "bne $t0, $s0, " + LABEL1;

    private final Mockery mockContext = new JUnit4Mockery();

    @Test
    public void testExecuteDoesNotJumpIfOperandsAreEqual()
    {
        final Assembler mockAssembler = mockContext.mock(Assembler.class);

        final MockRegister reg1 = new MockRegister(1);
        final MockRegister reg2 = new MockRegister(2);
        BranchOnNotEqualInstruction instruction = new BranchOnNotEqualInstruction(
            "<test>", reg1, LABEL1, reg2);

        mockContext.checking(new Expectations() {
            {
                oneOf(mockAssembler).getRegisterValue(reg1);
                will(returnValue(1));
                oneOf(mockAssembler).getRegisterValue(reg2);
                will(returnValue(1));
                never(mockAssembler).jump(LABEL1);
            }
        });

        instruction.execute(mockAssembler);
    }

    @Test
    public void testExecuteJumpsIfOperandsAreNotEqual()
    {
        final Assembler mockAssembler = mockContext.mock(Assembler.class);

        final MockRegister reg1 = new MockRegister(1);
        final MockRegister reg2 = new MockRegister(2);
        BranchOnNotEqualInstruction instruction = new BranchOnNotEqualInstruction(
            "<test>", reg1, LABEL1, reg2);

        mockContext.checking(new Expectations() {
            {
                oneOf(mockAssembler).getRegisterValue(reg1);
                will(returnValue(1));
                oneOf(mockAssembler).getRegisterValue(reg2);
                will(returnValue(2));
                oneOf(mockAssembler).jump(LABEL1);
            }
        });

        instruction.execute(mockAssembler);
    }
}
