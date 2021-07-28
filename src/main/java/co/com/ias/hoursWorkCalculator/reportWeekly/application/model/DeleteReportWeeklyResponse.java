package co.com.ias.hoursWorkCalculator.reportWeekly.application.model;

import co.com.ias.hoursWorkCalculator.commons.operation.ApplicationResponse;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.ReportWeekly;

public class DeleteReportWeeklyResponse implements ApplicationResponse {
    private final ReportWeekly reportWeekly;

    public ReportWeekly getReportWeekly() {
        return reportWeekly;
    }

    public DeleteReportWeeklyResponse(ReportWeekly reportWeekly) {
        this.reportWeekly = reportWeekly;
    }
    @Override
    public String toString() {
        System.out.println("DeleteReportWeeklyResponse");
        System.out.println(reportWeekly);
        return "DeleteReportWeeklyResponse{" +
                "reportWeekly=" + reportWeekly +
                '}';
    }
}
