package co.com.ias.hoursWorkCalculator.report.application.model;

import co.com.ias.hoursWorkCalculator.commons.operation.ApplicationResponse;
import co.com.ias.hoursWorkCalculator.report.application.domain.ReportWeekly;
import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;

public class CreateReportWeeklyResponse implements ApplicationResponse {
    private final ReportWeekly reportWeekly;

    @Override
    public String toString() {
        return "CreateReportWeeklyResponse{" +
                "reportWeekly=" + reportWeekly +
                '}';
    }

    public CreateReportWeeklyResponse(ReportWeekly reportWeekly) {
        this.reportWeekly = reportWeekly;
    }
}
