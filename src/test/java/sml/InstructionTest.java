package test.java.sml;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import sml.Machine;
import sml.Translator;

import org.junit.Test;

public class InstructionTest {

    /**
     * Stores 6 in register 1. Stores 2 in register 2. Add register 1 + 2 and store result in register 3.
     * 
     * @throws IOException
     */
    @Test
    public void add() throws IOException {
        final Machine m = new Machine();
        final Translator t = new Translator(getClass().getResource("/add.txt").getPath());
        t.readAndTranslate(m.getLabels(), m.getProg());

        m.execute();

        assertTrue(8 == m.getRegisters().getRegister(3));
    }

}
