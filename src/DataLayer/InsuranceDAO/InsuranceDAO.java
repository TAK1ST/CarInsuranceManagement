/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataLayer.InsuranceDAO;

import Application.Entity.Insurance;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author asus
 */
public final class InsuranceDAO implements IInsuranceDAO {

    private List<Insurance> insurances;
    public static final String INSURANCE_FILE = "./src/DataLayer/Data/InsuranceData.dat";

    public InsuranceDAO(List<Insurance> insurances) {
        this.insurances = insurances;
    }

    public InsuranceDAO() {
        insurances = new ArrayList<>();
        loadFromFile();
    }

    @Override
    public boolean add(Insurance insurance) {
        if (findById(insurance.getInsuranceId()) != null) {
            return false;
        }
        return insurances.add(insurance);
    }

    @Override
    public Insurance findById(String insuranceId) {
        return insurances.stream()
                .filter(i -> i.getInsuranceId().equals(insuranceId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Insurance> getInsurancesByYear(int year) {
        Calendar cal = Calendar.getInstance();
        return insurances.stream()
                .filter(i -> {
                    cal.setTime(i.getEstablishedDate());
                    System.out.println(cal.get(Calendar.YEAR));
                    return cal.get(Calendar.YEAR) == year;
                })
                .sorted(Comparator.comparingLong(Insurance::getInsuranceFees))
                .collect(Collectors.toList());
    }

    @Override
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(INSURANCE_FILE))) {
            oos.writeObject(insurances);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(INSURANCE_FILE))) {
            insurances = (List<Insurance>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            insurances = new ArrayList<>();
        }
    }

    @Override
    public boolean hasActiveInsurance(String licensePlate) {
        return insurances.stream()
                .anyMatch(i -> i.getLicensePlate().equalsIgnoreCase(licensePlate));
    }
}
