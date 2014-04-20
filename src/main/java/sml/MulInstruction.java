package sml;

public class MulInstruction extends Instruction {

    private int result;
    private int op1;
    private int op2;

    public MulInstruction(final String label, final String op) {
        super(label, op);
    }

    public MulInstruction(String label, Translator translator) {
        this(label, "mul");
        this.result = translator.scanInt();
        this.op1 = translator.scanInt();
        this.op2 = translator.scanInt();
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
