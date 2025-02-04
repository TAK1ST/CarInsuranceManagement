/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author asus
 */
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;
    private String licensePlate;
    private String carOwner;
    private String phoneNumber;
    private String carBrand;
    private long vehicleValue;
    private Date registrationDate;
    private String registrationPlace;
    private int numberOfSeats;

    // Constructor
    public Car(String licensePlate, String carOwner, String phoneNumber,
            String carBrand, long vehicleValue, Date registrationDate,
            String registrationPlace, int numberOfSeats) {
        this.licensePlate = licensePlate;
        this.carOwner = carOwner;
        this.phoneNumber = phoneNumber;
        this.carBrand = carBrand;
        this.vehicleValue = vehicleValue;
        this.registrationDate = registrationDate;
        this.registrationPlace = registrationPlace;
        this.numberOfSeats = numberOfSeats;
    }

    // Getters and Setters
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(String carOwner) {
        this.carOwner = carOwner;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCarBrand() {
        return carBrand.toUpperCase();
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public long getVehicleValue() {
        return vehicleValue;
    }

    public void setVehicleValue(long vehicleValue) {
        this.vehicleValue = vehicleValue;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRegistrationPlace() {
        return registrationPlace;
    }

    public void setRegistrationPlace(String registrationPlace) {
        this.registrationPlace = registrationPlace;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "Car{"
                + "licensePlate='" + licensePlate + '\''
                + ", carOwner='" + carOwner + '\''
                + ", phoneNumber='" + phoneNumber + '\''
                + ", carBrand='" + carBrand + '\''
                + ", vehicleValue=" + vehicleValue
                + ", registrationDate=" + registrationDate
                + ", registrationPlace='" + registrationPlace + '\''
                + ", numberOfSeats=" + numberOfSeats
                + '}';
    }
}
