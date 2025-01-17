package Application.Service;

import Application.Constant.Regex;
import Application.Entity.Car;
import Application.Entity.Insurance;
import DataLayer.CarDAO.CarDAO;
import DataLayer.DAOFactory;
import DataLayer.InsuranceDAO.IInsuranceDAO;
import Utils.DataInput;
import static Utils.validation.ValidCarInput.getDistrictNameByCode;
import static Utils.validation.ValidCarInput.validateCarBrand;
import static Utils.validation.ValidCarInput.validateCarOwner;
import static Utils.validation.ValidCarInput.validateLicensePlate;
import static Utils.validation.ValidCarInput.validateNumberOfSeats;
import static Utils.validation.ValidCarInput.validatePhoneNumber;
import static Utils.validation.ValidCarInput.validateRegistrationDate;
import static Utils.validation.ValidCarInput.validateVehicleValue;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class InsuranceService implements IService<Insurance> {

    private final IInsuranceDAO insuranceDAO;
    private final CarDAO carDAO;
    private final Scanner sc;
    private final SimpleDateFormat dateFormat;

    public InsuranceService(String inputDataFile) throws Exception {
        DAOFactory daoFactory = new DAOFactory(inputDataFile);
        this.insuranceDAO = daoFactory.insuranceDAO();
        this.carDAO = (CarDAO) daoFactory.carDAO();
        this.sc = new Scanner(System.in);
        this.dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    }

    @Override
    public void printList() throws Exception {
        List<Insurance> insurances = insuranceDAO.getList();
        if (insurances.isEmpty()) {
            System.out.println("No insurance records found.");
            return;
        }
        insurances.forEach(insurance -> System.out.println(formatInsuranceInfo(insurance)));
    }

    @Override
    public List<Insurance> getList() throws Exception {
        return insuranceDAO.getList();
    }

    @Override
    public void add(Insurance insurance) throws Exception {
        validateInsurance(insurance);
        insuranceDAO.addNew(insurance);
    }

    @Override
    public Insurance getCarByLicensePlate(String licensePlate) throws Exception {
        // This method should probably be in CarService instead
        throw new UnsupportedOperationException("Method not supported in InsuranceService");
    }

    private void validateInsurance(Insurance insurance) throws Exception {
        if (insurance == null) {
            throw new Exception("Insurance cannot be null");
        }
        if (insurance.getCar() == null) {
            throw new Exception("Car information cannot be null");
        }
        if (insurance.getInsuranceId() == null || insurance.getInsuranceId().length() != 4) {
            throw new Exception("Invalid insurance ID format");
        }
        // Add more validation as needed
    }

    public void addInsuranceStatement() throws Exception {
        while (true) {
            try {
                Insurance newInsurance = createInsuranceFromUserInput();
                validateInsurance(newInsurance);
                insuranceDAO.addNew(newInsurance);
                System.out.println("Insurance statement added successfully!");

                if (!continueAdding()) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                if (!retryOnError()) {
                    break;
                }
            }
        }
    }

    private Insurance createInsuranceFromUserInput() throws Exception {
        String insuranceId = inputInsuranceId();
        String licensePlate = inputLicensePlate();
        Date establishedDate = inputEstablishedDate();
        int insurancePeriod = inputInsurancePeriod();
        String insuranceOwner = inputInsuranceOwner();

        Car car = carDAO.getCarByLicensePlate(licensePlate);
        if (car == null) {
            throw new Exception("Car not found with license plate: " + licensePlate);
        }

        double vehicleValue = getVehicleValue(car);
        if (vehicleValue <= 999) {
            throw new Exception("Vehicle value must be greater than 999");
        }

        // Validate all insurance data
        validateInsuranceData(insuranceId, car, establishedDate, insurancePeriod, insuranceOwner);

        double insuranceFee = calculateInsuranceFee(vehicleValue, insurancePeriod);

        return new Insurance(
                insuranceId,
                car,
                establishedDate,
                insurancePeriod,
                String.format("%.2f", insuranceFee),
                insuranceOwner
        );
    }

    private double getVehicleValue(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Car cannot be null");
        }

        // In the Car class, the price field represents the vehicle value
        return car.getPrice();
    }

    private double calculateInsuranceFee(double vehicleValue, int insurancePeriod) {
        double baseRate;
        switch (insurancePeriod) {
            case 12:
                baseRate = 0.025; // 2.5% per month
                break;
            case 24:
                baseRate = 0.020; // 2% per month
                break;
            case 36:
                baseRate = 0.015; // 1.5% per month
                break;
            default:
                throw new IllegalArgumentException("Invalid insurance period. Must be 12, 24, or 36 months.");
        }

        // Calculate total fee based on vehicle value, rate, and period
        return vehicleValue * baseRate * (insurancePeriod / 12.0);
    }

    private String inputInsuranceId() throws Exception {
        while (true) {
            System.out.print("Enter insurance ID (4 characters): ");
            String id = sc.nextLine().trim();

            if (!id.matches("[A-Za-z0-9]{4}")) {
                throw new Exception("Insurance ID must be exactly 4 alphanumeric characters");
            }

            // Check if ID already exists
            List<Insurance> existingInsurances = insuranceDAO.getInsurancesById(id);
            if (!existingInsurances.isEmpty()) {
                throw new Exception("Insurance ID already exists");
            }

            return id.toUpperCase();
        }
    }
