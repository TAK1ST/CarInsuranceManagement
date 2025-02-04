/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Service;

import Application.Entity.Car;
import java.util.List;

/**
 *
 * @author asus
 */
public interface ICarService extends  IService<Car> {
    Car findCar(String licensePlate);
    boolean updateCar(Car car);
    boolean deleteCar(String licensePlate);
    List<Car> getUninsuredCars();
}
