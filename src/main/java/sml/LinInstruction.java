package main.java.sml;

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

    public LinInstruction(final String label, final int register, final int value) {
        super(label, "lin");
        this.register = register;
        this.value = value;

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
