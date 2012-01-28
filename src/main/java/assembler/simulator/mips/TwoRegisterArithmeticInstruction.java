package assembler.simulator.mips;

import assembler.simulator.Assembler;
import assembler.simulator.Register;

public abstract class TwoRegisterArithmeticInstruction extends
    ThreeRegisterInstruction
{
    protected final OverflowStrategy overflowStrategy;

    public TwoRegisterArithmeticInstruction(String operation,
        String instruction, Register operandOne, Register operandTwo,
        Register destination, OverflowStrategy overflowStrategy)
    {
        super(operation, instruction, operandOne, operandTwo, destination);
        this.overflowStrategy = overflowStrategy;
    }

    @Override
    public void execute(Assembler assembler)
    {
        long firstOperand = assembler.getRegisterValue(this.getOperandOne());
        long secondOperand = assembler.getRegisterValue(this.getOperandTwo());

        long result = calculateResult(firstOperand, secondOperand);

        assembler.setRegisterValue(this.getDestination(), (int) result);

        overflowStrategy.checkForOverflow(assembler, result);
    }

    protected abstract long calculateResult(long firstOperand,
        long secondOperand);
}
