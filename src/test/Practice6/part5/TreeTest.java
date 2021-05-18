package Practice6.part5;

import main.Practice6.part5.Tree;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class TreeTest {
    @Test
    public void main() {
        PrintStream outInitial = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
        Tree <Integer> tree = new Tree<>();
        tree.add(3);
        tree.add(7);
        tree.add(1);
        tree.add(8);
        tree.add(6);
        tree.add(2);
        tree.add(5);
        tree.print();
        String promise = "  1" + System.lineSeparator()
                + "    2" + System.lineSeparator()
                + "3" + System.lineSeparator()
                + "      5" + System.lineSeparator()
                + "    6" + System.lineSeparator()
                + "  7" + System.lineSeparator()
                + "    8" + System.lineSeparator();
        assertEquals(promise, baos.toString());
        System.setOut(outInitial);
    }
}
