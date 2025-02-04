/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import PresentationLayer.UI.Menu;

/**
 *
 * @author asus
 */

public class StartUtils {
    public static void start() throws Exception {
        boolean running = true;
        while (running) {
            // use try can implement try again, limit avoid throw exception in getIntegerNumber.
            try {
            Menu.displayMenu();
            int option = DataInput.getIntegerNumber();
            running = Menu.handleChoice(option);
            }  catch(NumberFormatException e)
            {
                System.out.println("Only input number 1-9, please choose again!");
            }
        }
    }
}
