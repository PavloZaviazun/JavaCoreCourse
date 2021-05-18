package Practice6.part1;

import main.Practice6.part1.WordContainer;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class WordContainerTest {
    @Test
    public void main() {
        InputStream inInitial = System.in;
        String text = "asd 43 asdf asd 43" + "\n" + "434 asdf \n" + "\n" + "kasdf asdf stop asdf\n" + "\n" + "stop";
        System.setIn(new ByteArrayInputStream((text.getBytes())));
        String answer = "asdf : 3" + System.lineSeparator()
                + "43 : 2" + System.lineSeparator()
                + "asd : 2" + System.lineSeparator()
                + "434 : 1"  + System.lineSeparator()
                +  "kasdf : 1" + System.lineSeparator();
        PrintStream outInitial = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);
        WordContainer.main(null);
        assertEquals(answer, outputStream.toString());
        System.setIn(inInitial);
        System.setOut(outInitial);
    }
}
