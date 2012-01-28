package assembler.simulator;

import java.io.File;
import java.util.Collection;

public interface Assembler extends Subject
{
    public static enum Bytes
    {
        HIGH, LOW;
    }

    int INITIAL_REGISTER_VALUE = 0;

    int getNumberOfRegisters();

    Collection<Register> getRegisters();

    void processLine(String line);

    int getRegisterValue(Register register);

    void setRegisterValue(Register register, int value);

    boolean runInstruction();

    void runInstructions();

    Collection<Instruction> getInstructions();

    int getWordFromMemory(int memoryAddress);

    void setWordInMemory(int memoryAddress, int memoryValue);

    long getDoubleWordFromMemory(int memoryAddress);

    void setDoubleWordInMemory(int memoryAddress, long value);

    short getHalfWordFromMemory(int memoryAddress);

    void setHalfWordInMemory(int memoryAddress, short value);

    void setByteInMemory(int memoryAddress, byte value);

    byte getByteFromMemory(int memoryAddress);

    void jump(String label);

    void resetRegisters();

    void clearInstructions();

    int getProgramCounter();

    void setProgramCounter(int firstIndex);

    void overflowOccurred();

    void setRegisterValue(Register register, long value, Bytes bytes);

    RegisterViewMode getRegisterViewMode();

    void setRegisterViewMode(RegisterViewMode viewMode);

    Collection<RegisterViewMode> getRegisterViewModes();

    void loadInstructions(File file);
}
