/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DataLayer.InsuranceDAO;

import DataLayer.IProductDAO;
import java.util.List;

/**
 *
 * @author asus
 * @param <Insurance>
 */
public interface IInsuranceDAO<Insurance> extends IProductDAO<Insurance> {
    void loadDataFromFile() throws Exception;

    List<Insurance> getInsurancesByYear(int year) throws Exception;
    
    List<Insurance> getInsurancesById(int year) throws Exception;

    List<Insurance> getAllInsurances() throws Exception;

    boolean hasInsurance(String licensePlate) throws Exception;

    void saveToFile(String filePath) throws Exception;

    void loadDataFromFile(String filePath) throws Exception;
    }
