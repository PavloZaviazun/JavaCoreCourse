package main.Practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {

    public static void main(String[] args) {
        String input = Util.getInput("part6.txt");
        System.out.println(convert(input));
    }

    public static String convert(String input) {
        String[] array = input.split("\\p{Space}+");
        StringBuffer sb = new StringBuffer();
        for(String el : array) {
            sb.setLength(0);
            Pattern pattern = Pattern.compile("\\b" + el + "\\b");
            Matcher matcherCount = pattern.matcher(input);
            int counter = 0;
            while(matcherCount.find()) {
                counter++;
            }
            Matcher matcherReplace = pattern.matcher(input);
            if(counter > 1) {
                while(matcherReplace.find()) matcherReplace.appendReplacement(sb, "_" + el);
            }
            matcherReplace.appendTail(sb);
            input = sb.toString();
        }
        return sb.toString();
    }
}

