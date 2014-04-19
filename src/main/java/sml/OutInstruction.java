package sml;

public class OutInstruction extends Instruction {

    private int op1;

    public OutInstruction(final String label, final String op) {
        super(label, op);
    }

    public OutInstruction(final String label, final int op1) {
        this(label, "out");
        this.op1 = op1;
    }

    @Override
    public void execute(final Machine m) {
        final int value1 = m.getRegisters().getRegister(op1);
        System.out.println(value1);
    }

    @Override
    public String toString() {
        return super.toString() + " " + op1;
    }
}
