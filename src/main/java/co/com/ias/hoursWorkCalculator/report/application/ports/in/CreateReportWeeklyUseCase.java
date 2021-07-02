package co.com.ias.hoursWorkCalculator.report.application.ports.in;

import co.com.ias.hoursWorkCalculator.commons.operation.ApplicationUseCase;
import co.com.ias.hoursWorkCalculator.report.application.model.CreateReportWeeklyRequest;
import co.com.ias.hoursWorkCalculator.report.application.model.CreateReportWeeklyResponse;

public interface CreateReportWeeklyUseCase extends ApplicationUseCase<CreateReportWeeklyRequest, CreateReportWeeklyResponse> {
}
