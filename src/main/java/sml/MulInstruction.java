package sml;

public class MulInstruction extends Instruction {

    private int result;
    private int op1;
    private int op2;

    public MulInstruction(final String label, final String op) {
        super(label, op);
    }

    public MulInstruction(final String label, final int result, final int op1, final int op2) {
        this(label, "mul");
        this.result = result;
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override
    public void execute(final Machine m) {
        final int value1 = m.getRegisters().getRegister(op1);
        final int value2 = m.getRegisters().getRegister(op2);
        m.getRegisters().setRegister(result, value1 * value2);
    }

    @Override
    public String toString() {
        return super.toString() + " " + op1 + " * " + op2 + " to " + result;
    }
}
