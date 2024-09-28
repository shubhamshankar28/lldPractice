package org.shubhamshankar.concepts;


class Shopper extends Thread {
    static int counter = 0;

    public void run() {
        for(int i=0;i<100000;++i) {
            counter++;
        }
    }
}
public class DataRaceDemo {


    public static void main(String [] args) {
        Thread th1 = new Shopper();
        Thread th2 = new Shopper();
        th1.start();
        th2.start();
        try {
            th1.join();
            th2.join();
            System.out.println(Shopper.counter);
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }
}
