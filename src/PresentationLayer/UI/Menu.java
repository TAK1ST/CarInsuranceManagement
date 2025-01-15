/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PresentationLayer.UI;

import Application.Service.IService;
import Utils.DataInput;

/**
 *
 * @author asus
 */
public class Menu {
    public static int getUserChoice()
    {
        int number = 0;
        try {
            number = DataInput.getIntegerNumber("Select:");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return number;
    }
    
        public static void manageCar(IService service) {
        CarMenu empMenu = new CarMenu(service);
        empMenu.displayCarMenu();
    }
}
