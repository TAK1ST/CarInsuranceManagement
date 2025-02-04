/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataLayer;

/**
 *
 * @author asus
 */
public interface IProductDAO <T>{

    boolean add(T obj);

    void saveToFile();

    void loadFromFile();

}
