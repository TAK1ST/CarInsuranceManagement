/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Service;

import Application.Entity.Car;
import DataLayer.CarDAO.CarDAO;
import DataLayer.InsuranceDAO.InsuranceDAO;
import Utils.validation.ValidCarInput;
import java.util.List;

/**
 *
 * @author asus
 */
public class CarService implements ICarService {

    private final CarDAO carDAO;
    private final InsuranceDAO insuranceDAO;

    public CarService() {
        this.carDAO = new CarDAO();
        this.insuranceDAO = new InsuranceDAO();
    }

    @Override
    public boolean addNew(Car car) {
        if (!ValidCarInput.validateCar(car)) {
            return false;
        }
        return carDAO.add(car);
    }

    @Override
    public Car findCar(String licensePlate) {
        return carDAO.findCarByLicensePlate(licensePlate);
    }

    @Override
    public boolean updateCar(Car car) {
        if (!ValidCarInput.validateCarUpdate(car)) {
            return false;
        }
        return carDAO.updateCar(car);
    }

    @Override
    public boolean deleteCar(String licensePlate) {
        if (insuranceDAO.hasActiveInsurance(licensePlate)) {
            return false;
        }
        return carDAO.deleteCar(licensePlate);
    }

    @Override
    public List<Car> getUninsuredCars() {
        return carDAO.getUninsuredCars();
    }

    @Override
    public void saveData() {
        carDAO.saveToFile();
    }
}
