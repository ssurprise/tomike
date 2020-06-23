package com.skx.tomike.bomberlaboratory.thread;

public class SynchronizedClassTest {

    private int i = 0;
    private final Object LOCK = new Object();

    public synchronized void fun1() {
        i++;
    }

    public void fun2() {
        synchronized (LOCK) {
            i++;
        }
    }

    public void fun3() {
        synchronized (this) {
            i++;
        }
    }
}
