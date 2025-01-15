/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataLayer;

import java.util.List;

/**
 *
 * @author asus
 * @param <T>
 */
public interface IFileManagement<T> {
    List <String> readDataFromFile() throws Exception;
    void writeDataToFile (List<String> data) throws Exception;
}
