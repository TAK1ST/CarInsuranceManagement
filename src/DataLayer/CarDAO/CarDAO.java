/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataLayer.CarDAO;

import Application.Entity.Car;
import DataLayer.IFileManagement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static Application.Constant.DateFormat.dateFormat;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author asus
 */
public class CarDAO implements ICarDAO<Car> {

    IFileManagement<Car> fileManager;
    List<Car> carList = new ArrayList<>();

    public CarDAO() {
        this.carList = new ArrayList<>();
    }

    public CarDAO(IFileManagement<Car> fileManager) throws Exception {
        this.fileManager = fileManager;
        this.carList = new ArrayList<>();
        loadDataFromFile();
    }

    //--------------------------------------------------------------------------------
    @Override
    public void loadDataFromFile() throws Exception {
        try {
            List<String> carData = fileManager.readDataFromFile();

            if (carData == null || carData.isEmpty()) {
                System.out.println("File empty, add data please");
                return;
            }

            for (String car : carData) {
                if (car == null || car.trim().isEmpty()) {
                    continue;
                }

                try {
                    String[] temp = car.split(",");
                    if (temp.length < 8) {
                        System.out.println("Data length input invalid: " + car);
                        continue;
                    }

                    String licensePlate = temp[0].trim();
                    String carOwner = temp[1].trim();
                    String phoneNumber = temp[2].trim();
                    String carBrand = temp[3].trim();
                    int price = Integer.parseInt(temp[4].trim());
                    Date registerDate = dateFormat.parse(temp[5].trim());
                    String placeOfRegistration = temp[6].trim();
                    String numberOfSeat = temp[7].trim();

                    Car newCar = new Car(licensePlate, carOwner, phoneNumber, carBrand,
                            price, registerDate, placeOfRegistration, numberOfSeat);
                    addNew(newCar);
                } catch (NumberFormatException e) {
                    System.out.println("Error Number Format: " + car);
                } catch (Exception e) {
                    System.out.println("Errors: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Error read file: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void addNew(Car car) throws Exception {
        carList.add(car);
    }

    @Override
    public List<Car> getList() throws Exception {
        if (carList.isEmpty()) {
            throw new Exception("Car list is empty.");
        }
        return carList;
    }

@Override
public void saveToFile(String filePath) throws Exception {
    try {
        // List to hold the formatted car data
        List<String> dataToWrite = new ArrayList<>();

        // Format each car's details and add to dataToWrite list
        for (Car car : carList) {
            String line = String.format("%s,%s,%s,%s,%d,%s,%s,%s",
                    car.getLicensePlate(),
                    car.getCarOwner(),
                    car.getPhoneNumber(),
                    car.getCarBrand(),
                    car.getPrice(),
                    dateFormat.format(car.getRegisterDate()),
                    car.getPlaceOfRegistration(),
                    car.getNumberOfSeat()
            );
            dataToWrite.add(line);
        }

        // Use the provided file path
        File file = new File(filePath);

        // Create a BufferedWriter to write to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Write each line from dataToWrite to the file
            for (String line : dataToWrite) {
                writer.write(line);
                writer.newLine(); // Add a newline after each line of data
            }
        } catch (IOException e) {
            throw new Exception("Error while writing data to file: " + e.getMessage(), e);
        }
    } catch (Exception e) {
        throw new Exception("An error occurred while saving data: " + e.getMessage(), e);
    }
}

    public Car getCarByLicensePlate(String licensePlate) {
        if (carList.isEmpty()) {
            System.out.println("List is empty.");
            return null;
        }
        for (Car car : carList) {
            if (car.getLicensePlate().equalsIgnoreCase(licensePlate)) {
                return car;
            }
        }
        return null;
    }
}
