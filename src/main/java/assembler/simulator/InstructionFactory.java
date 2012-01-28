package assembler.simulator;

public interface InstructionFactory
{
    Instruction parseInstruction(String instructionLine);
}
