package assembler.simulator.mips;

import assembler.simulator.Assembler;

public class JumpRegisterInstruction extends BaseJumpInstruction
{
    private static final String OPERATION = "jr";

    public JumpRegisterInstruction(String instruction, String address)
    {
        super(OPERATION, instruction, address);
    }

    @Override
    public void execute(Assembler assembler)
    {
        assembler.setProgramCounter(assembler.getRegisterValue(MipsRegister.fromName(address)));
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }
}
