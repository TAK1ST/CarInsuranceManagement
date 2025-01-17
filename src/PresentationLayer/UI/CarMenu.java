/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PresentationLayer.UI;

import static Application.Constant.DateFormat.dateFormat;
import Application.Entity.Car;
import Application.Service.CarService;
import Application.Service.IService;
import DataLayer.FileManagement;
import static PresentationLayer.UI.Menu.getUserChoice;
import Utils.DataInput;
import Utils.validation.ValidCarInput;
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
        int attempts = 0;
        while (attempts < 3) {
            int userChoice = getUserChoice();

            if (userChoice >= 1 && userChoice <= 9) {
                handleOption(userChoice);
                break;
            } else {
                System.out.println("Invalid option. Please enter a valid option [1-9].");
                attempts++;
                if (attempts >= 3) {
                    System.out.println("Too many invalid attempts. Exiting...");
                    return;
                }
            }
        }
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
        try {
            switch (option) {
                case 1:
                    addNewCar();
                    saveData();
                    break;
                case 2:
                    findCarByLicensePlate();
                    service.printList();
                    break;
                case 3:
                    updateCarInformation();

                    break;
                case 4:
                    deleteCarInformation();
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
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please enter a valid option [1-9].");
            }
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

        char distinctCode = licensePlate.charAt(2);
        placeOfRegistration = getDistrictNameByCode(distinctCode);

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

    public void findCarByLicensePlate() {
        try {
            String licensePlate = DataInput.getString("Enter license plate to search: ");
            Car car = ((CarService) service).getCarByLicensePlate(licensePlate);

            if (car != null) {
                System.out.println("Vehicle Details:");
                System.out.println("-----------------------------------------------------");
                System.out.println("License plate: " + car.getLicensePlate());
                System.out.println("Owner: " + car.getCarOwner());
                System.out.println("Phone: " + car.getPhoneNumber());
                System.out.println("Car brand: " + car.getCarBrand());
                System.out.println("Value of vehicle: " + String.format("%,d", car.getPrice()));
                System.out.println("Number of seats: " + car.getNumberOfSeat());
                System.out.println("Registration date: " + dateFormat.format(car.getRegisterDate()));
                System.out.println("-----------------------------------------------------");
            } else {
                System.out.println("No one matches the search criteria!");
            }

            System.out.println("Do you want to search for another car? (yes/no): ");
            String response = DataInput.getString();
            if (response.equalsIgnoreCase("yes")) {
                findCarByLicensePlate();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateCarInformation() {
        try {
            String licensePlate = DataInput.getString("Enter license plate to update: ");
            Car car = ((CarService) service).getCarByLicensePlate(licensePlate);

            if (car != null) {
                System.out.println("Current car information:");
                System.out.println(car);
                System.out.println("\nEnter new information (press Enter to keep current value):");

                String newOwner = DataInput.getString("Enter new car owner (current: " + car.getCarOwner() + "): ");
                if (!newOwner.isEmpty() && ValidCarInput.validateCarOwner(newOwner)) {
                    car.setCarOwner(newOwner);
                }

                String newPhone = DataInput.getString("Enter new phone number (current: " + car.getPhoneNumber() + "): ");
                if (!newPhone.isEmpty() && ValidCarInput.validatePhoneNumber(newPhone)) {
                    car.setPhoneNumber(newPhone);
                }

                String newBrand = DataInput.getString("Enter new car brand (current: " + car.getCarBrand() + "): ");
                if (!newBrand.isEmpty() && ValidCarInput.validateCarBrand(newBrand)) {
                    car.setCarBrand(newBrand);
                }

                String newSeats = DataInput.getString("Enter new number of seats (current: " + car.getNumberOfSeat() + "): ");
                if (!newSeats.isEmpty() && ValidCarInput.validateNumberOfSeats(newSeats)) {
                    car.setNumberOfSeat(newSeats);
                }

                System.out.println("Car information updated successfully!");
                saveData();
            } else {
                System.out.println("This vehicle does not exist.");
            }

            System.out.println("Do you want to update another car? (yes/no): ");
            String response = DataInput.getString();
            if (response.equalsIgnoreCase("yes")) {
                updateCarInformation();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteCarInformation() {
        try {
            String licensePlate = DataInput.getString("Enter license plate to delete: ");
            Car car = ((CarService) service).getCarByLicensePlate(licensePlate);

            if (car != null) {
                // TODO: Add check for insurance statements when that functionality is implemented
                System.out.println("Vehicle Details:");
                System.out.println("-----------------------------------------------------");
                System.out.println("License plate: " + car.getLicensePlate());
                System.out.println("Owner: " + car.getCarOwner());
                System.out.println("Phone: " + car.getPhoneNumber());
                System.out.println("Car brand: " + car.getCarBrand());
                System.out.println("Value of vehicle: " + String.format("%,d", car.getPrice()));
                System.out.println("Number of seats: " + car.getNumberOfSeat());
                System.out.println("Registration date: " + dateFormat.format(car.getRegisterDate()));
                System.out.println("-----------------------------------------------------");

                System.out.print("Are you sure you want to delete this registration? (Y/N): ");
                String confirmation = DataInput.getString();

                if (confirmation.equalsIgnoreCase("Y")) {
                    ((CarService) service).deleteCar(car);
                    System.out.println("The vehicle information has been successfully deleted.");
                    saveData();
                }
            } else {
                System.out.println("This vehicle has not registered yet.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
