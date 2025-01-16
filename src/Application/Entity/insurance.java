
package Application.Entity;

import Utils.validation.ValidationUtils;
import java.util.Date;

/**
 *
 * @author asus
 */
public class Insurance {
  private String insuranceId;  
  private Car car;  
  private Date establishedDate;  
  private int insurancePeriod;  
  private String fee;  
  private String insuranceOwner; 

    public Insurance() {
    }

    public Insurance(String insuranceId, Car car, Date establishedDate, int insurancePeriod, String fee, String insuranceOwner) {
        this.insuranceId = insuranceId;
        this.car = car;
        this.establishedDate = establishedDate;
        this.insurancePeriod = insurancePeriod;
        this.fee = fee;
        this.insuranceOwner = insuranceOwner;
    }
  
    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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

    public String getfee() {
        return fee;
    }

    public void setfee(String fee) {
        this.fee = fee;
    }

    public String getInsuranceOwner() {
        return insuranceOwner;
    }

    public void setInsuranceOwner(String insuranceOwner) {
        this.insuranceOwner = insuranceOwner;
    } 
}
