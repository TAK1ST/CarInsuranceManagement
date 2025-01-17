
import static Application.Constant.DateFormat.dateFormat;
import Application.Entity.Car;
import Application.Entity.Insurance;
import Application.Service.IService;
import Application.Service.InsuranceService;
import DataLayer.FileManagement;
import PresentationLayer.UI.IMenu;
import Utils.DataInput;
import java.util.List;


public class CarMenu implements IMenu {

    private final IService<Car> service;
    private final InsuranceService insuranceService; // Add this field

    public CarMenu(IService<Car> service) {
        this.service = service;
        try {
            this.insuranceService = new InsuranceService(FileManagement.carInputFile);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize InsuranceService: " + e.getMessage());
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
                    addInsuranceStatement();
                    break;
                case 6:
                    listInsuranceStatements();
                    break;
                case 7:
                    reportUninsuredCars();
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

    // Add new methods to handle insurance-related options
    private void addInsuranceStatement() {
        try {
            insuranceService.addInsuranceStatement();
        } catch (Exception e) {
            System.out.println("Error adding insurance statement: " + e.getMessage());
        }
    }

    private void listInsuranceStatements() {
        try {
            System.out.print("Enter year to list insurance statements (YYYY): ");
            int year = DataInput.getIntegerNumber();

            System.out.println("\nReport : INSURANCE STATEMENTS");
            System.out.println("From : 01/01/" + year + " To: 12/31/" + year);
            System.out.println("Sorted by: Established Date");
            System.out.println("Sort type : ASC");
            System.out.println("\nNo. Insurance Id  Established Date  License plate  Customer  Insurance period  Insurance fees");
            System.out.println("-------------------------------------------------------------------------------");

            List<Insurance> statements = insuranceService.getInsuranceStatementsForYear(year);
            if (statements.isEmpty()) {
                System.out.println("There are no statements in this year");
            } else {
                int count = 1;
                for (Insurance insurance : statements) {
                    System.out.printf("%d   %-11s  %-15s  %-12s  %-8s  %-16d  $%s%n",
                            count++,
                            insurance.getInsuranceId(),
                            dateFormat.format(insurance.getEstablishedDate()),
                            insurance.getCar().getLicensePlate(),
                            insurance.getInsuranceOwner(),
                            insurance.getInsurancePeriod(),
                            insurance.getFee());
                }
            }
        } catch (Exception e) {
            System.out.println("Error listing insurance statements: " + e.getMessage());
        }
    }

    private void reportUninsuredCars() {
        try {
            System.out.println("\nReport: UNINSURED CARS");
            System.out.println("Sorted by : Vehicle value");
            System.out.println("Sort type : DESC");
            System.out.println("\nNo. License plate  Registration Date  Vehicle Owner  Brand  Number of seats  Value of vehicle");
            System.out.println("--------------------------------------------------------------------------------");

            List<Car> uninsuredCars = insuranceService.getUninsuredCars();
            if (uninsuredCars.isEmpty()) {
                System.out.println("No information available");
            } else {
                int count = 1;
                for (Car car : uninsuredCars) {
                    System.out.printf("%d   %-12s  %-16s  %-13s  %-5s  %-14s  $%,d%n",
                            count++,
                            car.getLicensePlate(),
                            dateFormat.format(car.getRegisterDate()),
                            car.getCarOwner(),
                            car.getCarBrand(),
                            car.getNumberOfSeat(),
                            car.getPrice());
                }
            }
        } catch (Exception e) {
            System.out.println("Error generating uninsured cars report: " + e.getMessage());
        }
    }

    @Override
    public void saveData() {
        try {
            if (service instanceof CarService) {
                CarService carService = (CarService) service;
                carService.saveToFile(FileManagement.carInputFile);
            }
            // Also save insurance data
            insuranceService.saveToFile(FileManagement.insuranceInputFile);
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
