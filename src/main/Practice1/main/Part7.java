package main.Practice1.main;

public class Part7 {

    static int numberInt;
    static char[] array;

    public static void main(String[] args) {
        String ar = " ==> ";
        System.out.println("A" + ar + str2int("A") + ar + int2str(str2int("A")));
        System.out.println("B" + ar + str2int("B") + ar + int2str(str2int("B")));
        System.out.println("Z" + ar + str2int("Z") + ar + int2str(str2int("Z")));
        System.out.println("AA" + ar + str2int("AA") + ar + int2str(str2int("AA")));
        System.out.println("AZ" + ar + str2int("AZ") + ar + int2str(str2int("AZ")));
        System.out.println("BA" + ar + str2int("BA") + ar + int2str(str2int("BA")));
        System.out.println("ZZ" + ar + str2int("ZZ") + ar + int2str(str2int("ZZ")));
        System.out.println("AAA" + ar + str2int("AAA") + ar + int2str(str2int("AAA")));
    }

    public static int str2int(String number) {
        char letter1 = 65;
        array = new char[27];
        for(int i = 1; i < 27; i++) {
            array[i] = letter1++;
        }
            String[] letter = number.split("");
            int result = 0;
            int razriad = 1;
            for(int i = letter.length - 1; i >= 0; i--) {
                for (int j = 1; j < 27; j++) {
                    if (letter[i].equals(Character.toString(array[j]))) {
                        result += j * Math.pow(26, razriad - 1.0);
                    }
                }
                razriad++;
            }
        return result;
    }

    public static String int2str(int number) {
        char letter1 = 65;
        array = new char[27];
        for(int i = 1; i < 27; i++) {
            array[i] = letter1++;
        }
        int razriad = 1;
        numberInt = number;
        double value = number;
        StringBuilder result = new StringBuilder();
        while(value > 26) {
            value = value / 26 - 1;
            razriad++;
        }
        for(int i = razriad; i > 0; i--) {
            int inArray = (int) (numberInt / Math.pow(26, i - 1.0));
            System.out.println(inArray);
            if(numberInt % 26 != 0 || i == 1) {
                result = repeatCode(result, i, inArray);
            }
            else {
                result = repeatCode(result, i, 1);
            }
        }
        return result.toString();
    }

    public static StringBuilder repeatCode(StringBuilder result, int i, int inArray) {
            if (numberInt / Math.pow(26, i - 1.0) >= 26) {
                numberInt -= 26 * Math.pow(26, i - 1.0);
                return result.append(array[26]);
            } else {
                numberInt -= Math.pow(26, i - 1.0) * inArray;
                return result.append(array[inArray]);
            }
    }

    public static String rightColumn(String number) {
        return int2str(str2int(number) + 1);
    }
}
