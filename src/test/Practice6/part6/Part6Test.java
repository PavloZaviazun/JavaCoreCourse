package Practice6.part6;

import main.Practice6.part6.Part6;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class Part6Test {
    PrintStream outInitial;
    ByteArrayOutputStream baos;

    @Before
    public void replaceOutput() {
        outInitial = System.out;
        baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
    }

    @After
    public void returnOutput() {
        System.setOut(outInitial);
    }

    @Test
    public void main1() {
        Part6.main(new String[] {"--input", "part6.txt", "--task", "frequency"});
        String promise = "whale ==> 3" + System.lineSeparator()
                + "cheetah ==> 4" + System.lineSeparator()
                + "bison ==> 3" + System.lineSeparator();
        assertEquals(promise, baos.toString());
    }

    @Test
    public void main2() {
        Part6.main(new String[] {"--input", "part6.txt", "--task", "length"});
        String promise = "chimpanzee ==> 10" + System.lineSeparator()
                + "mongoose ==> 8" + System.lineSeparator()
                + "tortoise ==> 8" + System.lineSeparator();
        assertEquals(promise, baos.toString());
    }

    @Test
    public void main3() {
        Part6.main(new String[] {"--input", "part6.txt", "--task", "duplicates"});
        String promise = "RAUGAJ" + System.lineSeparator()
                + "NOSIB" + System.lineSeparator()
                + "ELAHW" + System.lineSeparator();
        assertEquals(promise, baos.toString());
    }

}
