package org.shubhamshankar;

import org.shubhamshankar.payment.CreditcardPaymentProcessor;
import org.shubhamshankar.payment.PaymentProcessor;
import org.shubhamshankar.payment.UpiPaymentProcessor;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class RentalSystem {

    Map<String, PaymentProcessor> allPaymentProcessors;

    Map<String, Car> allCars;
    Map<String, Reservation> allReservations;

    private static RentalSystem rentalSystem = null;
    private RentalSystem() {
        allCars = new HashMap<>();
        allReservations = new HashMap<>();
        allPaymentProcessors = new HashMap<>();
        allPaymentProcessors.put("credit" , new CreditcardPaymentProcessor());
        allPaymentProcessors.put("upi" , new UpiPaymentProcessor());
    }

    public synchronized void addCar(Car car) {
        allCars.put(car.getLicensePlate(), car);
    }

    public static synchronized RentalSystem getRentalSystem() {
       System.out.println("Thread " + Thread.currentThread().getName() + " is getting a rental system");
        if(rentalSystem == null) {
            rentalSystem = new RentalSystem();
        }
        return rentalSystem;
    }

    public synchronized List<Car> allCars(Timestamp currentTimestamp) {
        System.out.println("Thread " + Thread.currentThread().getName() + " is reading all cars in the rental system");

        return allCars.values()
                .stream()
                .filter((car) -> isCarAvailable(car, currentTimestamp, currentTimestamp))
                .collect(Collectors.toList());
    }

    private synchronized boolean isCarAvailable(Car car, Timestamp currentStartTimestamp, Timestamp currentEndTimestamp) {

        System.out.println("Thread " + Thread.currentThread().getName() + " is reading all cars in the rental system");

        return allReservations.values()
                .stream()
                .noneMatch((reservation) -> {
                    if(reservation.getCar().equals(car))  {
                        Timestamp startTimeStamp = reservation.getStartDate();
                        Timestamp endTimeStamp = reservation.getEndDate();

                        long startTime = startTimeStamp.getTime();
                        long endTime = endTimeStamp.getTime();

                        long curStartTime = currentStartTimestamp.getTime();
                        long curEndTime = currentEndTimestamp.getTime();
                        if((curStartTime > endTime) || (curEndTime < startTime)) {
                            return false;
                        }
                        return true;
                    }
                    return false;
                });
    }

    public synchronized Reservation makeReservation(Customer customer, Car car, String startTime, String endTime, String paymentType) {
        System.out.println("Thread " + Thread.currentThread().getName() + " is making a reservation in the rental system for " + startTime + " - " + endTime);

        Timestamp startTimeStamp = Timestamp.valueOf(startTime);
        Timestamp endTimeStamp = Timestamp.valueOf(endTime);

        if(!isCarAvailable(car, startTimeStamp, endTimeStamp)) {
            System.out.println("Thread " + Thread.currentThread().getName() + " cannot make a reservation in the rental system for " + startTime + " - " + endTime);
            return null;
        }

        String reservationId = UUID.randomUUID().toString().substring(0, 10);

        Reservation res = new Reservation(customer, reservationId, startTimeStamp, endTimeStamp, car);

        allPaymentProcessors.get(paymentType).pay(res.computeCost());
        allReservations.put(reservationId, res);
        System.out.println("Thread " + Thread.currentThread().getName() + " has made a reservation in the rental system for " + startTime + " - " + endTime);
        return res;
    }

    public synchronized void deleteReservation(String reservationId) {
        System.out.println("Thread " + Thread.currentThread().getName() + " is deleting a reservation in the rental system");

        allReservations.remove(reservationId);
    }

    public synchronized void updateReservation(String reservationId, Car car, String startTime, String endTime) {
        System.out.println("Thread " + Thread.currentThread().getName() + " is updating a reservation in the rental system");

        Timestamp startTimeStamp = Timestamp.valueOf(startTime);
        Timestamp endTimeStamp = Timestamp.valueOf(endTime);

        if(!isCarAvailable(car, startTimeStamp, endTimeStamp)) {
            return;
        }

        Reservation res = allReservations.get(reservationId);
        res.setCar(car);
        res.setStartDate(startTimeStamp);
        res.setEndDate(endTimeStamp);

        allReservations.put(reservationId, res);
    }

}
