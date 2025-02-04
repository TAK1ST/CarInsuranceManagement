/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataLayer.InsuranceDAO;

import Application.Entity.Insurance;
import DataLayer.IProductDAO;
import java.util.List;

/**
 *
 * @author asus
 */
public interface IInsuranceDAO  extends IProductDAO<Insurance>{

    Insurance findById(String insuranceId);

    List<Insurance> getInsurancesByYear(int year);

    boolean hasActiveInsurance(String licensePlate);
}
