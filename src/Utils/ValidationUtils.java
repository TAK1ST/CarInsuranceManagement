/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import static Application.Constant.Regex.REGEX_CHARACTER;
import static Application.Constant.Regex.REGEX_LENGHT_NUM;

/**
 *
 * @author asus
 */
public class ValidationUtils {
    public int getchoice(String option) throws Exception
    {
        if (!option.matches(REGEX_CHARACTER))
                throw new Exception("Invalid Input");
        return Integer.parseInt(option);
    }
    public static boolean validInsuranceId(String InsuranceId)
    {
        boolean status  = true;
        if (InsuranceId.isEmpty())
            status  = false;
        
        if (InsuranceId.matches(REGEX_LENGHT_NUM))
            status  = true;
        return status;
    }
}
