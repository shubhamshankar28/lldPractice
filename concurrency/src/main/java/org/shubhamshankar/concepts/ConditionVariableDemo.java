package org.shubhamshankar.concepts;

import java.util.concurrent.locks.*;

class HungryPeople extends Thread {
    private final String name;
    private Lock lock;

    private static int servings = 11;

    private Condition foodTaken;
    int turn;

    HungryPeople(String name, Lock lock, Condition foodTaken, int turn) {
        this.lock = lock;
        this.name = name;
        this.turn = turn;
        this.foodTaken = foodTaken;
    }

    public void run() {
        while(true) {
            lock.lock();
            try {
                while ((servings % 5 != turn)) {
                    System.out.println("It is not my turn: " + name);
                        foodTaken.await();
                    }

            if(servings > 0) {
                System.out.printf("Consumming serving number: %d by %s\n", servings, name);
                servings--;
            }
            }
            catch (InterruptedException e) {
                System.out.println(e.getStackTrace());
            }
            finally {

                foodTaken.signalAll();
                if (servings == 0) {
                    lock.unlock();
                    break;
                }
                lock.unlock();
            }

        }
    }
}

public class ConditionVariableDemo {
    public static void main(String [] args) {
        Lock lock = new ReentrantLock();
        Condition cond = lock.newCondition();

        for(int i=0;i<5;++i) {
            new HungryPeople("hungry-man " + i, lock, cond, i).start();
        }


    }
}
