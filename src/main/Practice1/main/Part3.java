package main.Practice1.main;

public class Part3 {
    public static void main(String[] args) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < args.length; i++) {
            result.append(args[i] + " ");
        }
        System.out.print(result.toString().trim());
    }
}
