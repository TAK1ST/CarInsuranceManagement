/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Service;

import Application.Entity.Car;
import DataLayer.CarDAO.CarDAO;
import java.util.List;

/**
 *
 * @author asus
 */
public class CarService implements IService<Car> {

    private final CarDAO carDAO;

    public CarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @Override
    public void printList() throws Exception {
        List<Car> cars = carDAO.getList();
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    @Override
    public List<Car> getList() throws Exception {
        return carDAO.getList();
    }

    @Override
    public void add(Car car) throws Exception {
        carDAO.addNew(car);
    }

    public void saveToFile(String filePath) throws Exception {
        carDAO.saveToFile(filePath);
    }
}
