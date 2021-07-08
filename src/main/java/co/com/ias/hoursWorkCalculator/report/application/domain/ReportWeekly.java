package co.com.ias.hoursWorkCalculator.report.application.domain;

import co.com.ias.hoursWorkCalculator.commons.NonEmptyString;

public class ReportWeekly {
    private final TechnicianIdentityNumber technicianIdentity ;

    private final ReportIdentityNumber reportIdentityNumber;
    private final NonEmptyString hour;
    private final NonEmptyString nightHour;
    private final NonEmptyString sundayHour;
    private final NonEmptyString extraHour;
    private final NonEmptyString extraNightHour;
    private final NonEmptyString extraSundayHour;

    public TechnicianIdentityNumber getTechnicianIdentity() {
        return technicianIdentity;
    }

    public ReportIdentityNumber getReportIdentityNumber() {
        return reportIdentityNumber;
    }

    public NonEmptyString getHour() {
        return hour;
    }

    public NonEmptyString getNightHour() {
        return nightHour;
    }

    public NonEmptyString getSundayHour() {
        return sundayHour;
    }

    public NonEmptyString getExtraHour() {
        return extraHour;
    }

    public NonEmptyString getExtraNightHour() {
        return extraNightHour;
    }

    public NonEmptyString getExtraSundayHour() {
        return extraSundayHour;
    }

    public ReportWeekly(TechnicianIdentityNumber technicianIdentity, ReportIdentityNumber reportIdentityNumber, NonEmptyString hour, NonEmptyString nightHour, NonEmptyString sundayHour, NonEmptyString extraHour, NonEmptyString extraNightHour, NonEmptyString extraSundayHour) {
   

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