package sml;

import sml.Translator;

/**
 * This class ....
 * 
 * @author someone
 */

public class LinInstruction extends Instruction {
    private int register;
    private int value;

    public LinInstruction(final String label, final String opcode) {
        super(label, opcode);
    }
    
    public LinInstruction(String label, Translator translator) {
        super(label, "lin");
        this.register = translator.scanInt();
        this.value = translator.scanInt();
    }

    @Override
    public void execute(final Machine m) {
        m.getRegisters().setRegister(register, value);
    }

    @Override
    public String toString() {
        return super.toString() + " register " + register + " value is "
                + value;
    }
}
