package main.Practice6.part1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Part1 {
    public static void main(String[] args) {
        InputStream initial = System.in;
        String text = "asd 43 asdf asd 43" + "\n" + "434 asdf \n" + "\n" + "kasdf asdf stop asdf\n" + "\n" + "stop";
        System.setIn(new ByteArrayInputStream((text.getBytes())));
        WordContainer.main(null);
        System.setIn(initial);
    }
}

