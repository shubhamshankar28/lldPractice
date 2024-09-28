package org.shubhamshankar.concepts;


import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



class Producer extends Thread {
    private Lock lc;
    private Condition isFull;
    private Condition isEmpty;
    int capacity;
    Queue<String> allStrings;

    Producer(Queue<String> allStrings, Lock lc, Condition isFull, Condition isEmpty, int capacity) {
        this.allStrings = allStrings;
        this.lc = lc;
        this.isEmpty = isEmpty;
        this.isFull = isFull;
        this.capacity = capacity;
    }

    public void run() {
        for(int i=0;i<10;++i) {
            lc.lock();

            try {
                while (allStrings.size() == capacity) {
                    System.out.println("cannot produce string because queue is full");
                    isFull.await();
                }


                String temp = UUID.randomUUID().toString();
                System.out.println("Adding string : " + temp);

                allStrings.add(temp);

                isEmpty.signalAll();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                lc.unlock();
                try {
                    Thread.sleep(5000);
                }
                catch (InterruptedException e) {

                }
            }
        }
    }
}

class Consumer extends Thread {
    private Lock lc;
    private Condition isFull;
    private Condition isEmpty;
    int capacity;
    Queue<String> allStrings;

    Consumer(Queue<String> allStrings, Lock lc, Condition isFull, Condition isEmpty, int capacity) {
        this.allStrings = allStrings;
        this.lc = lc;
        this.isEmpty = isEmpty;
        this.isFull = isFull;
        this.capacity = capacity;
    }

    public void run() {
        for(int i=0;i<10;++i) {
            lc.lock();

            try {
                while (allStrings.size() == 0) {

                    System.out.println("cannot consume string because queue is empty");
                    isEmpty.await();
                }

                String temp = allStrings.poll();
                System.out.println("Processed string : " + temp);


                isFull.signalAll();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                lc.unlock();
            }
        }
    }
}


public class ProducerConsumerIllustration {
    public static void main(String [] args) {
        int capacity = 5;
        Queue<String> q = new ArrayDeque<>(5);
        Lock lc = new ReentrantLock();
        Condition isFull = lc.newCondition();
        Condition isEmpty = lc.newCondition();


        new Producer(q, lc, isFull, isEmpty, 5).start();
        new Consumer(q, lc, isFull, isEmpty, 5).start();

    }
}
