package assembler.simulator.mips;

import assembler.simulator.Register;

abstract class BaseAddInstruction extends TwoRegisterArithmeticInstruction
{
    public BaseAddInstruction(String instruction, String operation,
        Register operandOne, Register operandTwo, Register destination,
        OverflowStrategy overflowStrategy)
    {
        super(operation, instruction, operandOne, operandTwo,
            destination, overflowStrategy);
    }

    @Override
    protected long calculateResult(long firstOperand, long secondOperand)
    {
        return firstOperand + secondOperand;
    }
}
