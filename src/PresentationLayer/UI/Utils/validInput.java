/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PresentationLayer.UI.Utils;

import static Application.Constant.regex.REGEX_CHARACTER;

/**
 *
 * @author asus
 */
public class validInput {
    public int getchoice(String option) throws Exception
    {
        if !(option.matches(REGEX_CHARACTER))
                throw new "Invalid input";
        return Integer.parseInt(option);
    }
}
