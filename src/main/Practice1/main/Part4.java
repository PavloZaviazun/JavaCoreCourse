package main.Practice1.main;

public class Part4 {
    public static void main(String[] args) {
        int a = 0;
        int b = 0;
        try {
            a = Integer.parseInt(args[0]);
            b = Integer.parseInt(args[1]);
        } catch(Exception e) {
            System.out.print(e);
        }
        if(b > a) {
            int c = a;
            a = b;
            b = c;
        }
        if(a != 0 && b != 0) recursion(a, b);
    }

    public static void recursion(int a, int b) {
        if(a % b == 0) System.out.print(b);
        else recursion(b, a % b);
    }
}
