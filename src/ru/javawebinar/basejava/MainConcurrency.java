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

        Thread firstThread = new Thread(() -> MainConcurrency.makeDeadlock(LOCK, LOCK1));
        Thread secondThread = new Thread(() -> MainConcurrency.makeDeadlock(LOCK1, LOCK));
        firstThread.start();
        secondThread.start();
    }

    public static void makeDeadlock(Object lock, Object lock1) {
        synchronized (lock) {
            try {
                java.lang.Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            System.out.println(lock);
            synchronized (lock1) {
                System.out.println("never print");
            }
        }
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

}
