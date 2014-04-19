package sml;

public class BnzInstruction extends Instruction {

    private int op1;
    private String targetLabel;

    public BnzInstruction(final String label, final String op) {
        super(label, op);
    }

    public BnzInstruction(final String label, final int op1, final String targetLabel) {
        this(label, "bnz");
        this.op1 = op1;
        this.targetLabel = targetLabel;
    }

    @Override
    public void execute(final Machine m) {
        final int value1 = m.getRegisters().getRegister(op1);
        if(value1 != 0) {
            int pc = m.getLabels().indexOf(targetLabel);
            m.setPc(pc);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " " + op1 + " to " + targetLabel;
    }
}
