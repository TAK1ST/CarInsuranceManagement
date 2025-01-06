/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PresentationLayer.UI;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author asus
 */
public class Program {

    public void displayMenu() {
        List<String> menuItems = Arrays.asList(
                "Add new car",
                "Find a car by license plate",
                "Update car information",
                "Delete car information",
                "Add an insurance statement",
                "List of insurance statements",
                "Report uninsured cars",
                "Save data",
                "Quit"
        );
        System.out.println(
                "==========Car Management==========");
        int count = 1;
        for (String item : menuItems) {
            System.out.println(count++ + "." + item);
        }
    }

    public void handleOption(int option) {
        switch (option) {
            case 1 -> {
            }
            case 2 -> {
            }
            case 3 -> {
            }
            case 4 -> {
            }
            case 5 -> {
            }
            case 6 -> {
            }
            case 7 -> {
            }
            case 8 -> {
            }
            case 9 -> {
            }
            default ->
                System.out.println("Invalid please enter [1-9]");
        }
    }

}
