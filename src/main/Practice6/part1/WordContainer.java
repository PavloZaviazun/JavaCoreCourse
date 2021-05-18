package main.Practice6.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordContainer {
    Set <Word> set = new TreeSet <>();
    public static void main(String[] args) {
        WordContainer wc = new WordContainer();
        StringBuilder sb = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while(reader.ready()) {
                sb.append(reader.readLine()).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String cut = sb.toString().split("\\bstop\\b")[0];
        wc.countWords(cut);
        wc.printWords();
    }

    private void printWords() {
        for(Word unique : set) {
            System.out.println(unique.getContent() + " : " + unique.getFrequency());
        }
    }

    private void countWords(String sb) {
        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher matcher = pattern.matcher(sb);
        while(matcher.find()) {
            String group = matcher.group();
            if(group.equals("stop")) break;
            Pattern defWord = Pattern.compile("\\b" + group + "\\b");
            Matcher defMatcher = defWord.matcher(sb);
            int count = 0;
            while (defMatcher.find()) {
                count++;
            }
            if(count > 0) set.add(new Word(group, count));
        }
    }

}

