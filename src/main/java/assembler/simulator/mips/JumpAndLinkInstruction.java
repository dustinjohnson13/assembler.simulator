package assembler.simulator.mips;

import assembler.simulator.Assembler;

public class JumpAndLinkInstruction extends BaseJumpInstruction
{
    private static final String OPERATION = "jal";

    public JumpAndLinkInstruction(String instruction, String address)
    {
        super(OPERATION, instruction, address);
    }

    @Override
    public void execute(Assembler assembler)
    {
        assembler.setProgramCounter(assembler.getProgramCounter() + 1);
        assembler.jump(address);
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }
}
