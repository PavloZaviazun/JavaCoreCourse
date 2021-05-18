package main.Practice5;

public class Part1 {

    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        MyThread2 myThread2 = new MyThread2();
        Thread myThread = new Thread(myThread2);
        myThread1.start();
        try {
            myThread1.join();
            myThread.start();
            myThread.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public static void run() {
        for (int i = 0; i < 4; i++) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    static class MyThread1 extends Thread {
        @Override
        public void run() {
            Part1.run();
        }
    }

    static class MyThread2 implements Runnable {
        @Override
        public void run() {
            Part1.run();
        }
    }
}

