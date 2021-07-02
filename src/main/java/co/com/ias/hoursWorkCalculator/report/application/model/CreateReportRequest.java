package co.com.ias.hoursWorkCalculator.report.application.model;

import co.com.ias.hoursWorkCalculator.commons.operation.ApplicationRequest;

import java.util.Objects;

public class CreateReportRequest implements ApplicationRequest {
    private String technicianIdentity ;
    private String reportdentityNumber;
    private String  hourInit;
    private String  dateInit;
    private String  hourFinish;
    private String  dateFinish;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateReportRequest that = (CreateReportRequest) o;
        return Objects.equals(technicianIdentity, that.technicianIdentity) && Objects.equals(reportdentityNumber, that.reportdentityNumber) && Objects.equals(hourInit, that.hourInit) && Objects.equals(dateInit, that.dateInit) && Objects.equals(hourFinish, that.hourFinish) && Objects.equals(dateFinish, that.dateFinish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(technicianIdentity, reportdentityNumber, hourInit, dateInit, hourFinish, dateFinish);
    }

    public String getTechnicianIdentity() {
        return technicianIdentity;
    }

    public void setTechnicianIdentity(String technicianIdentity) {
        this.technicianIdentity = technicianIdentity;
    }

    public String getReportdentityNumber() {
        return reportdentityNumber;
    }

    public void setReportdentityNumber(String reportdentityNumber) {
        this.reportdentityNumber = reportdentityNumber;
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
                ", reportdentityNumber='" + reportdentityNumber + '\'' +
                ", hourInit='" + hourInit + '\'' +
                ", dateInit='" + dateInit + '\'' +
                ", hourFinish='" + hourFinish + '\'' +
                ", dateFinish='" + dateFinish + '\'' +
                '}';
    }

    public CreateReportRequest(String technicianIdentity, String reportdentityNumber, String hourInit, String dateInit, String hourFinish, String dateFinish) {
        this.technicianIdentity = technicianIdentity;
        this.reportdentityNumber = reportdentityNumber;
        this.hourInit = hourInit;
        this.dateInit = dateInit;
        this.hourFinish = hourFinish;
        this.dateFinish = dateFinish;
    }
}