// Add method to validate additional insurance data:

    private void validateInsuranceData(String insuranceId, Car car, Date establishedDate,
            int insurancePeriod, String insuranceOwner) throws Exception {
        if (insuranceId == null || !insuranceId.matches("[A-Za-z0-9]{4}")) {
            throw new Exception("Invalid insurance ID format");
        }

        if (car == null) {
            throw new Exception("Car information not found");
        }

        if (establishedDate == null || establishedDate.after(new Date())) {
            throw new Exception("Invalid established date");
        }

        if (insurancePeriod != 12 && insurancePeriod != 24 && insurancePeriod != 36) {
            throw new Exception("Invalid insurance period");
        }

        if (insuranceOwner == null || !insuranceOwner.matches("[a-zA-Z\\s]{2,25}")) {
            throw new Exception("Invalid insurance owner name");
        }
    }

    private String inputLicensePlate() throws Exception {
        while (true) {
            System.out.print("Enter license plate: ");
            String plate = sc.nextLine().trim();

            if (!plate.matches(Regex.REGEX_LICENSE_PLATE)) {
                throw new Exception("Invalid license plate format");
            }

            // Verify the car exists
            Car car = carDAO.getCarByLicensePlate(plate);
            if (car == null) {
                throw new Exception("No car found with this license plate");
            }

            return plate;
        }
    }

    private Date inputEstablishedDate() throws Exception {
        while (true) {
            System.out.print("Enter established date (MM/dd/yyyy): ");
            String dateStr = sc.nextLine().trim();
            try {
                Date date = dateFormat.parse(dateStr);
                Date now = new Date();
                if (date.after(now)) {
                    throw new Exception("Established date cannot be in the future");
                }
                return date;
            } catch (Exception e) {
                throw new Exception("Invalid date format. Please use MM/dd/yyyy");
            }
        }
    }

    private int inputInsurancePeriod() throws Exception {
        while (true) {
            System.out.print("Enter insurance period (12/24/36 months): ");
            try {
                int period = Integer.parseInt(sc.nextLine().trim());
                if (period != 12 && period != 24 && period != 36) {
                    throw new Exception("Insurance period must be 12, 24, or 36 months");
                }
                return period;
            } catch (NumberFormatException e) {
                throw new Exception("Invalid number format");
            }
        }
    }

    private String inputInsuranceOwner() throws Exception {
        while (true) {
            System.out.print("Enter insurance owner name: ");
            String owner = sc.nextLine().trim();

            if (!owner.matches("[a-zA-Z\\s]{2,25}")) {
                throw new Exception("Owner name must be 2-25 characters and contain only letters and spaces");
            }

            return owner;
        }
    }

    private boolean continueAdding() {
        while (true) {
            System.out.print("Do you want to add another insurance statement? (Y/N): ");
            String answer = sc.nextLine().trim().toUpperCase();
            if (answer.equals("Y") || answer.equals("N")) {
                return answer.equals("Y");
            }
            System.out.println("Please enter Y or N");
        }
    }

    private boolean retryOnError() {
        while (true) {
            System.out.print("Would you like to try again? (Y/N): ");
            String answer = sc.nextLine().trim().toUpperCase();
            if (answer.equals("Y") || answer.equals("N")) {
                return answer.equals("Y");
            }
            System.out.println("Please enter Y or N");
        }
    }

    private String formatInsuranceInfo(Insurance insurance) {
        return String.format("Insurance ID: %s | License Plate: %s | Owner: %s | Established: %s | Period: %d months | Fee: $%s",
                insurance.getInsuranceId(),
                insurance.getCar().getLicensePlate(),
                insurance.getInsuranceOwner(),
                dateFormat.format(insurance.getEstablishedDate()),
                insurance.getInsurancePeriod(),
                insurance.getFee());
    }
}
