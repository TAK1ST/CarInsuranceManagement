/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Service;

import Application.Entity.Insurance;
import DataLayer.DAOFactory;
import DataLayer.IDAOFactory;
import DataLayer.InsuranceDAO.IInsuranceDAO;
import java.util.List;
/**
 *
 * @author asus
 */
public class InsuranceService implements IService<Insurance> {

    IInsuranceDAO insuranceAction;
    IDAOFactory insuranceDAOFactory;

    public InsuranceService() {
    }

    public InsuranceService(String inputDataFile) throws Exception {
        insuranceDAOFactory = new DAOFactory(inputDataFile);
        this.insuranceAction = insuranceDAOFactory.insuranceDAO();
    }

    public void getInsuranceByLicensePlate() {

    }

    @Override
    public void printList() throws Exception {
    }

    @Override
    public List<Insurance> getList() throws Exception {
        return insuranceAction.getList();
    }

    @Override
    public void add(Insurance obj) throws Exception {
        insuranceAction.addNew(obj);
    }
}
