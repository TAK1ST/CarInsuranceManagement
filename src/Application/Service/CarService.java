/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Service;

import static Application.Constant.DateFormat.dateFormat;
import Application.Entity.Car;
import DataLayer.CarDAO.CarDAO;
import Utils.DataInput;
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

    @Override
    public Car getCarByLicensePlate(String licensePlate) {
        return carDAO.getCarByLicensePlate(licensePlate);
    }

    public void deleteCar(Car car) throws Exception {
        carDAO.deleteCar(car);
    }

    public void deleteCarInformation() {
        try {
            String licensePlate = DataInput.getString("Enter license plate to delete: ");
            // Thay vì dùng service, dùng trực tiếp this vì đang ở trong CarService
            Car car = this.getCarByLicensePlate(licensePlate);

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

                System.out.print("Are you sure you want to delete this registration? (Y/N): ");
                String confirmation = DataInput.getString();

                if (confirmation.equalsIgnoreCase("Y")) {
                    // Thay vì ép kiểu service, dùng trực tiếp this
                    this.deleteCar(car);
                    System.out.println("The vehicle information has been successfully deleted.");
                    // Cần thêm method saveData() hoặc gọi saveToFile()
                    this.saveToFile("cars.txt"); // Hoặc tên file tương ứng
                }
            } else {
                System.out.println("This vehicle has not registered yet.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
