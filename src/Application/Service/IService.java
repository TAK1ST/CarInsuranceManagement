/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Application.Service;

import java.util.List;

/**
 *
 * @author asus
 * @param <T>
 */
public interface IService<T> {
    //repo
    void printList() throws Exception ;   
    List<T> getList() throws Exception;    
    void add(T obj) throws Exception;     
}
