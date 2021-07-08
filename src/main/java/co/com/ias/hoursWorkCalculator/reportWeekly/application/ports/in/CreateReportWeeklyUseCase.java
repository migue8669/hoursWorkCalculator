package co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.in;

import co.com.ias.hoursWorkCalculator.commons.operation.ApplicationUseCase;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.model.CreateReportWeeklyRequest;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.model.CreateReportWeeklyResponse;

public interface CreateReportWeeklyUseCase extends ApplicationUseCase<CreateReportWeeklyRequest, CreateReportWeeklyResponse> {
}
