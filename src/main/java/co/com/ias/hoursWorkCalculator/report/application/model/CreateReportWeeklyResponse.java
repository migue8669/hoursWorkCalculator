package co.com.ias.hoursWorkCalculator.report.application.model;

import co.com.ias.hoursWorkCalculator.commons.operation.ApplicationResponse;
import co.com.ias.hoursWorkCalculator.report.application.domain.ReportWeekly;

public class CreateReportWeeklyResponse implements ApplicationResponse {
    private final ReportWeekly reportWeekly;

    public ReportWeekly getReportWeekly() {
        return reportWeekly;
    }

    public CreateReportWeeklyResponse(ReportWeekly reportWeekly) {
        this.reportWeekly = reportWeekly;
    }
    @Override
    public String toString() {
        return "CreateReportWeeklyResponse{" +
                "reportWeekly=" + reportWeekly +
                '}';
    }
}