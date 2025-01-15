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
public interface IProductDAO<T> {
    void addNew(T obj) throws Exception;
    List<T> getList() throws Exception;
}
