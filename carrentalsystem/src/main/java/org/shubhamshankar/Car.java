package org.shubhamshankar;

public class Car {
    private final String model;
    private final String type;
    private final String year;
    private final String licensePlate;

    private Car(String model, String type, String year, String licensePlate) {
        this.model = model;
        this.type = type;
        this.year = year;
        this.licensePlate = licensePlate;
    }

    static Car.Builder newBuilder() {

        return new Car.Builder();

    }

    Car.Builder toBuilder() {
        return new Builder(model, type, year, licensePlate);
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public String getYear() {
        return year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public static class Builder {
        private  String model;
        private  String type;
        private  String year;
        private String licensePlate;


         private Builder() {
            this.model = "";
            this.type = "";
            this.year = "";
            this.licensePlate = "";
        }

        private Builder(String model, String type, String year, String licensePlate) {
             this.model = model;
             this.year = year;
             this.type = type;
             this.licensePlate = licensePlate;
        }

        public String getModel() {
            return model;
        }

        public Car.Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public String getType() {
            return type;
        }

        public Car.Builder setType(String type) {
            this.type = type;
            return this;
        }

        public String getYear() {
            return year;
        }

        public Car.Builder setYear(String year) {
            this.year = year;
            return this;
        }

        public String getLicensePlate() {
            return licensePlate;
        }

        public Car.Builder setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public Car build() {
             return new Car(model, type, year, licensePlate);
        }
    }
}
