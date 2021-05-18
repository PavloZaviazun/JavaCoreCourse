package main.Practice4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public static String readFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try(Scanner scanner = new Scanner(new File(fileName), "cp1251")) {
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = readFile("part1.txt");
        System.out.println(convert(input));
    }

    public static String convert(String input) {
        Pattern pattern = Pattern.compile("\\b(?iu)[a-zа-яъєёїі]{4,}\\b");
        Matcher matcher = pattern.matcher(input);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()) {
            matcher.appendReplacement(sb, matcher.group().substring(2));
        }
        matcher.appendTail(sb);
        return sb.toString().trim();
    }
}


