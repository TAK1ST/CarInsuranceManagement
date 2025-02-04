/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author asus
 */

public class Insurance implements Serializable {
    private static final long serialVersionUID = 1L;
    private String insuranceId;    
    private String licensePlate;   
    private Date establishedDate;  
    private int insurancePeriod;   
    private long insuranceFees;    
    private String insuranceOwner; 

    // Constructor
    public Insurance(String insuranceId, String licensePlate, 
                    Date establishedDate, int insurancePeriod, 
                    long insuranceFees, String insuranceOwner) {
        this.insuranceId = insuranceId;
        this.licensePlate = licensePlate;
        this.establishedDate = establishedDate;
        this.insurancePeriod = insurancePeriod;
        this.insuranceFees = insuranceFees;
        this.insuranceOwner = insuranceOwner;
    }

    // Getters and Setters
    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Date getEstablishedDate() {
        return establishedDate;
    }

    public void setEstablishedDate(Date establishedDate) {
        this.establishedDate = establishedDate;
    }

    public int getInsurancePeriod() {
        return insurancePeriod;
    }

    public void setInsurancePeriod(int insurancePeriod) {
        this.insurancePeriod = insurancePeriod;
    }

    public long getInsuranceFees() {
        return insuranceFees;
    }

    public void setInsuranceFees(long insuranceFees) {
        this.insuranceFees = insuranceFees;
    }

    public String getInsuranceOwner() {
        return insuranceOwner;
    }

    public void setInsuranceOwner(String insuranceOwner) {
        this.insuranceOwner = insuranceOwner;
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "insuranceId='" + insuranceId + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", establishedDate=" + establishedDate +
                ", insurancePeriod=" + insurancePeriod +
                ", insuranceFees=" + insuranceFees +
                ", insuranceOwner='" + insuranceOwner + '\'' +
                '}';
    }

    public static long calculateInsuranceFees(long vehicleValue, int insurancePeriod) {
        long fees = 0;
        switch (insurancePeriod) {
            case 12:
                fees = (long) (vehicleValue * 0.25); // 25% of vehicle value
                break;
            case 24:
                fees = (long) (vehicleValue * 0.20 * 2); // 20% * 2 of vehicle value
                break;
            case 36:
                fees = (long) (vehicleValue * 0.15 * 3); // 15% * 3 of vehicle value
                break;
        }
        return fees;
    }
}