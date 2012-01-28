package assembler.simulator.mips;

import assembler.simulator.Assembler;
import assembler.simulator.ByteArrayConverter;
import assembler.simulator.Register;

public class LoadDoubleWordInstruction extends
    DoubleWordDataTransferInstruction
{
    private static final String OPERATION = "ld";

    public LoadDoubleWordInstruction(String instruction,
        Register dataRegister, int addressOffset, Register addressRegister)
    {
        super(OPERATION, instruction, dataRegister, addressOffset,
            addressRegister);
    }

    @Override
    public void execute(Assembler assembler)
    {
        Register dataRegister = this.getDataRegister();
        Register secondDataRegister = findSecondDataRegister(assembler,
            dataRegister);

        int memoryAddress = assembler.getRegisterValue(this.getAddressRegister());
        memoryAddress += this.getAddressOffset();

        long doubleWord = assembler.getDoubleWordFromMemory(memoryAddress);
        int[] twoWords = ByteArrayConverter.splitDoubleWordIntoTwoWords(doubleWord);

        assembler.setRegisterValue(dataRegister, twoWords[0]);
        assembler.setRegisterValue(secondDataRegister, twoWords[1]);
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }
}