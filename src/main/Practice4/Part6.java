package main.Practice4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {

    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while(true) {
                String line = reader.readLine();
                if(line.equals("stop")) break;
                checkRequest(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checkRequest(String line) {
        StringBuilder sb = new StringBuilder(line + ": ");
        switch (line.toLowerCase()) {
            case "cyrl" :
                findMatches("\\b(?iu)[а-яъєёїі]+\\b", sb);
                break;
            case "latn" :
                findMatches("\\b(?iu)[a-z]+\\b", sb);
                break;
            default:
                System.out.println(line + ": Incorrect input");
        }
    }

    private static void findMatches(String regex, StringBuilder sb) {
        String input = Part1.readFile("part6.txt");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while(matcher.find()) {
            sb.append(matcher.group()).append(" ");
        }
        System.out.println(sb.toString());
    }

}

