package main.Practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part5 {
    static int[] decimalArray = {1, 4, 5, 9, 10, 40, 50, 90, 100};
    static String[] romanArray = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};

    public static void main(String[] args) {
        System.out.println(roman2Decimal("II"));
    }

    public static String decimal2Roman(int dec) {
        StringBuilder sb = new StringBuilder();
        for(int i = decimalArray.length - 1; i >= 0; i--) {
            int quant = dec / decimalArray[i];
            if(quant > 0) {
                for(int j = 0; j < quant; j++) {
                    sb.append(romanArray[i]);
                    dec -= decimalArray[i];
                }
            }
        }
        return sb.toString();
    }

    public static int roman2Decimal(String roman) {
        int result = 0;
        while(roman.length() > 0) {
            for (int i = 0; i < romanArray.length; i++) {
                String regex = romanArray[i] + "$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(roman);
                if (matcher.find()) {
                    result += decimalArray[i];
                    roman = roman.replaceAll(regex, "");
                }
            }
        }
        return result;
    }
}

