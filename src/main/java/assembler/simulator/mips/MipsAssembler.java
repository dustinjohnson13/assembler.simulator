package assembler.simulator.mips;

import assembler.simulator.BaseAssembler;

public class MipsAssembler extends BaseAssembler
{
    public MipsAssembler()
    {
        super(MipsRegister.values(), new MipsInstructionFactory());
    }

    @Override
    protected String getInstructionStringWithoutComments(
        String instruction)
    {
        String retVal = null;
        int indexOfCommentChar = instruction.indexOf('#');

        if (indexOfCommentChar != -1)
        {
            retVal = instruction.substring(0, indexOfCommentChar);
        }
        else
        {
            retVal = instruction;
        }

        retVal = retVal.trim();

        if (retVal.isEmpty())
        {
            return null;
        }
        else
        {
            return retVal;
        }
    }

    @Override
    public void overflowOccurred()
    {
        setRegisterValue(MipsRegister.EPC, getProgramCounter());
    }
}
