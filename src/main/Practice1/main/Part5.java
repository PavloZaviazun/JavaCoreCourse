package main.Practice1.main;

public class Part5 {
    public static void main(String[] args) {
        try {
            String[] array = args[0].split("");
            int result = 0;
            for (int i = 0; i < array.length; i++) {
                result += Integer.parseInt(array[i]);
            }
            System.out.print(result);
        } catch(Exception e) {
            System.out.print(e);
        }
    }
}
