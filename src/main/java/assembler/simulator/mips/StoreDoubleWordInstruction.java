package assembler.simulator.mips;

import assembler.simulator.Assembler;
import assembler.simulator.ByteArrayConverter;
import assembler.simulator.Register;

public class StoreDoubleWordInstruction extends
    DoubleWordDataTransferInstruction
{
    private static final String OPERATION = "sd";

    public StoreDoubleWordInstruction(String instruction,
        Register dataRegister, int addressOffset, Register addressRegister)
    {
        super(OPERATION, instruction, dataRegister, addressOffset,
            addressRegister);
    }

    @Override
    public void execute(Assembler assembler)
    {
        Register dataRegister = getDataRegister();
        Register secondDataRegister = findSecondDataRegister(assembler,
            dataRegister);

        int memoryAddress = assembler.getRegisterValue(getAddressRegister());
        memoryAddress += this.getAddressOffset();

        int registerValue = assembler.getRegisterValue(dataRegister);
        int secondRegisterValue = assembler.getRegisterValue(secondDataRegister);

        long doubleWord = ByteArrayConverter.combineTwoWordsIntoDoubleWord(
            registerValue, secondRegisterValue);

        assembler.setDoubleWordInMemory(memoryAddress, doubleWord);
    }

    public static String getOperationKey()
    {
        return OPERATION;
    }
}