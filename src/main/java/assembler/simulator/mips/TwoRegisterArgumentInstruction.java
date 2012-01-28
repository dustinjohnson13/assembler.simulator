package assembler.simulator.mips;

import assembler.simulator.Register;

abstract class TwoRegisterArgumentInstruction extends
    OneRegisterArgumentInstruction
{
    Register operandTwo;

    TwoRegisterArgumentInstruction(String operation, String originalLine,
        Register operandOne, Register operandTwo)
    {
        super(operation, originalLine, operandOne);
        this.operandTwo = operandTwo;
    }

    Register getOperandTwo()
    {
        return operandTwo;
    }

}
