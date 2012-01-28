package assembler.simulator.mips;

import assembler.simulator.Assembler;
import assembler.simulator.Register;

public abstract class OneRegisterArithmeticInstruction extends
    OneRegisterArgumentWithImmediateInstruction<Integer>
{
    protected final OverflowStrategy overflowStrategy;

    OneRegisterArithmeticInstruction(String operation,
        String instruction, Register destination, int immediate,
        Register operandOne, OverflowStrategy overflowStrategy)
    {
        super(operation, instruction, destination, immediate, operandOne);
        this.overflowStrategy = overflowStrategy;
    }

    @Override
    public void execute(Assembler assembler)
    {
        long firstOperand = assembler.getRegisterValue(this.getOperandOne());
        long secondOperand = operandTwo;

        long result = calculateResult(firstOperand, secondOperand);

        assembler.setRegisterValue(this.getDestination(), (int) result);

        overflowStrategy.checkForOverflow(assembler, result);
    }

    protected abstract long calculateResult(long firstOperand,
        long secondOperand);
}
