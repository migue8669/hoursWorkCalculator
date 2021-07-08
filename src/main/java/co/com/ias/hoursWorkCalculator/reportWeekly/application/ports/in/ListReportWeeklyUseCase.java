package co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.in;

import co.com.ias.hoursWorkCalculator.commons.operation.ApplicationUseCase;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.model.ListWeeklyReportRequest;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.model.ListWeeklyReportResponse;

public interface ListReportWeeklyUseCase extends ApplicationUseCase<ListWeeklyReportRequest, ListWeeklyReportResponse> {
}