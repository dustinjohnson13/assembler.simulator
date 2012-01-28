package assembler.simulator.mips;

import assembler.simulator.Assembler;

class JumpInstruction extends BaseJumpInstruction
{
    private static final String OPERATION = "j";

    public JumpInstruction(String instruction, String address)
    {
        super(OPERATION, instruction, address);
    }

    @Override
    public void execute(Assembler assembler)
    {
        assembler.jump(address);
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }

}
