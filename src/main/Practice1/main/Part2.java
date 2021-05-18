package main.Practice1.main;

public class Part2 {
    public static void main(String[] args) {
        int result = 0;
        for(int i = 0; i < args.length; i++) {
                result += Integer.parseInt(args[i]);
        }
        System.out.print(result);
    }
}
