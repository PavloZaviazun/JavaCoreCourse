package main.Practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    public static void main(String[] args) {
        String input = Util.getInput("part3.txt");
        System.out.println(convert(input));
    }

    public static String convert(String input) {
        Pattern pattern = Pattern.compile("\\b[a-zA-Zа-яА-ЯіїёєІЇЄЁ]{3,}\\b");
        Matcher matcher = pattern.matcher(input);
        StringBuilder sb = new StringBuilder(input);
        while(matcher.find()) {
            char first = matcher.group().charAt(0);
            int index = matcher.start();
            if(Character.isLowerCase(first)) {
                char upperCase = Character.toUpperCase(first);
                sb.setCharAt(index, upperCase);
            } else {
                char lowerCase = Character.toLowerCase(first);
                sb.setCharAt(index, lowerCase);
            }
        }
        return sb.toString();
    }
}

