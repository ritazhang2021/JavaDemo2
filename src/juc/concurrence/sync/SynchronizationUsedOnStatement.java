package juc.concurrence.sync;

/**
 * @Author: Rita
 */
public class SynchronizationUsedOnStatement {
    /*c1 c2 never used together*/

    private long c1 = 0;
    private long c2 = 0;
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void m1() {
        synchronized(lock1) {
            c1++;
        }
    }

    public void m2() {
        synchronized(lock2) {
            c2++;
        }
    }
}
