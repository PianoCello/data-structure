package com.hzu.juc;

/**
 * @author PianoCello
 * @date 2019-11-15
 */
public class VolatileTest {

    public static void main(String[] args) {

        NumberTest test = new NumberTest();
        Thread thread = new Thread(test);
        thread.start();

        /*
        while (true) {
            if (test.isFlag()) {
                System.out.println("------三生三世十里桃花-----");
                break;
            }
        }*/

        while (true) {
            if (test.isFlag()) {
                System.out.println("-------三生三世十里桃花-------");
                break;
            }
        }

    }
}

class NumberTest implements Runnable {

    private volatile boolean flag = false;
//    private boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println(" I'm true ");
    }
}
