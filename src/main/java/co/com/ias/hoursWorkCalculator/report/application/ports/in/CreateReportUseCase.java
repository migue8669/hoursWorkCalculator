package co.com.ias.hoursWorkCalculator.report.application.ports.in;

import co.com.ias.hoursWorkCalculator.commons.operation.ApplicationUseCase;
import co.com.ias.hoursWorkCalculator.report.application.model.CreateReportRequest;
import co.com.ias.hoursWorkCalculator.report.application.model.CreateReportResponse;

public interface CreateReportUseCase extends ApplicationUseCase<CreateReportRequest, CreateReportResponse> {
}