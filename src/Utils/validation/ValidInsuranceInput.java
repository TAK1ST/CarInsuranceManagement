/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils.validation;

import Application.Entity.Car;
import Application.Entity.Insurance;
import DataLayer.CarDAO.CarDAO;

/**
 *
 * @author asus
 */
public class ValidInsuranceInput {
    public static  boolean validateInsuranceId(String id) {
        return id != null && id.length() == 4;
    }

    public static  boolean validateInsurancePeriod(int period) {
        return period == 12 || period == 24 || period == 36;
    }

    public static  boolean validateInsuranceOwner(String owner) {
        return owner != null && owner.length() >= 2 && owner.length() <= 25;
    }

    public static  boolean validateInsuranceFees(Insurance insurance, CarDAO car) {
        Car carNew = car.findCarByLicensePlate(insurance.getLicensePlate());
        if (carNew == null) {
            return false;
        }
        long expectedFees = Insurance.calculateInsuranceFees(
            carNew.getVehicleValue(), 
            insurance.getInsurancePeriod()
        );
        
        return insurance.getInsuranceFees() == expectedFees;
    }
}
