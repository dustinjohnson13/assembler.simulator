package assembler.simulator.mips;

import java.util.Iterator;

import assembler.simulator.Assembler;
import assembler.simulator.Register;

public abstract class DoubleWordDataTransferInstruction extends
    DataTransferInstruction
{

    public DoubleWordDataTransferInstruction(String operation,
        String instruction, Register dataRegister, int addressOffset,
        Register addressRegister)
    {
        super(operation, instruction, dataRegister, addressOffset,
            addressRegister);
    }

    protected Register findSecondDataRegister(Assembler assembler,
        Register dataRegister)
    {
        Iterator<Register> iter = assembler.getRegisters().iterator();
        Register secondDataRegister = null;
        while (iter.hasNext())
        {
            Register reg = iter.next();
            if (reg == dataRegister)
            {
                secondDataRegister = iter.next();
                break;
            }
        }

        if (secondDataRegister == null)
        {
            throw new IllegalStateException(
                "Unable to find the next register from " + dataRegister + "!");
        }

        return secondDataRegister;
    }
}
