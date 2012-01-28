package assembler.simulator.mips;

import assembler.simulator.Assembler;
import assembler.simulator.Register;

public abstract class BaseDivInstruction extends
    TwoRegisterArithmeticInstruction
{
    BaseDivInstruction(String operation, String instruction,
        Register operandOne, Register operandTwo, Register destination)
    {
        super(operation, instruction, operandOne, operandTwo,
            destination, OverflowStrategy.IGNORE);
    }

    @Override
    public void execute(Assembler assembler)
    {
        long firstOperand = assembler.getRegisterValue(this.getOperandOne());
        long secondOperand = assembler.getRegisterValue(this.getOperandTwo());

        // quotient[0], modulus[1]
        long[] results = calculateResults(firstOperand, secondOperand);

        assembler.setRegisterValue(MipsRegister.LO, (int) results[0]);
        assembler.setRegisterValue(MipsRegister.HI, (int) results[1]);
    }

    protected abstract long[] calculateResults(long firstOperand,
        long secondOperand);

    @Override
    protected long calculateResult(long firstOperand, long secondOperand)
    {
        throw new UnsupportedOperationException(
            "There are two results from division, so this class should not return a single argument!");
    }

}
