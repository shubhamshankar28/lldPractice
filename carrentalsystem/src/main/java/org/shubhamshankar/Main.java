package org.shubhamshankar;

import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
class Customers extends Thread {

    Customer customer;
    Car car;
    Integer date;

    Customers(Customer customer, Car car, Integer date) {
        this.customer = customer;
        this.car = car;
        this.date = date;
    }


    public void run() {
        RentalSystem system = RentalSystem.getRentalSystem();
        system.makeReservation(customer,car,"2023-10-"+date+ " 10:06:00", "2023-10-"+ (date+1) + " 10:06:00", ((date%2)) == 0 ? "credit" : "upi");
    }
}

public class Main {
    public static void main(String[] args) {

        RentalSystem system = RentalSystem.getRentalSystem();

        List<Car>  allCars = new ArrayList<>();
        List<Customer> allCustomers = new ArrayList<>();

        for(int i=0;i<10;++i) {

            Car temp = Car.newBuilder().setModel("model-" + i)
                    .setYear("year-" + i)
                    .setType("type-" + i)
                    .setLicensePlate("license+" + i)
                    .build();

            allCars.add(temp);

            system.addCar(temp);
            allCustomers.add(new Customer("name-" + i, i));
        }

        for(int i=0;i<10;++i) {
            new Customers(allCustomers.get(i), allCars.get(0), i+10).start();
        }

    }
}