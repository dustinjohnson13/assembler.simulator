package assembler.simulator.mips;

import assembler.simulator.Register;

abstract class OneRegisterArgumentInstruction extends BaseInstruction
{
    Register operandOne;

    OneRegisterArgumentInstruction(String operation, String originalLine,
        Register operandOne)
    {
        super(operation, originalLine);
        this.operandOne = operandOne;
    }

    Register getOperandOne()
    {
        return operandOne;
    }
}
