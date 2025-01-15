/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DataLayer.CarDAO;

import DataLayer.IProductDAO;

/**
 *
 * @author asus
 * @param <Car>
 */
public interface ICarDAO<Car> extends IProductDAO<Car>{
    void loadDataFromFile() throws Exception ;   
    void saveToFile(String fileName) throws Exception;
}
