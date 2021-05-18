package main.Practice3;

import java.security.SecureRandom;
import java.util.regex.Pattern;

public class Part1 {

    public static void main(String[] args) {
        String input = Util.getInput("part1.txt");
        System.out.println(convert4(input));
    }

    public static String convert1(String input) {
        String[] array = input.split(System.lineSeparator());
        StringBuilder result = new StringBuilder("");
        for(int i = 1; i < array.length; i++) {
            String[] list = array[i].split(";");
            result.append(list[0]).append(": ").append(list[2]).append(System.lineSeparator());
        }
        return result.toString();
    }

    public static String convert2(String input) {
        String[] array = input.split(System.lineSeparator());
        StringBuilder result = new StringBuilder("");
        for(int i = 1; i < array.length; i++) {
            String[] list = array[i].split(";");
            String[] fio = list[1].split(" ");
            result.append(fio[1])
                    .append(" ")
                    .append(fio[0])
                    .append(" (email: ")
                    .append(list[2]).append(')')
                    .append(System.lineSeparator());
        }
        return result.toString();
    }

    public static String convert3(String input) {
        String[] array = input.split(System.lineSeparator());
        StringBuilder domainsString = new StringBuilder();
        StringBuilder result = new StringBuilder("");
        for(int i = 1; i < array.length; i++) {
            String domain = array[i].split("@")[1];
            if(!domainsString.toString().contains(domain)) domainsString.append(domain).append(" ");
        }
        String[] domains = domainsString.toString().split(" ");
        for(int j = 0; j < domains.length; j++) {
            result.append(domains[j]).append(" ==> ");
            Pattern pattern = Pattern.compile(".*@" + domains[j]);
            for(int k = 1; k < array.length; k++) {
                if(pattern.matcher(array[k]).matches()) result.append(array[k].split(";")[0]).append(", ");
            }
            result.delete(result.length() - 2, result.length()).append(System.lineSeparator());
        }
        return result.toString();
    }

    public static String convert4(String input) {
        String[] array = input.split(System.lineSeparator());
        StringBuilder result = new StringBuilder(array[0] + ";Password" + System.lineSeparator());
        SecureRandom random = new SecureRandom();
        for(int i = 1; i < array.length; i++){
            result.append(array[i])
                    .append(';')
                    .append(random.nextInt((9999 - 1000)) + 1000)
                    .append(System.lineSeparator());
        }
        return result.toString();
    }
}

