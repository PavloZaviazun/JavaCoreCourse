package main.Practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    public static void main(String[] args) {
        String input = Util.getInput("part2.txt");
        System.out.println(convert(input));
    }

    public static String convert(String input) {
        Pattern minPattern = Pattern.compile("\\b[a-zA-Zа-яА-Яіїёє]{" + findMinValue(input) + "}\\b");
        Pattern maxPattern = Pattern.compile("\\b[a-zA-Zа-яА-Яіїёє]{" + findMaxValue(input) + "}\\b");
        StringBuilder result = new StringBuilder("Min: ");
        StringBuilder minWords = new StringBuilder();
        StringBuilder maxWords = new StringBuilder();
        Matcher matcher = minPattern.matcher(input);
        while (matcher.find()) {
            if(!minWords.toString().contains(matcher.group())) minWords.append(matcher.group()).append(", ");
        }
        result.append(minWords.delete(minWords.length() - 2, minWords.length()).append(System.lineSeparator()));
        matcher = maxPattern.matcher(input);
        while (matcher.find()) {
            if(!maxWords.toString().contains(matcher.group())) maxWords.append(matcher.group()).append(", ");
        }
        result.append("Max: ").append(maxWords.delete(maxWords.length() - 2, maxWords.length()));
        return result.toString();
    }

    public static int findMaxValue(String input) {
        int max = 0;
        for(int i = 1; i < 20; i++) {
            Pattern pattern = Pattern.compile("\\b[a-zA-Zа-яА-ЯіїёєІЇЄЁ]{" + i + "}\\b");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                max = i;
            }
        }
        return max;
    }

    public static int findMinValue(String input) {
        for(int i = 1; i < 20; i++) {
            Pattern pattern = Pattern.compile("\\b[a-zA-Zа-яА-ЯіїёєІЇЄЁ]{" + i + "}\\b");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                return i;
            }
        }
        return 0;
    }
}

