package Practice6.part4;

import main.Practice6.part4.Part4;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class RangeTest {
    @Test
    public void main() {
        PrintStream outInitial = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
        Part4.main(null);
        assertEquals("3 4 5 6 7 8 9 10 " + System.lineSeparator(), baos.toString());
        System.setOut(outInitial);
    }
}
