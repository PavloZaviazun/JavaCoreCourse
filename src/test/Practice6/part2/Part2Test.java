package Practice6.part2;

import main.Practice6.part2.Part2;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class Part2Test {
    @Test
    public void main() {
        PrintStream outInitial = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
        Part2.main(null);
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(baos.toString());
        List<Integer> list = new ArrayList <>();
        while(matcher.find()) {
            list.add(Integer.parseInt(matcher.group()));
        }
        assertTrue(list.get(1)/list.get(0) > 4);
        assertTrue(list.get(2) > list.get(3));
        System.setOut(outInitial);
    }
}
