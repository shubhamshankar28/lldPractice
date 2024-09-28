package org.shubhamshankar.concepts;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MathematicalManipulatorUsingLatch extends Thread {

    int turn;
    static int value = 1;
    static Lock lock = new ReentrantLock();

    static CountDownLatch latch = new CountDownLatch(5);

    MathematicalManipulatorUsingLatch(int turn) {
        this.turn = turn;
    }

    public void run() {

        if (turn == 0) {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            value = value + 1;
            lock.unlock();
        } else {
            lock.lock();
            value = value * 2;
            lock.unlock();

            latch.countDown();
        }

    }
}
public class CountDownLatchDemo {
    public static void main(String [] args) {

        List<MathematicalManipulatorUsingLatch> allThreads = new ArrayList<>();
        for(int i=0;i<10;++i){
            MathematicalManipulatorUsingLatch  m =  new MathematicalManipulatorUsingLatch(i%2);
            allThreads.add(m);
            m.start();
        }


        for(int i=0;i<10;++i) {
            try {
                allThreads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(MathematicalManipulatorUsingLatch.value);
    }
}
