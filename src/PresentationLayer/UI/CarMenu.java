/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PresentationLayer.UI;

import Application.Entity.Car;
import Application.Service.CarService;
import Application.Service.IService;
import DataLayer.FileManagement;
import static PresentationLayer.UI.Menu.getUserChoice;
import Utils.DataInput;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author asus
 */
public class CarMenu implements IMenu {

    private final IService<Car> service;

    public CarMenu(IService<Car> service) {
        this.service = service;
    }

    public void displayCarMenu() {
        List<String> menuItems = Arrays.asList(
                "Add new car",
                "Find a car by license plate",
                "Update car information",
                "Delete car information",
                "Add an insurance statement",
                "List of insurance statements",
                "Report uninsured cars",
                "Save data",
                "Quit"
        );
        System.out.println(
                "==========Car Management==========");
        int count = 1;
        for (String item : menuItems) {
            System.out.println(count++ + "." + item);
        }
        handleOption(getUserChoice());
    }

    public Car getCar() throws Exception {
        String licensePlate = DataInput.getString("Enter car license plate:");
        String carOwner = DataInput.getString("Enter car owner:");
        String phoneNumber = DataInput.getString("Enter phoneNumber:");
        String carBrand = DataInput.getString("Enter car brand:");
        int price = DataInput.getIntegerNumber("Enter car price:");
        Date registerDate = DataInput.getDate("Enter car register date:");
        String placeOfRegistration = DataInput.getString("Enter car place of registration:");
        String numberOfSeat = DataInput.getString("Enter car number of seat:");
        return new Car(licensePlate, carOwner, phoneNumber,
                carBrand, price, registerDate, placeOfRegistration, numberOfSeat);
    }

    public void addNewCar() {
        try {
            Car newCar = getCar();
            service.add(newCar);
            service.getList();
            System.out.println(">>Car added successfully.");
        } catch (Exception e) {
            System.out.println(">>" + e.getMessage());
        }
    }

    public void handleOption(int option) {
        boolean stop = true;
    try {
        do {
            switch (option) {
                case 1 -> { 
                    addNewCar(); 
                    saveData(); // save after adding
                }
                case 2 -> { service.printList(); }
                case 3 -> {}
                case 4 -> {}
                case 5 -> {}
                case 6 -> {}
                case 7 -> {}
                case 8 -> { 
                    saveData();
                    System.out.println("Data saved successfully!");
                }
                case 9 -> {
                    saveData(); // save before exit
                    stop = false;
                    System.out.println("Exiting...");
                }
                default ->
                    System.out.println("Invalid please enter [1-9]");
            }
            if (stop) {
                displayCarMenu();
                option = getUserChoice();
            }
        } while (stop);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void saveData() {
        try {
            if (service instanceof CarService carService) {
                carService.saveToFile(FileManagement.carInputFile);
            }
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
