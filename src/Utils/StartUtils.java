/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Application.Entity.Car;
import Application.Service.CarService;
import Application.Service.IService;
import DataLayer.CarDAO.CarDAO;
import DataLayer.FileManagement;
import static DataLayer.FileManagement.loadFile;
import DataLayer.IFileManagement;
import PresentationLayer.UI.CarMenu;

/**
 *
 * @author asus
 */
public class StartUtils {

    public static void start() throws Exception {
        try {
            loadFile(FileManagement.carInputFile);

            IFileManagement<Car> fileManager = new FileManagement<>(FileManagement.carInputFile);
            CarDAO carDAO = new CarDAO(fileManager);
            IService<Car> carService = new CarService(carDAO);

            CarMenu menu = new CarMenu(carService);
            menu.displayCarMenu();

        } catch (Exception e) {
            System.out.println("Error starting application: " + e.getMessage());
        }
    }
}