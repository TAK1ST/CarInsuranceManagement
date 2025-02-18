/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DataLayer.CarDAO;

import Application.Entity.Car;
import DataLayer.IProductDAO;
import java.util.List;

/**
 *
 * @author asus
 */
public interface ICarDAO extends IProductDAO<Car>{
    Car findCarByLicensePlate(String licensePlate);
    boolean updateCar(Car car);
    boolean deleteCar(String licensePlate);
    List<Car> getAllCars();
    List<Car> getUninsuredCars();
}

