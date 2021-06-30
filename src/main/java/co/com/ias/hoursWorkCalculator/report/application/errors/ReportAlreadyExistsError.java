package co.com.ias.hoursWorkCalculator.report.application.errors;

import co.com.ias.hoursWorkCalculator.commons.errors.ApplicationError;
import co.com.ias.hoursWorkCalculator.commons.errors.HttpStatusCode;
import co.com.ias.hoursWorkCalculator.report.application.domain.ReportdentityNumber;

import java.util.Map;

public class ReportAlreadyExistsError extends ApplicationError {
    private ReportdentityNumber reportdentityNumber;

    public ReportdentityNumber getServiceIdentity() {
        return reportdentityNumber;
    }

    public void setServiceIdentity(ReportdentityNumber reportdentityNumber) {
        this.reportdentityNumber = reportdentityNumber;
    }

    public ReportAlreadyExistsError(ReportdentityNumber reportdentityNumber) {
        this.reportdentityNumber = reportdentityNumber;
    }


    @Override
    public String getMessage() {
        return "The service report with id number: " + reportdentityNumber + " already exists.";
    }

    @Override
    public String errorCode() {
        return "SERVICE_REPORT_ALREADY_EXISTS_ERROR";
    }

    @Override
    public HttpStatusCode httpStatusCode() {
        return HttpStatusCode.BAD_REQUEST;
    }

    @Override
    public Map<String, Object> metadata() {
        return Map.of(
                "idNumber", reportdentityNumber
        );
    }
}
