package main.Practice4;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 implements Iterable<String> {

    public static void main(String[] args) {
        Part4 part4 = new Part4();
        Iterator <String> iterator = part4.iterator();
        while(iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }
    }

    @Override
    public Iterator <String> iterator() {
        String lines = Part1.readFile("part4.txt");
        Pattern pattern = Pattern.compile("[A-ZА-ЯЪЄЁЇ][a-zа-яъєёїі\\s,]+\\.");
        Matcher matcher = pattern.matcher(lines);

        return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return matcher.find();
            }

            @Override
            public String next() {
                if(matcher.hitEnd()) throw new NoSuchElementException();
               return matcher.group().replace(System.lineSeparator(), " ");
            }

        };
    }
}

