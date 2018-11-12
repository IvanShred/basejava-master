package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private int counter;
    private static final Object LOCK = new Object();
    private static final Object LOCK1 = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(java.lang.Thread.currentThread().getName());

        java.lang.Thread thread0 = new java.lang.Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                throw new IllegalStateException();
            }
        };
        thread0.start();

        new java.lang.Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(java.lang.Thread.currentThread().getName() + ", " + java.lang.Thread.currentThread().getState());
            }

            private void inc() {
                synchronized (this) {
//                    counter++;
                }
            }

        }).start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<java.lang.Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            java.lang.Thread thread = new java.lang.Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(mainConcurrency.counter);

        Thread thread = new Thread();
        Thread1 thread1 = new Thread1();
        thread.start();
        thread1.start();
    }

    private synchronized void inc() {
//        synchronized (this) {
//        synchronized (MainConcurrency.class) {
        counter++;
//                wait();
//                readFile
//                ...
//        }
    }

    private static class Thread extends java.lang.Thread {
        public void run() {
            synchronized (LOCK) {
                try {
                    java.lang.Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                System.out.println("thread");
                synchronized (LOCK1) {
                    System.out.println("never print");
                }
            }
        }
    }

    private static class Thread1 extends java.lang.Thread {
        public void run() {
            synchronized (LOCK1) {
                try {
                    java.lang.Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                System.out.println("thread2");
                synchronized (LOCK) {
                    System.out.println("never print");
                }
            }
        }
    }

}
