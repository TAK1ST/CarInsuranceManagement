package DataLayer.CarDAO;

import Application.Entity.Car;
import DataLayer.InsuranceDAO.InsuranceDAO;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.stream.Collectors;

public final class CarDAO implements ICarDAO {

    private List<Car> cars;
    public static final String CAR_FILE = "./src/DataLayer/Data/CarData.dat";

    public CarDAO() {
        cars = new ArrayList<>();
        loadFromFile();
    }

    @Override
    public boolean add(Car car) {
        if (findCarByLicensePlate(car.getLicensePlate()) != null) {
            return false;
        }
        return cars.add(car);
    }

    @Override
    public Car findCarByLicensePlate(String licensePlate) {
        return cars.stream()
                .filter(c -> c.getLicensePlate().equals(licensePlate))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateCar(Car car) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getLicensePlate().equals(car.getLicensePlate())) {
                cars.set(i, car);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteCar(String licensePlate) {
        return cars.removeIf(c -> c.getLicensePlate().equals(licensePlate));
    }

    @Override
    public List<Car> getAllCars() {
        return new ArrayList<>(cars);
    }

    @Override
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(CAR_FILE))) {
            oos.writeObject(cars);
            System.out.println("Successfully saved " + cars.size() + " cars to file.");
        } catch (IOException e) {
            System.err.println("Error saving cars to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(CAR_FILE))) {
            cars = (List<Car>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            cars = new ArrayList<>();
        }
    }

    @Override
    public List<Car> getUninsuredCars() {
        return cars.stream()
                .filter(car -> !hasInsurance(car.getLicensePlate()))
                .sorted((c1, c2) -> Long.compare(c2.getVehicleValue(), c1.getVehicleValue()))
                .collect(Collectors.toList());
    }

    private boolean hasInsurance(String licensePlate) {
        InsuranceDAO insuranceDAO = new InsuranceDAO();
        return insuranceDAO.hasActiveInsurance(licensePlate);
    }
}
