package main.Practice5;

import java.io.IOException;
import java.io.InputStream;

public class Part2 {

    public static void main(final String[] args) {
        InputStream initial =  System.in;
        System.setIn(new Imitation());
        Thread t = new Thread() {
            @Override
            public void run() {
                Spam.main(null);
            }
        };
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
        System.setIn(initial);
    }

    static class Imitation extends InputStream {
        @Override
        public int read() throws IOException {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                Thread.currentThread().interrupt();
            }
            return '\n';
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            byte[] bytes = System.lineSeparator().getBytes();
            return super.read(bytes, 0, bytes.length);
        }
    }

}

