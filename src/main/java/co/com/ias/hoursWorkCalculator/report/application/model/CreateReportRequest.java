package co.com.ias.hoursWorkCalculator.report.application.model;

import co.com.ias.hoursWorkCalculator.commons.TechnicianIdentityNumber;
import co.com.ias.hoursWorkCalculator.commons.operation.ApplicationRequest;

import java.util.Objects;

public class CreateReportRequest implements ApplicationRequest {
    private String technicianIdentity ;
    private String reportIdentityNumber;
    private String  hourInit;
    private String  dateInit;
    private String  hourFinish;
    private String  dateFinish;



    public String getNumWeek() {
        return numWeek;
    }

    public void setNumWeek(String numWeek) {
        this.numWeek = numWeek;
    }

    private String  numWeek;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateReportRequest that = (CreateReportRequest) o;
        return Objects.equals(technicianIdentity, that.technicianIdentity) && Objects.equals(reportIdentityNumber, that.reportIdentityNumber) && Objects.equals(hourInit, that.hourInit) && Objects.equals(dateInit, that.dateInit) && Objects.equals(hourFinish, that.hourFinish) && Objects.equals(dateFinish, that.dateFinish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(technicianIdentity, reportIdentityNumber, hourInit, dateInit, hourFinish, dateFinish);
    }

    public String getTechnicianIdentity() {
        return technicianIdentity;
    }

    public void setTechnicianIdentity(String technicianIdentity) {
        this.technicianIdentity = technicianIdentity;
    }

    public String getReportIdentityNumber() {
        return reportIdentityNumber;
    }

    public void setReportIdentityNumber(String reportIdentityNumber) {
        this.reportIdentityNumber = reportIdentityNumber;
    }

    public String getHourInit() {
        return hourInit;
    }

    public void setHourInit(String hourInit) {
        this.hourInit = hourInit;
    }

    public String getDateInit() {
        return dateInit;
    }

    public void setDateInit(String dateInit) {
        this.dateInit = dateInit;
    }

    public String getHourFinish() {
        return hourFinish;
    }

    public void setHourFinish(String hourFinish) {
        this.hourFinish = hourFinish;
    }

    public String getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(String dateFinish) {
        this.dateFinish = dateFinish;
    }

    @Override
    public String toString() {
        return "CreateReportRequest{" +
                "technicianIdentity='" + technicianIdentity + '\'' +
                ", reportIdentityNumber='" + reportIdentityNumber + '\'' +
                ", hourInit='" + hourInit + '\'' +
                ", dateInit='" + dateInit + '\'' +
                ", hourFinish='" + hourFinish + '\'' +
                ", dateFinish='" + dateFinish + '\'' +
                ", numWeek='" + numWeek + '\'' +

                '}';
    }

    public CreateReportRequest(String reportIdentityNumber, String technicianIdentity, String hourInit, String dateInit, String hourFinish, String dateFinish, String numWeek) {
        this.reportIdentityNumber = reportIdentityNumber;

        this.technicianIdentity = technicianIdentity;
        this.hourInit = hourInit;
        this.dateInit = dateInit;
        this.hourFinish = hourFinish;
        this.dateFinish = dateFinish;
        this.numWeek = numWeek;
    }

}