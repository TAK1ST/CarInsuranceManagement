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
import static Utils.validation.ValidCarInput.*;
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

    public void addNewCar() {
        boolean stop = true;
        while (stop) {
            try {
                Car newCar = getCar();
                service.add(newCar);
                service.getList();
                System.out.println(">>Car added successfully.");
                System.out.println("Do you want to enter another vehicle? (yes/no): ");
                String response = DataInput.getString();
                if (!response.equalsIgnoreCase("yes")) {
                    stop = false;
                }
            } catch (Exception e) {
                System.out.println(">>" + e.getMessage());
            }
        }
    }

    public void handleOption(int option) {
        boolean stop = true;
        try {
            do {
                switch (option) {
                    case 1:
                        addNewCar();
                        saveData();
                        break;
                    case 2:
                        service.printList();
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        saveData();
                        System.out.println("Data saved successfully!");
                        break;
                    case 9:
                        saveData();
                        stop = false;
                        System.out.println("Exiting...");
                    default:
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
            if (service instanceof CarService) {
                CarService carService = (CarService) service;
                carService.saveToFile(FileManagement.carInputFile);
            }

        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    //redundance should feed after complete lab211, should you generic function.
    public Car getCar() throws Exception {
        String licensePlate;
        String carOwner;
        String phoneNumber;
        String carBrand;
        int price;
        Date registerDate;
        String placeOfRegistration;
        String numberOfSeat;

        while (true) {
            licensePlate = DataInput.getString("Enter car license plate:");
            if (validateLicensePlate(licensePlate)) {
                break; 
            } else {
                System.out.println("Invalid license plate. Please try again.");
            }
        }

        while (true) {
            carOwner = DataInput.getString("Enter car owner:");
            if (validateCarOwner(carOwner)) {
                break;
            } else {
                System.out.println("Invalid car owner. Please try again.");
            }
        }

        while (true) {
            phoneNumber = DataInput.getString("Enter phone number:");
            if (validatePhoneNumber(phoneNumber)) {
                break;
            } else {
                System.out.println("Invalid phone number. Please try again.");
            }
        }

        while (true) {
            carBrand = DataInput.getString("Enter car brand:");
            if (validateCarBrand(carBrand)) {
                break;
            } else {
                System.out.println("Invalid car brand. Please try again.");
            }
        }

        while (true) {
            price = DataInput.getIntegerNumber("Enter car price:");
            if (validateVehicleValue(price)) {
                break;
            } else {
                System.out.println("Invalid vehicle price. Please enter a value greater than 999.");
            }
        }

        while (true) {
            registerDate = DataInput.getDate("Enter car register date:");
            if (validateRegistrationDate(registerDate)) {
                break;
            } else {
                System.out.println("Invalid registration date. Please enter a valid date before today.");
            }
        }

        while (true) {
            placeOfRegistration = DataInput.getString("Enter car place of registration:");
            if (validatePlaceOfRegistration(placeOfRegistration, licensePlate)) {
                break;
            } else {
                System.out.println("Invalid place of registration. Please enter a valid district.");
            }
        }
        while (true) {
            numberOfSeat = DataInput.getString("Enter car number of seat:");
            if (validateNumberOfSeats(numberOfSeat)) {
                break;
            } else {
                System.out.println("Invalid number of seats. Please enter a number between 4 and 36.");
            }
        }

        return new Car(licensePlate, carOwner, phoneNumber, carBrand, price, registerDate, placeOfRegistration, numberOfSeat);
    }
}
