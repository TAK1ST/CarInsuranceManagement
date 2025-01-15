/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DataLayer;

import DataLayer.CarDAO.ICarDAO;
import DataLayer.InsuranceDAO.IInsuranceDAO;

/**
 *
 * @author asus
 */
public interface IDAOFactory{
    ICarDAO carDAO() throws Exception;
    IInsuranceDAO insuranceDAO() throws Exception;
}
