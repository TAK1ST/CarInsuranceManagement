/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DataLayer.InsuranceDAO;

import DataLayer.IProductDAO;
import static Utils.ValidationUtils.validInsuranceId;

/**
 *
 * @author asus
 */
public interface IInsuranceDAO<Insurance> extends IProductDAO<Insurance>{
    void loadDataFromFile() throws Exception ;   


}
