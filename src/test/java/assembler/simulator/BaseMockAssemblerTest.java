package assembler.simulator;

import org.junit.Ignore;

@Ignore
public abstract class BaseMockAssemblerTest
{
    protected static final Register OPERAND_ONE = new MockRegister(0);
    protected static final Register OPERAND_TWO = new MockRegister(1);
    protected static final Register DESTINATION = new MockRegister(2);
    protected static final Register FOUR = new MockRegister(3);
    protected static final Register[] REGISTERS = new Register[]
    { OPERAND_ONE, OPERAND_TWO, DESTINATION, FOUR };

    protected MockBaseAssembler assembler = new MockBaseAssembler(REGISTERS,
        new MockInstructionFactory());
}
