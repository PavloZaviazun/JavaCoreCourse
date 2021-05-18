package main.Practice4;

import java.io.*;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        SecureRandom number = new SecureRandom();
        for(int i = 0; i < 10; i++) {
            sb.append(number.nextInt(51)).append(" ");
        }
        String initial = sb.toString().trim();
        toFile(initial, "part2.txt");
        System.out.println("input ==> " + initial);
        String line = fromFile();
        String output = sortAndWrite(line);
        System.out.println("output ==> " + output);
    }

    public static void toFile(String line, String fileName) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(line);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String fromFile() {
        String line = "";
        try(BufferedReader reader = new BufferedReader(new FileReader("part2.txt"));) {
            line = reader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return line;
    }

    public static String sortAndWrite(String line) {
        Pattern pattern = Pattern.compile("[0-9]{1,2}");
        Matcher matcher = pattern.matcher(line);
        StringBuilder sb = new StringBuilder();
        int[] array = new int[10];
        int i = 0;
        while(matcher.find()) {
            array[i] = Integer.parseInt(matcher.group());
            i++;
        }
        for(int k = 0; k < array.length; k++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        Arrays.stream(array).forEach(el -> sb.append(el).append(" "));
        String result = sb.toString().trim();
        toFile(result, "part2_sorted.txt");
        return result;
    }

}

