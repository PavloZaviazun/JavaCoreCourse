package main.Practice6.part6;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part62 {
    private Part62() {

    }
    public static void lengthCount(String input) {
        Pattern pattern = Pattern.compile("\\b[a-zA-Z]+\\b");
        Matcher matcher = pattern.matcher(input);
        List <String> list = new ArrayList <>();
        while(matcher.find()) {
            String group = matcher.group();
            int count = 0;
            for(String el : list) {
                if(!el.equals(group)) count++;
            }
            if(count == list.size()) list.add(group);
        }
        list.stream().sorted((a, b) -> b.length() - a.length())
                .limit(3)
                .forEach(a -> System.out.println(a + " ==> " + a.length()));
    }
}
