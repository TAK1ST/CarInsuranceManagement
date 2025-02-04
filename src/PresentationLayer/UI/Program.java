/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PresentationLayer.UI;

import Application.Entity.Car;
import static DataLayer.CarDAO.CarDAO.CAR_FILE;
import static Utils.StartUtils.start;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 *
 * @author asus
 */
public class Program {
    
    public static void main(String[] args) throws Exception {
        start();
        displayCarData(CAR_FILE);
    }

    //create temp display car(can use for insurance)
    public static void displayCarData(String fileName) throws Exception {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<Car> cars = (List <Car>) ois.readObject();
            for (Car car : cars)
            {
                System.out.println(car.getLicensePlate());
                System.out.println(car.getRegistrationDate());
            }
        } catch (IOException | ClassNotFoundException e) {
        }
    }
}
