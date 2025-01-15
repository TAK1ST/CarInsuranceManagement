/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PresentationLayer.UI;

//import static Application.Constant.DateFormat.dateFormat;
//import Application.Entity.Car;
//import Application.Service.CarService;
//import Application.Service.IService;
//import java.util.List;
import static Utils.StartUtils.start;

/**
 *
 * @author asus
 */
public class Program {

    public static void main(String[] args) throws Exception {
        start();

//        //Test
//        try {
//            String projectRoot = System.getProperty("user.dir");
//            String carInputFile = projectRoot + "/src/DataLayer/Data/CarData.txt";
//
//            // Initialize service
//            IService<Car> carService = new CarService(carInputFile);
//
//            // Test adding a new car
//            testAddNewCar(carService);
//
//            // Test reading and displaying all cars
//            testDisplayAllCars(carService);
//
//        } catch (Exception e) {
//            System.err.println("Program error: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    private static void testAddNewCar(IService<Car> service) {
//        try {
//            Car newCar = new Car(
//                    "59A-12345",
//                    "Nguyễn Văn A",
//                    "0123456789",
//                    "Toyota",
//                    500000000,
//                    dateFormat.parse("01/01/2024"),
//                    "Hà Nội",
//                    "5"
//            );
//
//            service.add(newCar);
//            System.out.println("Successfully added new car:");
//            System.out.println(newCar);
//            System.out.println("------------------------");
//
//        } catch (Exception e) {
//            System.err.println("Error adding new car: " + e.getMessage());
//        }
//    }
//
//    private static void testDisplayAllCars(IService<Car> service) {
//        try {
//            System.out.println("\nAll cars in system:");
//            System.out.println("------------------------");
//            List<Car> cars = service.getList();
//
//            if (cars.isEmpty()) {
//                System.out.println("No cars found in the system.");
//                return;
//            }
//
//            cars.forEach(car -> System.out.println(car));
//
//        } catch (Exception e) {
//            System.err.println("Error displaying cars: " + e.getMessage());
//        }
    }
}
