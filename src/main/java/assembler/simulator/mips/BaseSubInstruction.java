package assembler.simulator.mips;

import assembler.simulator.Register;

class BaseSubInstruction extends TwoRegisterArithmeticInstruction
{
    BaseSubInstruction(String operation, String instruction,
        Register operandOne, Register operandTwo, Register destination,
        OverflowStrategy overflowStrategy)
    {
        super(operation, instruction, operandOne, operandTwo,
            destination, overflowStrategy);
    }

    @Override
    protected long calculateResult(long firstOperand, long secondOperand)
    {
        return firstOperand - secondOperand;
    }
}
