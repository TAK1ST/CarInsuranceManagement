/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Constant;

/**
 *
 * @author asus
 */
public class Regex {
    public static final String REGEX_LENGHT = "^.{1,9}$";
    public static final String REGEX_NUMBER = "\\d{1,10}";
    public static final String REGEX_PHONENUMBER = "^(09|03|07|08|05)\\d{8}$";
    public static final String REGEX_LICENSE_PLATE = "^[5][0-9][P|S|X][1-9][0-9]{5}$";
    public static final String REGEX_DISTINCT = "[PSX]";
}

