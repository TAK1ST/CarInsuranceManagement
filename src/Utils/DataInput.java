/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import static Application.Constant.DateFormat.dateFormat;
import static Application.Constant.Regex.REGEX_CHOICE;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author asus
 */
public class DataInput {

    public static int getIntegerNumber(String displayMessage) throws Exception {
        int number = 0;
        String s;
        System.out.print(displayMessage);
        Scanner sc = new Scanner(System.in);
        try {
            s = sc.next();
            number = Integer.parseInt(s);
        } catch (Exception ex) {
            System.out.println("Data invalid.");
        }
        return number;
    }

    public static int getIntegerNumber() throws Exception {
        int number = 0;
        String s;
        Scanner sc = new Scanner(System.in);
        s = sc.next();
        if (!s.matches(REGEX_CHOICE)) {
            throw new Exception("Data invalid.");
        } else {
            number = Integer.parseInt(s);
        }
        return number;
    }

    public static String getString(String displayMessage) {
        String s;
        Scanner sc = new Scanner(System.in);
        System.out.print(displayMessage);
        s = sc.nextLine();
        return s.trim();
    }

    public static String getString() {
        String s;
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        return s;
    }

    public static Date getDate(String displayMessage) {
        Scanner sc = new Scanner(System.in);
        System.out.print(displayMessage);
        String dateString = sc.nextLine();
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }
}
