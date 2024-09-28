package org.shubhamshankar.concepts;

import java.util.concurrent.locks.*;


class ShopperWithMutex extends Thread {

    static Integer sampleLock = 0;
    static int counter = 0;
    static Lock mutex = new ReentrantLock();


    // Static needs to be added for this to work. Think why
    // Another option is to make this class singleton and then
    // static keyword can be avoided.
    private static synchronized void addGarlic() {
        counter++;
    }

    public void run() {

        for(int i=0;i<10000000;++i) {
//            mutex.lock();
//            counter++;
//            mutex.unlock();
        synchronized (sampleLock) {
            counter++;
        }
//            addGarlic();
//            System.out.println("Waiting in " + Thread.currentThread().getName());

//            try {
//                Thread.sleep(1000);
//            }
//            catch (InterruptedException e) {
//                System.out.println(e.getMessage());
//            }
        }
    }
}

public class MutualExclusionDemo {

    public static void main(String [] args) {
        Thread th1 = new ShopperWithMutex();
        Thread th2 = new ShopperWithMutex();

//        Thread th1 = ShopperWithMutexSingleton.getMutex();
//        Thread th2 = ShopperWithMutexSingleton.getMutex();


        th1.start();
        th2.start();

        try {
            th1.join();
            th2.join();
            System.out.println(ShopperWithMutex.counter);
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }
}
