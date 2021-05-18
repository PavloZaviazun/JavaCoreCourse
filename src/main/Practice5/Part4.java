package main.Practice5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class Part4 {
    static ArrayList<String> inputData;
    public static void main(final String[] args) {
        inputData = new ArrayList <>();
        try(BufferedReader reader = new BufferedReader(new FileReader("part4.txt"))) {
            while(reader.ready()) {
                String line = reader.readLine();
                inputData.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        multiThreadingCount();
        singleThreadingCount();
    }

    private static void multiThreadingCount() {
        CountDownLatch countDownLatch = new CountDownLatch(inputData.size());
        long start = System.currentTimeMillis();
        int[] results = new int[inputData.size()];
        for(int i = 0; i < inputData.size(); i++) {
            int finalI = i;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    String[] array = inputData.get(finalI).split(" ");
                    results[finalI] = loop(array);
                    countDownLatch.countDown();
                }
            };
            thread.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
        System.out.println(Arrays.stream(results).max().getAsInt());
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void singleThreadingCount() {
        long start = System.currentTimeMillis();
        int[] results = new int[inputData.size()];
        for(int i = 0; i < inputData.size(); i++) {
            String[] array = inputData.get(i).split(" ");
            results[i] = loop(array);
        }
        System.out.println(Arrays.stream(results).max().getAsInt());
        System.out.println(System.currentTimeMillis() - start);
    }

    public static int loop(String[] array) {
        int max = Integer.MIN_VALUE;
        for (int j = 0; j < array.length; j++) {
            int compar = Integer.parseInt(array[j]);
            if (max < compar) max = compar;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        return max;
    }

}

