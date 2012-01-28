package assembler.simulator.mips;

import assembler.simulator.Register;

public abstract class BaseAddImmediateInstruction extends
    OneRegisterArithmeticInstruction
{
    BaseAddImmediateInstruction(String operation, String instruction,
        Register destination, int immediate, Register operandOne,
        OverflowStrategy overflowStrategy)
    {
        super(operation, instruction, destination, immediate, operandOne, overflowStrategy);
    }

    @Override
    protected long calculateResult(long firstOperand, long secondOperand)
    {
        return firstOperand + secondOperand;
    }
}
