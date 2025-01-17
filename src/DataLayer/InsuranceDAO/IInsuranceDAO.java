package DataLayer.InsuranceDAO;

import DataLayer.IProductDAO;
import java.util.List;

/**
 * Interface for Insurance Data Access Object.
 * @param <Insurance> 
 */
public interface IInsuranceDAO<Insurance> extends IProductDAO<Insurance> {
    void loadDataFromFile() throws Exception;

    List<Insurance> getInsurancesByYear(int year) throws Exception;
    
    List<Insurance> getInsurancesById(String id) throws Exception;

    List<Insurance> getAllInsurances() throws Exception;

    boolean hasInsurance(String licensePlate) throws Exception;

    void saveToFile(String filePath) throws Exception;

}