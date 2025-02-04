/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Service;

import Application.Entity.Insurance;
import java.util.List;

/**
 *
 * @author asus
 */

public interface IInsuranceService extends IService<Insurance>
{
    List<Insurance> getInsurancesByYear(int year);
}