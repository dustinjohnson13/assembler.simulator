package assembler.simulator.mips;

import assembler.simulator.Register;

abstract class DataTransferInstruction extends BaseInstruction
{
    private final Register dataRegister;

    private final Register addressRegister;

    private final int addressOffset;

    DataTransferInstruction(String operation, String instruction,
        Register dataRegister, int addressOffset, Register addressRegister)
    {
        super(operation, instruction);
        this.dataRegister = dataRegister;
        this.addressOffset = addressOffset;
        this.addressRegister = addressRegister;
    }

    Register getDataRegister()
    {
        return dataRegister;
    }

    Register getAddressRegister()
    {
        return addressRegister;
    }

    int getAddressOffset()
    {
        return addressOffset;
    }
}
