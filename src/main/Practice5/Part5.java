package main.Practice5;

import java.io.*;

public class Part5 {

    public static void main(final String[] args) {
       writeData();
       try(BufferedReader reader = new BufferedReader(new FileReader("part5.txt"))) {
           while(reader.ready()) {
               System.out.println(reader.readLine());
           }
       }catch (IOException ioe) {
           System.out.println(ioe.getMessage());
       }
    }

    public static void writeData() {
        try(RandomAccessFile raf = new RandomAccessFile("part5.txt", "rw")) {
            for(int i = 0; i < 10; i++) {
                int iFinal = i;
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        for(int j = 0; j < 20; j++) {
                            try {
                                raf.write('0' + iFinal);
                                    Thread.sleep(1);
                            } catch (IOException | InterruptedException e) {
                                System.out.println(e.getMessage());
                                Thread.currentThread().interrupt();
                            }
                        }
                        try {
                            raf.write(System.lineSeparator().getBytes());
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                            Thread.currentThread().interrupt();
                        }
                    }
                };
                thread.start();
                thread.join();
            }
        } catch (InterruptedException | IOException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}

