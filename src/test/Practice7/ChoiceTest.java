package Practice7;

import main.Practice7.Choice;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ChoiceTest {

    @Test
    public void choiceTestId() {
        Choice choice = new Choice();
        choice.makeYourChoice("id", "testId");
        assertEquals("testId", choice.getId());
    }

    @Test
    public void choiceTestTitle() {
        Choice choice = new Choice();
        choice.makeYourChoice("title", "testTitle");
        assertEquals("testTitle", choice.getTitle());
    }

    @Test
    public void choiceTestYear() {
        Choice choice = new Choice();
        choice.makeYourChoice("year", "testYear");
        assertEquals("testYear", choice.getYear());
    }

    @Test
    public void choiceTestGenre() {
        Choice choice = new Choice();
        choice.makeYourChoice("genre", "testGenre");
        assertEquals("testGenre", choice.getGenre());
    }

    @Test
    public void choiceTestDefault() {
        Choice choice = new Choice();
        PrintStream outInitial = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
        choice.makeYourChoice("smth", "testSmth");
        assertEquals("Something went wrong with element name" + System.lineSeparator(), baos.toString());
        System.setOut(outInitial);
    }
}
