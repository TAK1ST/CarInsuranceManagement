/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PresentationLayer.UI;

import Application.Constant.DateFormat;
import static Application.Constant.DateFormat.dateFormat;
import Application.Entity.Car;
import Application.Entity.Insurance;
import Application.Service.CarService;
import Application.Service.InsuranceService;
import Utils.DataInput;
import Utils.validation.ValidCarInput;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author asus
 */
public class CRUD {

    // bridge between Application Layer (Service) and PresentationLayer(UI)
    private static final CarService carService = new CarService();
    private static final InsuranceService insuranceService = new InsuranceService();

    public static void addNewCar() throws Exception {
        System.out.println("\nAdd New Car");
        int count = 0;

        // Validate license plate
        String licensePlate;
        do {
            System.out.print("Enter license plate: ");
            licensePlate = DataInput.getString();
            if (!ValidCarInput.validateLicensePlate(licensePlate)) {
                System.out.println("Invalid license plate. Please try again. Ex: 51P123456");
                count++;
            }
            if (count == 3) {
                System.out.println("You will be returned to main menu because you wrong entry 3 times.");
                return;
            }
        } while (!ValidCarInput.validateLicensePlate(licensePlate));

        count = 0;
        // Validate car owner
        String owner;
        do {
            System.out.print("Enter car owner: ");
            owner = DataInput.getString();
            if (!ValidCarInput.validateCarOwner(owner)) {
                System.out.println("Invalid car owner, length's name must in [2,25]. Please try again.");
                count++;

            }
            if (count == 3) {
                System.out.println("You will be returned to main menu because you wrong entry 3 times.");
                return;
            }
        } while (!ValidCarInput.validateCarOwner(owner));

        count = 0;
// Validate phone number
        String phone;
        do {
            System.out.print("Enter phone number: ");
            phone = DataInput.getString();
            if (!ValidCarInput.validatePhoneNumber(phone)) {
                System.out.println("Invalid phone number. Please try again.");
                count++;
            }
            if (count == 3) {
                System.out.println("You will be returned to main menu because you wrong entry 3 times.");
                return;
            }
        } while (!ValidCarInput.validatePhoneNumber(phone));

        count = 0;
        // Validate car brand
        String brand;
        do {
            System.out.print("Enter car brand: ");
            brand = DataInput.getString().toUpperCase();
            if (!ValidCarInput.validateCarBrand(brand)) {
                System.out.println("Invalid car brand. Please try again. Length's branch in [5,12]");
                count++;
            }
            if (count == 3) {
                System.out.println("You will be returned to main menu because you wrong entry 3 times.");
                return;
            }
        } while (!ValidCarInput.validateCarBrand(brand));

        count = 0;
        // Validate vehicle value
        long value;
        do {
            System.out.print("Enter vehicle value: ");
            value = Long.parseLong(DataInput.getString());
            if (!ValidCarInput.validateVehicleValue(value)) {
                System.out.println("Invalid vehicle value. Please try again. Value must over 999");
                count++;
                value = -1;
            }
            if (count == 3) {
                System.out.println("You will be returned to main menu because you wrong entry 3 times.");
                return;
            }
        } while (value <= 0);

        count = 0;
        // Validate registration date
        Date regDate = null;
        do {
            System.out.print("Enter registration date (dd/MM/yyyy): ");
            //help to set hard date
            dateFormat.setLenient(false);
            try {
                regDate = DateFormat.dateFormat.parse(DataInput.getString());
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please try again.  ");
                continue;
            }
            if (!ValidCarInput.validateRegistrationDate(regDate)) {
                System.out.println("Invalid registration date. Format dd/MM/yyyy. Please try again.");
                count++;
                regDate = null;
            }
            if (count == 3) {
                System.out.println("You will be returned to main menu because you wrong entry 3 times.");
                return;
            }
        } while (regDate == null);

        int seats;
        count = 0;
        do {
            System.out.print("Enter number of seats: ");
            try {
                seats = DataInput.getIntegerNumber();
                if (!ValidCarInput.validateNumberOfSeats(seats)) {
                    System.out.println("Invalid number of seats. Please try again.");
                    seats = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                count++;
                seats = -1; // set seats to continue loop
            }
            if (count == 3) {
                System.out.println("You will be returned to main menu because you wrong entry 3 times.");
                return;
            }
        } while (seats <= 0);

        // Registration place is determined by license plate
        String regPlace = determineRegistrationPlace(licensePlate);
        try {
            Car car = new Car(licensePlate, owner, phone, brand, value, regDate, regPlace, seats);
            if (carService.addNew(car)) {
                System.out.println("Car added successfully!");
            } else {
                System.out.println("Failed to add car. Please check the input data.");
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //get location for regPlate in line 160
    private static String determineRegistrationPlace(String licensePlate) {
        char districtCode = licensePlate.charAt(2);
        switch (districtCode) {
            case 'X':
                return "Thu Duc";
            case 'S':
                return "Binh Thanh";
            case 'T':
                return "District 1";
            case 'V':
                return "Go Vap";
            case 'P':
                return "Tan Binh";
            default:
                return "Unknown";
        }
    }

    public static void findCar() {
        System.out.print("\nEnter license plate to search: ");
        String licensePlate = DataInput.getString();

        Car car = carService.findCar(licensePlate);
        if (car != null) {
            displayCarDetails(car);
        } else {
            System.out.println("No car found with this license plate.");
        }
    }

    public static void updateCar() {
        System.out.print("\nEnter license plate to update: ");
        String licensePlate = DataInput.getString();

        Car car = carService.findCar(licensePlate);
        if (car == null) {
            System.out.println("Car not found.");
            return;
        }
        displayCarDetails(car);
        try {
            System.out.println("\nEnter new details (press Enter to keep current value):");

            System.out.print("Car owner [" + car.getCarOwner() + "]: ");
            String owner = DataInput.getString();
            if (!owner.isEmpty()) {
                car.setCarOwner(owner);
            }

            System.out.print("Phone number [" + car.getPhoneNumber() + "]: ");
            String phone = DataInput.getString();
            if (!phone.isEmpty()) {
                car.setPhoneNumber(phone);
            }

            System.out.print("Car brand [" + car.getCarBrand() + "]: ");
            String brand = DataInput.getString();
            if (!brand.isEmpty()) {
                car.setCarBrand(brand);
            }

            System.out.print("Number of seats [" + car.getNumberOfSeats() + "]: ");
            String seats = DataInput.getString();
            if (!seats.isEmpty()) {
                car.setNumberOfSeats(Integer.parseInt(seats));
            }

            if (carService.updateCar(car)) {
                System.out.println("Car information updated successfully!");
            } else {
                System.out.println("Failed to update car information.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Update cancelled.");
        }
    }

    public static void deleteCar() {
        System.out.print("\nEnter license plate to delete: ");
        String licensePlate = DataInput.getString();

        Car car = carService.findCar(licensePlate);
        if (car == null) {
            System.out.println("Car not found.");
            return;
        }

        displayCarDetails(car);

        System.out.print("Are you sure you want to delete this car? (Y/N): ");
        String confirm = DataInput.getString();

        if (confirm.equalsIgnoreCase("Y")) {
            if (carService.deleteCar(licensePlate)) {
                System.out.println("Car deleted successfully!");
            } else {
                System.out.println("Cannot delete car. It may have active insurance.");
            }
        }
    }

    public static void addInsurance() {
        try {
            System.out.println("\nAdd New Insurance");
            String id = insuranceService.generateInsuranceId();

            System.out.print("Enter license plate: ");
            String licensePlate = DataInput.getString();

            if (carService.findCar(licensePlate) == null) {
                System.out.println("Car not found. Please register the car first.");
                return;
            }

            System.out.print("Enter established date (dd/MM/yyyy): ");
            Date estDate = dateFormat.parse(DataInput.getString());

            System.out.print("Enter insurance period (12/24/36 months): ");
            int period = Integer.parseInt(DataInput.getString());

            System.out.print("Enter insurance owner: ");
            String owner = DataInput.getString();

            Car car = carService.findCar(licensePlate);
            long fees = Insurance.calculateInsuranceFees(car.getVehicleValue(), period);

            Insurance insurance = new Insurance(id, licensePlate, estDate, period, fees, owner);

            if (insuranceService.addNew(insurance)) {
                System.out.println("Insurance added successfully!");
                System.out.println("Insurance fees: $" + fees);
            } else {
                System.out.println("Failed to add insurance. Please check the input data.");
            }
        } catch (ParseException | NumberFormatException e) {
            System.out.println("Invalid input format. Please try again.");
        }
    }

    public static void listInsurances() {
        System.out.print("\nEnter year to list insurances: ");
        try {
            int year = Integer.parseInt(DataInput.getString());
            List<Insurance> insurances = insuranceService.getInsurancesByYear(year);
            if (insurances.isEmpty()) {
                System.out.println("No insurance statements found for year " + year);
                return;
            }

            System.out.println("\nReport: INSURANCE STATEMENTS");
            System.out.println("From: 01/01/" + year + " To: 12/31/" + year);
            System.out.println("Sorted by: Insurance fees");
            System.out.println("Sort type: ASC");
            System.out.println("\nNo. Insurance ID   Established Date   License plate   Customer   Period   Fees");
            System.out.println("-------------------------------------------------------------------------");

            int i = 1;
            for (Insurance ins : insurances) {
                System.out.printf("%d.  %-12s  %-16s  %-13s  %-9s  %-7d  $%,d%n",
                        i++,
                        ins.getInsuranceId(),
                        dateFormat.format(ins.getEstablishedDate()),
                        ins.getLicensePlate(),
                        ins.getInsuranceOwner(),
                        ins.getInsurancePeriod(),
                        ins.getInsuranceFees()
                );
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid year format.");
        }
    }

    public static void reportUninsuredCars() {
        List<Car> uninsuredCars = carService.getUninsuredCars();

        if (uninsuredCars.isEmpty()) {
            System.out.println("No uninsured cars found.");
            return;
        }

        System.out.println("\nReport: UNINSURED CARS");
        System.out.println("Sorted by: Vehicle value");
        System.out.println("Sort type: DESC");
        System.out.println("\nNo  . License plate   Registration Date    "
                + "Owner"
                + "                     "
                + "Brand   "
                + "        "
                + "Seats   Value");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        int i = 1;
        for (Car car : uninsuredCars) {
            //%-s|d "-": create space for input
            System.out.printf("%-4d. %-15s %-20s %-25s %-15s %-7d $%,d%n", 
                    i++,
                    car.getLicensePlate(),
                    dateFormat.format(car.getRegistrationDate()),
                    car.getCarOwner(),
                    car.getCarBrand(),
                    car.getNumberOfSeats(),
                    car.getVehicleValue()
            );
        }
    }

    public static void saveAllData() {
        carService.saveData();
        insuranceService.saveData();
        System.out.println("Data saved successfully!");
    }

    public static boolean confirmExit() {
        System.out.print("Are you sure you want to exit? (Y/N): ");
        String confirm = DataInput.getString();

        if (confirm.equalsIgnoreCase("Y")) {
            System.out.print("Do you want to save data before exiting? (Y/N): ");
            String saveConfirm = DataInput.getString();

            if (saveConfirm.equalsIgnoreCase("Y")) {
                carService.saveData();
            }
            return false;
        }
        return true;
    }

    private static void displayCarDetails(Car car) {
        System.out.println("\nCar Details:");
        System.out.println("-----------------------------------------------------");
        System.out.println("License plate    : " + car.getLicensePlate());
        System.out.println("Owner            : " + car.getCarOwner());
        System.out.println("Phone            : " + car.getPhoneNumber());
        System.out.println("Car brand        : " + car.getCarBrand());
        System.out.println("Value of vehicle : $" + String.format("%,d", car.getVehicleValue()));
        System.out.println("Number of seats  : " + car.getNumberOfSeats());
        System.out.println("Registration date: "
                + dateFormat.format(car.getRegistrationDate()));
        System.out.println("-----------------------------------------------------");
    }

}
