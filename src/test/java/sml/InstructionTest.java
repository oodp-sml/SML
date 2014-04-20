package test.java.sml;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

import sml.Machine;
import sml.Translator;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class InstructionTest {
    
    private static Machine m;
    
    @BeforeClass
    public static void setup() {
        m = new Machine();
    }

    /**
     * Stores 6 in register 1. Stores 2 in register 2. Add register 1 and 2 and store result in register 3.
     */
    @Test
    public void add() {
        final Translator t = new Translator(getClass().getResource("/add.txt").getPath());
        executeAndAssert(t, 3, 8);
    }
    
    /**
     * Stores 6 in register 1. Stores 2 in register 2. Subtract register 1 and 2 and store result in register 3.
     */
    @Test
    public void subtract() {
        final Translator t = new Translator(getClass().getResource("/sub.txt").getPath());
        executeAndAssert(t, 3, 4);
    }
    
    /**
     * Stores 6 in register 1. Stores 2 in register 2. Multiply register 1 and 2 and store result in register 3.
     */
    @Test
    public void multiply() {
        final Translator t = new Translator(getClass().getResource("/mul.txt").getPath());
        executeAndAssert(t, 3, 12);
    }
    
    /**
     * Stores 6 in register 1. Stores 2 in register 2. Divide register 1 and 2 and store result in register 3.
     */
    @Test
    public void divide() {
        final Translator t = new Translator(getClass().getResource("/div.txt").getPath());
        executeAndAssert(t, 3, 3);
    }
    
    /**
     * Stores 6 in register 1. Stores 2 in register 2. Output the values from register 1 and 2.
     */
    @Test
    public void out() {
        final Translator t = new Translator(getClass().getResource("/out.txt").getPath());
        
        PrintStream out = System.out;
        CustomPrintStream printStream = new CustomPrintStream();
        System.setOut(printStream);
        
        t.readAndTranslate(m.getLabels(), m.getProg());

        m.execute();
        System.setOut(out);
        
        assertTrue(printStream.getInts().get(0) == 6);
        assertTrue(printStream.getInts().get(1) == 2);
        
    }
    
    /**
     * Calculates factorial 6 and stores the result in the register 21
     */
    @Test
    public void bnz() {
        final Translator t = new Translator(getClass().getResource("/bnz.txt").getPath());
        executeAndAssert(t, 21, 720);
    }
    
    private void executeAndAssert(Translator t, int resultRegister, int result) {
        t.readAndTranslate(m.getLabels(), m.getProg());

        m.execute();
        
        assertTrue(result == m.getRegisters().getRegister(resultRegister));
    }
    
    private class CustomPrintStream extends PrintStream {
        
        private List<Integer> ints;
        
        public CustomPrintStream() {
            super(System.out);
            ints = new LinkedList<>();
        }
        
        @Override
        public void println(int x) {
            ints.add(x);
            super.println(x);
        }
        
        public List<Integer> getInts() {
            return ints;
        }
        
    }

}
