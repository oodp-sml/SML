package sml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
public class Translator {

    // word + line is the part of the current line that's not yet processed
    // word has no whitespace
    // If word and line are not empty, line begins with whitespace
    private String line = "";
    private Labels labels; // The labels of the program being translated
    private ArrayList<Instruction> program; // The program to be created
    private final String fileName; // source file of SML code

    public Translator(final String fileName) {
        this.fileName = fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"
    public boolean readAndTranslate(final Labels lab, final ArrayList<Instruction> prog) {
        Scanner sc; // Scanner attached to the file chosen by the user
        try {
            sc = new Scanner(new File(fileName));
        } catch (final IOException ioE) {
            System.out.println("File: IO error to start " + ioE.getMessage());
            return false;
        }
        labels = lab;
        labels.reset();
        program = prog;
        program.clear();

        try {
            line = sc.nextLine();
        } catch (final NoSuchElementException ioE) {
            return false;
        }

        // Each iteration processes line and reads the next line into line
        while (line != null) {
            // Store the label in label
            final String label = scan();

            if (label.length() > 0) {
                final Instruction ins = getInstruction(label);
                if (ins != null) {
                    labels.addLabel(label);
                    program.add(ins);
                }
            }

            try {
                line = sc.nextLine();
            } catch (final NoSuchElementException ioE) {
                return false;
            }
        }
        return true;
    }

    // line should consist of an MML instruction, with its label already
    // removed. Translate line into an instruction with label label
    // and return the instruction
    public Instruction getInstruction(final String label) {
        int s1; // Possible operands of the instruction
        int s2;
        int r;
        final int x;

        if (line.equals("")) {
            return null;
        }

        final String ins = scan();
        switch (ins) {
        case "add":
            r = scanInt();
            s1 = scanInt();
            s2 = scanInt();
            return new AddInstruction(label, r, s1, s2);
        case "lin":
            r = scanInt();
            s1 = scanInt();
            return new LinInstruction(label, r, s1);
        }

        // You will have to write code here for the other instructions.

        return null;
    }

    /*
     * Return the first word of line and remove it from line. If there is no word, return ""
     */
    public String scan() {
        line = line.trim();
        if (line.length() == 0) {
            return "";
        }

        int i = 0;
        while ((i < line.length()) && (line.charAt(i) != ' ')
                && (line.charAt(i) != '\t')) {
            i = i + 1;
        }
        final String word = line.substring(0, i);
        line = line.substring(i);
        return word;
    }

    // Return the first word of line as an integer. If there is
    // any error, return the maximum int
    public int scanInt() {
        final String word = scan();
        if (word.length() == 0) {
            return Integer.MAX_VALUE;
        }

        try {
            return Integer.parseInt(word);
        } catch (final NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }
}