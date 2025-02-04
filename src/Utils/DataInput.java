/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import static Application.Constant.DateFormat.dateFormat;
import static Application.Constant.Regex.REGEX_NUMBER;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author asus
 */
public class DataInput {

    private static final Scanner sc = new Scanner(System.in);

    public static int getIntegerNumber(String displayMessage) throws Exception {
        int number = 0;
        String s;
        System.out.print(displayMessage);
        try {
            s = sc.next();
            number = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            System.out.println("Data invalid.");
        }
        return number;
    }

    public static int getIntegerNumber() throws NumberFormatException {
        int number = 0;
        String s;
        s = sc.nextLine();
        if (!s.matches(REGEX_NUMBER)) {
            throw new NumberFormatException(" Invalid input.");
        } else {
            number = Integer.parseInt(s);
        }
        return number;
    }

    public static String getString(String displayMessage) {
        String s;
        System.out.print(displayMessage);
        s = sc.nextLine();
        return s.trim();
    }

    public static String getString() {
        String s;
        s = sc.nextLine();
        return s;
    }

    public static Date getDate(String displayMessage) {
        System.out.print(displayMessage);
        String dateString = sc.nextLine();
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }
}
