package main.Practice4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while(true) {
                String line = reader.readLine();
                if(line.equals("stop")) break;
                typeOfInput(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void typeOfInput(String line) {
        switch(line) {
            case "char":
                findAndPrint("\\b(?iu)[a-zа-яъєёїі]{1}\\b");
                break;
            case "int":
                findAndPrint("\\b(?<!\\.)[0-9]+(?!\\.)\\b");
                break;
            case "double":
                findAndPrint("\\b?[0-9]*\\.[0-9]+\\b");
                break;
            case "String":
                findAndPrint("\\b(?iu)[a-zа-яъєёїі]{2,}\\b");
                break;
            default: System.out.println("Incorrect input");
        }
    }

    private static void findAndPrint(String regex) {
        String input = Part1.readFile("part3.txt");
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while(matcher.find()) {
            sb.append(matcher.group()).append(" ");
        }
        System.out.println(sb.toString());
    }

}

