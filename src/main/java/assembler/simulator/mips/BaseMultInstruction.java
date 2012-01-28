package assembler.simulator.mips;

import assembler.simulator.Assembler;
import assembler.simulator.Assembler.Bytes;
import assembler.simulator.Register;

abstract class BaseMultInstruction extends TwoRegisterArithmeticInstruction
{

    public BaseMultInstruction(String operation, String instruction,
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

        long result = calculateResult(firstOperand, secondOperand);

        // Place the high bits in HI and low bits in LO
        assembler.setRegisterValue(MipsRegister.HI, result, Bytes.HIGH);
        assembler.setRegisterValue(MipsRegister.LO, result, Bytes.LOW);

        overflowStrategy.checkForOverflow(assembler, result);
    }
}
