/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Application.Service;

/**
 *
 * @author asus
 * @param <T>
 */
public interface IService<T> {
    //repo
    boolean addNew(T obj) throws Exception;     
    void saveData();
}
