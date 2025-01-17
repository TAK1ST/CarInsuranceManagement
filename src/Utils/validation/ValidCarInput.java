/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils.validation;

import static Application.Constant.Regex.REGEX_PHONENUMBER;
import java.util.Date;

/**
 *
 * @author asus
 */
public class ValidCarInput {

    public static boolean validateLicensePlate(String licensePlate) {
        if (licensePlate == null || licensePlate.isEmpty()) {
            return false;
        }
        if (!licensePlate.substring(0, 2).matches("[5][0-9]")) {
            return false;
        }

        char districtCode = licensePlate.charAt(2);
        if (!isValidDistrictCode(districtCode)) {
            return false;
        }

        if (!Character.isDigit(licensePlate.charAt(3))) {
            return false;
        }

        return licensePlate.substring(4).matches("\\d{5}");
    }

    private static boolean isValidDistrictCode(char districtCode) {
        String validDistricts = "PSTXV";
        return validDistricts.indexOf(districtCode) >= 0;
    }

    public static boolean validateCarOwner(String carOwner) {
        if (carOwner == null || carOwner.isEmpty()) {
            return false;
        }
        return carOwner.length() >= 2 && carOwner.length() <= 25;
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() != 10) {
            return false;
        }
        return phoneNumber.matches(REGEX_PHONENUMBER);
    }

    public static boolean validateCarBrand(String carBrand) {
        if (carBrand == null || carBrand.isEmpty()) {
            return false;
        }
        return carBrand.length() >= 5 && carBrand.length() <= 12;
    }

    public static boolean validateVehicleValue(int value) {
        return value > 999;
    }

    public static boolean validateRegistrationDate(Date registerDate) {
        if (registerDate == null) {
            return false;
        }

        Date currentDate = new Date();
        return registerDate.before(currentDate);
    }

    public static String getDistrictNameByCode(char districtCode) {
        switch (districtCode) {
            case 'P':
                return "Tan Binh";
            case 'S':
                return "Binh Thanh";
            case 'T':
                return "District 1";
            case 'X':
                return "Thu Duc";
            case 'V':
                return "Go Vap";
            default:
                return null;
        }
    }

    public static boolean validateNumberOfSeats(String numberOfSeat) {
        try {
            int seats = Integer.parseInt(numberOfSeat);
            return seats >= 4 && seats <= 36;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
