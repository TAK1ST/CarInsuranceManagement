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
 * CarDAO class to manage car data.
 */
public class CarDAO implements ICarDAO<Car> {

    private final IFileManagement<Car> fileManager;
    private final List<Car> carList;

    public CarDAO(IFileManagement<Car> fileManager) throws Exception {
        this.fileManager = fileManager;
        this.carList = new ArrayList<>();
        loadDataFromFile();
    }

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
            List<String> dataToWrite = new ArrayList<>();

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

            File file = new File(filePath);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String line : dataToWrite) {
                    writer.write(line);
                    writer.newLine();
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

    public void deleteCar(Car car) throws Exception {
        if (!carList.remove(car)) {
            throw new Exception("Failed to delete car");
        }
    }
}