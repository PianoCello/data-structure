package com.hzu.juc;

/**
 * 模拟 CAS 算法
 *
 * @author PianoCello
 * @date 2019-11-21
 */
public class CompareAndSwapTest {
    public static void main(String[] args) {


        final CompareAndSwap cas = new CompareAndSwap();

        for (int i = 0; i < 10; i++) {

            new Thread(() -> {
                int expectedValue = cas.getValue();

                boolean b = cas.compareAndSet(expectedValue, (int) (Math.random() * 101));

                System.out.println(b);

            }).start();

        }

    }

}

class CompareAndSwap {

    private int value;

    //获取当前值
    public synchronized int getValue() {
        return value;
    }

    //比较 交换
    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;

        if (oldValue == expectedValue) {
            this.value = newValue;
        }
        return oldValue; //返回旧值
    }

    //设置值
    public synchronized boolean compareAndSet(int expectedValue, int newValue) {

        return expectedValue == compareAndSwap(expectedValue, newValue);
    }


}