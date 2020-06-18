package com.pianocello.juc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 原子性测试
 *
 * @author PianoCello
 * @date 2019-11-15
 */
public class AtomicDemoTest {

    public static void main(String[] args) {

        AtomicDemo atomicDemo = new AtomicDemo();

        for (int i = 0; i < 10; i++) {
            new Thread(atomicDemo).start();
        }
    }
}

class AtomicDemo implements Runnable {
//    private int number = 0;

    private AtomicInteger number = new AtomicInteger();

    //可重入锁
    private final ReentrantLock lock = new ReentrantLock();

    public int getNumber() {

        /*synchronized (this) {
            this.number++;
        }*/
//        lock.lock();
//        try {
//            this.number++;
//        } finally {
//            lock.unlock();
//        }

        return number.getAndIncrement();
    }

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(getNumber());

    }
}