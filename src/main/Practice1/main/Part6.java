package main.Practice1.main;

public class Part6 {
    public static void main(String[] args) {
        StringBuilder result = new StringBuilder();
        if(args.length > 0 && !args[0].equals("0")) {
            int[] array = new int[Integer.parseInt(args[0])];
            logic(args, result, array);
        }
    }

    public static void logic(String[] args, StringBuilder result, int[] array) {
        array[0] = 2;
        result.append(array[0]).append(" ");
        int value = 3;
        int count = 1;
        while (array[Integer.parseInt(args[0]) - 1] == 0) {
            for (int i = 0; i < count; i++) {
                if (value % array[i] == 0) break;
                else if (i == count - 1) {
                    array[count] = value;
                    result.append(array[count]).append(" ");
                    if (count < array.length - 1) count++;}
            }
            value++;}
        System.out.print(result.toString().trim());}
}
