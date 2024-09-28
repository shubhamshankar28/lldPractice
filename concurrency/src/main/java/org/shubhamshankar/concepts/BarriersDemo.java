package org.shubhamshankar.concepts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

 class MathematicalManipulator extends Thread {

    int turn;
    static int value = 1;
    static Lock lock = new ReentrantLock();

    static CyclicBarrier barrier = new CyclicBarrier(10);

    MathematicalManipulator(int turn) {
        this.turn = turn;
    }

    public void run() {

            if (turn == 0) {
                try {
                    barrier.await();
                } catch (InterruptedException|BrokenBarrierException e) {
                    e.printStackTrace();
                }
                lock.lock();
                value = value + 1;
                lock.unlock();
            } else {
                lock.lock();
                value = value * 2;
                lock.unlock();
                try {
                    barrier.await();
                } catch (InterruptedException|BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }

    }
}

public class BarriersDemo {



    public static void main(String [] args) {

        List<MathematicalManipulator> allThreads = new ArrayList<>();
        for(int i=0;i<10;++i){
           MathematicalManipulator  m =  new MathematicalManipulator(i%2);
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
        System.out.println(MathematicalManipulator.value);
    }
}
