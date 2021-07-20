package co.com.ias.hoursWorkCalculator.reportWeekly.application.domain;

public class WeeklyPojo {
    private final String technicianIdentity ;

    private final String reportIdentityNumber;
    private final String hour;
    private final String nightHour;
    private final String sundayHour;
    private final String extraHour;
    private final String extraNightHour;
    private final String extraSundayHour;

    public String getTechnicianIdentity() {
        return technicianIdentity;
    }

    public String getReportIdentityNumber() {
        return reportIdentityNumber;
    }

    public String getHour() {
        return hour;
    }

    public String getNightHour() {
        return nightHour;
    }

    public String getSundayHour() {
        return sundayHour;
    }

    public String getExtraHour() {
        return extraHour;
    }

    public String getExtraNightHour() {
        return extraNightHour;
    }

    public String getExtraSundayHour() {
        return extraSundayHour;
    }

    public WeeklyPojo(String technicianIdentity, String reportIdentityNumber, String hour, String nightHour, String sundayHour, String extraHour, String extraNightHour, String extraSundayHour) {


        this.technicianIdentity = technicianIdentity;

        this.reportIdentityNumber = reportIdentityNumber;
        this.hour = hour;
        this.nightHour = nightHour;
        this.sundayHour = sundayHour;
        this.extraHour = extraHour;
        this.extraNightHour = extraNightHour;
        this.extraSundayHour = extraSundayHour;
    }

}
