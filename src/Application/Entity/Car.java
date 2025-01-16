/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Entity;

import static Utils.validation.ValidCarInput.*;
import java.util.Date;

/**
 *
 * @author asus
 */
public class Car {

    private String licensePlate;
    private String carOwner;
    private String phoneNumber;
    private String carBrand;
    private int price;
    private Date registerDate;
    private String placeOfRegistration;
    private String numberOfSeat;

    public Car() {
    }

    public Car(String licensePlate, String carOwner, String phoneNumber, String carBrand, int price, Date registerDate, String placeOfRegistration, String numberOfSeat) {
            this.licensePlate = licensePlate;
            this.carOwner = carOwner;
            this.phoneNumber = phoneNumber;
            this.carBrand = carBrand;
            this.price = price;
            this.registerDate = registerDate;
            this.placeOfRegistration = placeOfRegistration;
            this.numberOfSeat = numberOfSeat;
    }

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
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getPlaceOfRegistration() {
        return placeOfRegistration;
    }

    public void setPlaceOfRegistration(String placeOfRegistration) {
        this.placeOfRegistration = placeOfRegistration;
    }

    public String getNumberOfSeat() {
        return numberOfSeat;
    }

    public void setNumberOfSeat(String numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
    }

    @Override
    public String toString() {
        // Trả về chuỗi với thông tin chi tiết về chiếc xe
        return "Car{"
                + "licensePlate='" + licensePlate + '\''
                + ", carOwner='" + carOwner + '\''
                + ", phoneNumber='" + phoneNumber + '\''
                + ", carBrand='" + carBrand + '\''
                + ", price=" + price
                + ", registerDate=" + registerDate
                + ", placeOfRegistration='" + placeOfRegistration + '\''
                + ", numberOfSeat='" + numberOfSeat + '\''
                + '}';
    }
}
