package co.com.ias.hoursWorkCalculator.report.application.model;

import co.com.ias.hoursWorkCalculator.commons.operation.ApplicationRequest;
import co.com.ias.hoursWorkCalculator.report.application.errors.InputDataError;
import io.vavr.control.Validation;

import java.util.Objects;

public class CreateReportWeeklyRequest implements ApplicationRequest {
   private   String technicianIdentity;
    private  String reportdentityNumber;
    private  String hour;

    private   String nightHour;
    private   String sundayHour;
    private   String extraHour;
    private   String extraNightHour;
    private   String extraSundayHour;


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

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getNightHour() {
        return nightHour;
    }

    public void setNightHour(String nightHour) {
        this.nightHour = nightHour;
    }

    @Override
    public String toString() {
        return "CreateReportWeeklyRequest{" +
                "technicianIdentity='" + technicianIdentity + '\'' +
                ", reportdentityNumber='" + reportdentityNumber + '\'' +
                ", hour='" + hour + '\'' +
                ", nightHour='" + nightHour + '\'' +
                ", sundayHour='" + sundayHour + '\'' +
                ", extraHour='" + extraHour + '\'' +
                ", extraNightHour='" + extraNightHour + '\'' +
                ", extraSundayHour='" + extraSundayHour + '\'' +
                '}';
    }

    public String getSundayHour() {
        return sundayHour;
    }

    public void setSundayHour(String sundayHour) {
        this.sundayHour = sundayHour;
    }

    public String getExtraHour() {
        return extraHour;
    }

    public void setExtraHour(String extraHour) {
        this.extraHour = extraHour;
    }

    public String getExtraNightHour() {
        return extraNightHour;
    }

    public void setExtraNightHour(String extraNightHour) {
        this.extraNightHour = extraNightHour;
    }

    public String getExtraSundayHour() {
        return extraSundayHour;
    }

    public void setExtraSundayHour(String extraSundayHour) {
        this.extraSundayHour = extraSundayHour;
    }

    public CreateReportWeeklyRequest(String technicianIdentity, String reportdentityNumber, String hour, String nightHour, String sundayHour, String extraHour, String extraNightHour, String extraSundayHour) {
        this.technicianIdentity = technicianIdentity;
        this.reportdentityNumber = reportdentityNumber;
        this.hour = hour;
        this.nightHour = nightHour;
        this.sundayHour = sundayHour;
        this.extraHour = extraHour;
        this.extraNightHour = extraNightHour;
        this.extraSundayHour = extraSundayHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateReportWeeklyRequest that = (CreateReportWeeklyRequest) o;
        return technicianIdentity.equals(that.technicianIdentity) && reportdentityNumber.equals(that.reportdentityNumber) && hour.equals(that.hour) && nightHour.equals(that.nightHour) && sundayHour.equals(that.sundayHour) && extraHour.equals(that.extraHour) && extraNightHour.equals(that.extraNightHour) && extraSundayHour.equals(that.extraSundayHour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(technicianIdentity, reportdentityNumber, hour, nightHour, sundayHour, extraHour, extraNightHour, extraSundayHour);
    }
}
