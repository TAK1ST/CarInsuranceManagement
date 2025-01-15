/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataLayer;

import DataLayer.CarDAO.CarDAO;
import DataLayer.CarDAO.ICarDAO;
import DataLayer.InsuranceDAO.IInsuranceDAO;
import DataLayer.InsuranceDAO.InsuranceDAO;

/**
 *
 * @author asus
 */
public class DAOFactory implements IDAOFactory{
    IFileManagement fileManagement;

    public DAOFactory() {
    }

    public DAOFactory(String inputDataFile) {
        this.fileManagement = new FileManagement(inputDataFile);
    }
    
    @Override
    public ICarDAO carDAO() throws Exception
    {
        return new CarDAO(fileManagement);
    }
    
    @Override
    public IInsuranceDAO insuranceDAO() throws Exception
    {
        return new InsuranceDAO(fileManagement); 
    }
}
