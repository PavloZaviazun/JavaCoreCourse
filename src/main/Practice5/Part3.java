package main.Practice5;

import java.util.concurrent.CountDownLatch;

public class Part3 {

    private int counter;
    private int counter2;
    private int numberOfThreads;
    private int numberOfIterations;
    Object obj = new Object();

    public Part3(int numberOfThreads, int numberOfIterations) {
        this.numberOfThreads = numberOfThreads;
        this.numberOfIterations = numberOfIterations;
    }

    public static void main(final String[] args) {
        Part3 part3 = new Part3(2, 3);
        Thread t1 = new Thread() {
            @Override
            public void run() {
                part3.compare();
            }
        };
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
        Thread t2 = new Thread() {
            @Override
            public void run() {
                part3.compareSync();
            }
        };
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public void compare() {
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);
        for(int j = 0; j < numberOfThreads; j++) {
            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    repeatedCode(countDownLatch);
                }
            };
            thread1.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public void compareSync() {
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);
        for(int j = 0; j < numberOfThreads; j++) {
            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    synchronized (obj) {
                        repeatedCode(countDownLatch);
                    }
                }
            };
            thread2.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private void repeatedCode(CountDownLatch countDownLatch) {
        for (int i = 0; i < numberOfIterations; i++) {
            System.out.println(counter + " == " + counter2);
            counter++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                Thread.currentThread().interrupt();
            }
            counter2++;
        }
        countDownLatch.countDown();
    }
}

//public class Part3 {
//
//    private int counter;
//    private int counter2;
//    private int numberOfThreads;
//    private int numberOfIterations;
//
//    public Part3(int numberOfThreads, int numberOfIterations) {
//        this.numberOfThreads = numberOfThreads;
//        this.numberOfIterations = numberOfIterations;
//    }
//
//    public static void main(final String[] args) {
//        Part3 part3 = new Part3(2, 3);
//        Thread t = new Thread() {
//            public void run() {
//                part3.compare();
//                part3.compareSync();
//            }
//        };
//        t.start();
//        try {
//            t.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(Thread.activeCount());
//    }
//
//    public void compare() {
//        for(int i = 0; i < numberOfThreads; i++) {
//            Thread thread1 = new Thread() {
//                @Override
//                public void run() {
//                    for(int j = 0; j < numberOfIterations; j++) {
//                        System.out.println(counter + " == " + counter2);
//                        counter++;
//                        try {
//                            Thread.sleep(100);
//                        } catch (InterruptedException e) {
//                            System.out.println(e.getMessage());
//                            Thread.currentThread().interrupt();
//                        }
//                        counter2++;
//                    }
//                }
//            };
//            thread1.start();
//            try {
//                thread1.join();
//            } catch (InterruptedException e) {
//                System.out.println(e.getMessage());
//                Thread.currentThread().interrupt();
//            }
//        }
//    }
//
//    public void compareSync() {
//        Object obj = new Object();
//        synchronized (obj) {
//            for(int i = 0; i < numberOfThreads; i++) {
//                Thread thread2 = new Thread() {
//                    @Override
//                    public void run() {
//                        for (int j = 0; j < numberOfIterations; j++) {
//                            System.out.println(counter + " == " + counter2 + Thread.currentThread().getName());
//                            counter++;
//                            try {
//                                Thread.sleep(100);
//                            } catch (InterruptedException e) {
//                                System.out.println(e.getMessage());
//                                Thread.currentThread().interrupt();
//                            }
//                            counter2++;
//                        }
//                    }
//                };
//                thread2.start();
//                try {
//                    thread2.join();
//                } catch (InterruptedException e) {
//                    System.out.println(e.getMessage());
//                    Thread.currentThread().interrupt();
//                }
//            }
//        }
//    }
//}
