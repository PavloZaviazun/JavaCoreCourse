package main.Practice5;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class Spam {

    private Thread[] threads;
    private String[] messages;
    private int[] delays;

    public Spam(final String[] messages, final int[] delays) {
        this.messages = messages;
        this.delays = delays;
    }

    public static void main(final String[] args) {
        Spam spam = new Spam(new String[] { "aaaa", "bbbbbbb" ,"ccc", "ddd", "eee"}, new int[] {250, 500, 151, 100, 300});
        spam.start();
        try {
            while(System.in.read() != '\n');
            spam.stop();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void start() {
        threads = new Thread[messages.length];
        for(int i = 0; i < threads.length; i++) {
            threads[i] = new Worker(messages[i], delays[i]);
            threads[i].start();
        }
    }

    public void stop() {
        CountDownLatch countDownLatch = new CountDownLatch(threads.length);
        for (int i = 0; i < threads.length; i++) {
            threads[i].interrupt();
            while (threads[i].getState() != Thread.State.TERMINATED) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
            countDownLatch.countDown();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private static class Worker extends Thread {
        private String message;
        private int delay;
        public Worker(String message, int delay) {
            this.message = message;
            this.delay = delay;
        }
        @Override
        public void run() {
            while (!isInterrupted()) {
                System.out.println(message);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}

