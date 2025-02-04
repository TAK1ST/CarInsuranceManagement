/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Service;

import Application.Entity.Insurance;
import DataLayer.CarDAO.CarDAO;
import DataLayer.InsuranceDAO.InsuranceDAO;
import static Utils.validation.ValidInsuranceInput.validateInsuranceFees;
import static Utils.validation.ValidInsuranceInput.validateInsuranceId;
import static Utils.validation.ValidInsuranceInput.validateInsuranceOwner;
import static Utils.validation.ValidInsuranceInput.validateInsurancePeriod;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author asus
 */
public class InsuranceService implements IInsuranceService {

    private final InsuranceDAO insuranceDAO;
    private final CarDAO carDAO;

    public InsuranceService() {
        this.insuranceDAO = new InsuranceDAO();
        this.carDAO = new CarDAO();
    }

    @Override
    public boolean addNew(Insurance insurance) {
        if (!validateInsurance(insurance)) {
            return false;
        }
        return insuranceDAO.add(insurance);
    }

    @Override
    public List<Insurance> getInsurancesByYear(int year) {
        return insuranceDAO.getInsurancesByYear(year);
    }

    @Override
    public void saveData() {
        insuranceDAO.saveToFile();
    }
    //auto generateInsuranceId
    public String generateInsuranceId()
    {        
            return String.format("%04d",    getRandomNumber());
    }
//create number by using ThreadLocalRandom
    public static int getRandomNumber() {
        return ThreadLocalRandom.current().nextInt(0, 10000);
    }

    private boolean validateInsurance(Insurance insurance) {
        return validateInsuranceId(insurance.getInsuranceId())
                && validateInsurancePeriod(insurance.getInsurancePeriod())
                && validateInsuranceOwner(insurance.getInsuranceOwner())
                && validateInsuranceFees(insurance, carDAO);
    }
}
