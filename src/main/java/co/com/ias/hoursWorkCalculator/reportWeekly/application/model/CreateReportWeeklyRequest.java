package co.com.ias.hoursWorkCalculator.reportWeekly.application.model;

import co.com.ias.hoursWorkCalculator.commons.operation.ApplicationRequest;

import java.util.Objects;

public class CreateReportWeeklyRequest implements ApplicationRequest {
    private String technicianIdentity;
    private String hour;
    private String nightHour;
    private String sundayHour;
    private  String extraHour;
    private  String extraNightHour;
    private String extraSundayHour;
    private  String numWeek;

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

    public String getNumWeek() {
        return numWeek;
    }

    public void setNumWeek(String numWeek) {
        this.numWeek = numWeek;
    }

    public String getTechnicianIdentity() {
        return technicianIdentity;
    }

    public void setTechnicianIdentity(String technicianIdentity) {
        this.technicianIdentity = technicianIdentity;
    }


    @Override
    public String toString() {
        return "CreateReportWeeklyRequest{" +
                "technicianIdentity='" + technicianIdentity + '\'' +
                ", hour='" + hour + '\'' +
                ", nightHour='" + nightHour + '\'' +
                ", sundayHour='" + sundayHour + '\'' +
                ", extraHour='" + extraHour + '\'' +
                ", extraNightHour='" + extraNightHour + '\'' +
                ", extraSundayHour='" + extraSundayHour + '\'' +
                ", numWeek='" + numWeek + '\'' +

                '}';
    }

    public CreateReportWeeklyRequest(String technicianIdentity, String hour, String nightHour, String sundayHour, String extraHour, String extraNightHour, String extraSundayHour, String numWeek) {
        this.technicianIdentity = technicianIdentity;
        this.hour = hour;
        this.nightHour = nightHour;
        this.sundayHour = sundayHour;
        this.extraHour = extraHour;
        this.extraNightHour = extraNightHour;
        this.extraSundayHour = extraSundayHour;
        this.numWeek = numWeek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateReportWeeklyRequest that = (CreateReportWeeklyRequest) o;
        return Objects.equals(technicianIdentity, that.technicianIdentity) && Objects.equals(numWeek, that.numWeek) && Objects.equals(hour, that.hour) && Objects.equals(nightHour, that.nightHour) && Objects.equals(sundayHour, that.sundayHour) && Objects.equals(extraHour, that.numWeek) && Objects.equals(extraNightHour, that.numWeek) && Objects.equals(extraSundayHour, that.numWeek);

    }

    @Override
    public int hashCode() {
        return Objects.hash(technicianIdentity, numWeek);
    }
}
