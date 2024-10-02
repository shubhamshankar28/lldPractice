package org.shubhamshankar;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class Reservation {
    private Customer customer;
    private String reservationId;

    private Timestamp startDate;
    private Timestamp endDate;

    private Car car;

    public Reservation(Customer customer, String reservationId, Timestamp startDate, Timestamp endDate, Car car) {
        this.customer = customer;
        this.reservationId = reservationId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Double computeCost() {
        long startingTime = startDate.getTime();
        long endingTime = endDate.getTime();

        long diffDays = TimeUnit.MILLISECONDS.toDays(endingTime - startingTime);
        return 0.1*diffDays;
    }
}
