/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataLayer.InsuranceDAO;

import Application.Constant.DateFormat;
import Application.Entity.Car;
import Application.Entity.Insurance;
import DataLayer.CarDAO.CarDAO;
import DataLayer.IFileManagement;
import static Utils.validation.ValidationUtils.validInsuranceId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author asus
 */
public class InsuranceDAO implements IInsuranceDAO<Insurance> {

    IFileManagement<Insurance> fileManagerment;
    public List<Insurance> insuranceList = new ArrayList<>();

    public InsuranceDAO() {
    }

    public InsuranceDAO(IFileManagement fileManagerment) throws Exception {
        this.fileManagerment = fileManagerment;
        loadDataFromFile();
    }

    public void loadDataFromFile() throws Exception {
        String insuranceId;
        Car car;
        Date establishedDate;
        int insurancePeriod;
        String fee;
        String insuranceOwner;

        List<String> insuranceData = fileManagerment.readDataFromFile();

        CarDAO carDAO = new CarDAO();

        for (String insurance : insuranceData) {
            List<String> temp = Arrays.asList(insurance.split(","));
            insuranceId = temp.get(0);
            car = carDAO.getCarByLicensePlate(temp.get(0));
            establishedDate = DateFormat.dateFormat.parse(temp.get(1));
            insurancePeriod = Integer.parseInt(temp.get(2));
            fee = temp.get(3);
            insuranceOwner = temp.get(4);
            Insurance newInsurance = new Insurance(
                    insuranceId,
                     car,
                     establishedDate,
                     insurancePeriod,
                     fee,
                     insuranceOwner);
            addNew(newInsurance);
        }
    }

    public void addNew(Insurance insurance) throws Exception {
        insuranceList.add(insurance);
    }

    public List<Insurance> getList() throws Exception {
        if (insuranceList.isEmpty()) {
            throw new Exception("Insurance list is empty.");
        }
        return insuranceList;
    }
}
