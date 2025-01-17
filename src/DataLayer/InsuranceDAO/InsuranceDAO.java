package DataLayer.InsuranceDAO;

import Application.Constant.DateFormat;
import static Application.Constant.DateFormat.dateFormat;
import Application.Entity.Car;
import Application.Entity.Insurance;
import DataLayer.CarDAO.CarDAO;
import DataLayer.IFileManagement;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public final class InsuranceDAO implements IInsuranceDAO<Insurance> {

    private final IFileManagement<Insurance> fileManagement;
    private final List<Insurance> insuranceList;
    private final CarDAO carDAO;

    public InsuranceDAO(IFileManagement<Insurance> fileManagement, CarDAO carDAO) throws Exception {
        this.fileManagement = fileManagement;
        this.insuranceList = new ArrayList<>();
        this.carDAO = carDAO;
        loadDataFromFile();
    }

    public InsuranceDAO(IFileManagement<Insurance> fileManagement) throws Exception {
        this.fileManagement = fileManagement;
        this.insuranceList = new ArrayList<>();
        loadDataFromFile();
    }

    //--------------------------------------------------------------------------------
    @Override
    public void loadDataFromFile() throws Exception {
        try {
            List<String> insuranceData = fileManagement.readDataFromFile();

            if (insuranceData == null || insuranceData.isEmpty()) {
                System.out.println("File empty, add data please");
                return;
            }

            for (String insurance : insuranceData) {
                if (insurance == null || insurance.trim().isEmpty()) {
                    continue;
                }

                try {
                    String[] temp = insurance.split(",");
                    if (temp.length < 8) {
                        System.out.println("Data length input invalid: " + insurance);
                        continue;
                    }
                    String insuranceId = temp[0].trim();
                    Date establishedDate = dateFormat.parse(temp[1].trim());
                    int insurancePeriod = Integer.parseInt(temp[2].trim());
                    String fee = temp[3].trim();
                    String insuranceOwner = temp[4].trim();

                    Insurance newInsurance = new Insurance(insuranceId, getCarByLicensePlate(insurance), establishedDate, insurancePeriod, fee, insuranceOwner);

                    addNew(newInsurance);
                } catch (NumberFormatException e) {
                    System.out.println("Error Number Format: " + insurance);
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
    public void saveToFile(String filePath) throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Insurance insurance : insuranceList) {
                String line = formatInsuranceLine(insurance);
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new Exception("Error saving to file: " + e.getMessage());
        }
    }

    @Override
    public void addNew(Insurance insurance) throws Exception {
        insuranceList.add(insurance);
    }

    @Override
    public List<Insurance> getInsurancesByYear(int year) throws Exception {
        if (year < 1900 || year > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new Exception("Invalid year: " + year);
        }

        List<Insurance> result = insuranceList.stream()
                .filter(insurance -> {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(insurance.getEstablishedDate());
                    return cal.get(Calendar.YEAR) == year;
                })
                .sorted(Comparator.comparing(Insurance::getEstablishedDate))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new Exception("No insurances found for year: " + year);
        }

        return result;
    }

    public List<Insurance> getInsurancesById(String id) throws Exception {
        if (id == null || id.trim().isEmpty()) {
            throw new Exception("Insurance ID cannot be empty");
        }

        List<Insurance> result = insuranceList.stream()
                .filter(insurance -> insurance.getInsuranceId().contains(id.trim()))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new Exception("No insurances found with ID containing: " + id);
        }

        return result;
    }

    @Override
    public List<Insurance> getAllInsurances() throws Exception {
        if (insuranceList.isEmpty()) {
            throw new Exception("No insurances found in the system");
        }
        return new ArrayList<>(insuranceList);
    }

    @Override
    public boolean hasInsurance(String licensePlate) throws Exception {
        if (licensePlate == null || licensePlate.trim().isEmpty()) {
            throw new Exception("License plate cannot be empty");
        }

        return insuranceList.stream()
                .anyMatch(insurance
                        -> insurance.getCar().getLicensePlate()
                        .equalsIgnoreCase(licensePlate.trim())
                );
    }

    private Insurance parseInsuranceLine(String line) throws Exception {
        String[] parts = line.split(",");
        if (parts.length < 6) {
            throw new Exception("Invalid line format");
        }

        try {
            String insuranceId = parts[0].trim();
            String licensePlate = parts[1].trim();
            Date establishedDate = DateFormat.dateFormat.parse(parts[2].trim());
            int insurancePeriod = Integer.parseInt(parts[3].trim());
            String fee = parts[4].trim();
            String insuranceOwner = parts[5].trim();

            // Giả sử có một phương thức để lấy thông tin Car từ licensePlate
            Car car = getCarByLicensePlate(licensePlate);

            return new Insurance(
                    insuranceId,
                    car,
                    establishedDate,
                    insurancePeriod,
                    fee,
                    insuranceOwner
            );
        } catch (ParseException e) {
            throw new Exception("Error parsing date: " + e.getMessage());
        } catch (NumberFormatException e) {
            throw new Exception("Error parsing number: " + e.getMessage());
        }
    }

    private String formatInsuranceLine(Insurance insurance) {
        return String.format("%s,%s,%s,%d,%s,%s",
                insurance.getInsuranceId(),
                insurance.getCar().getLicensePlate(),
                DateFormat.dateFormat.format(insurance.getEstablishedDate()),
                insurance.getInsurancePeriod(),
                insurance.getFee(),
                insurance.getInsuranceOwner()
        );
    }

    private Car getCarByLicensePlate(String licensePlate) throws Exception {
        // Implementation to get car details
        return carDAO.getCarByLicensePlate(licensePlate);
    }
}
