package assembler.simulator.mips;

import assembler.simulator.Register;

abstract class OneRegisterArgumentWithImmediateInstruction<T> extends
    OneRegisterArgumentInstruction
{
    protected final Register destination;

    protected final T operandTwo;

    protected OneRegisterArgumentWithImmediateInstruction(String operation,
        String instruction, Register destination, T immediate,
        Register operandOne)
    {
        super(operation, instruction, operandOne);
        this.operandTwo = immediate;
        this.destination = destination;
    }

    public Register getDestination()
    {
        return destination;
    }

    public T getOperandTwo()
    {
        return operandTwo;
    }
}
