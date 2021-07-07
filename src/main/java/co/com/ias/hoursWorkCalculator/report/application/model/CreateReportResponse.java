package co.com.ias.hoursWorkCalculator.report.application.model;

import co.com.ias.hoursWorkCalculator.commons.operation.ApplicationResponse;
import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;

public class CreateReportResponse implements ApplicationResponse {
    private final ServiceReport serviceReport;

    public CreateReportResponse(ServiceReport serviceReport) {
        this.serviceReport = serviceReport;
    }

    public ServiceReport getServiceReport() {
        return serviceReport;
    }

    @Override
    public String toString() {
        return "CreateReportResponse{" +
                "report service=" + serviceReport +
                '}';
    }
}