package co.com.ias.hoursWorkCalculator.report.application.ports.in;

import co.com.ias.hoursWorkCalculator.commons.operation.ApplicationUseCase;
import co.com.ias.hoursWorkCalculator.report.application.model.ListReportRequest;
import co.com.ias.hoursWorkCalculator.report.application.model.ListReportResponse;

public interface ListReportsUseCase extends ApplicationUseCase<ListReportRequest, ListReportResponse> {
}