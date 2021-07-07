package co.com.ias.hoursWorkCalculator.report.application.errors;

import co.com.ias.hoursWorkCalculator.commons.errors.ApplicationError;
import co.com.ias.hoursWorkCalculator.commons.errors.HttpStatusCode;
import co.com.ias.hoursWorkCalculator.report.application.domain.ReportIdentityNumber;

import java.util.Map;

public class ReportAlreadyExistsError extends ApplicationError {
    private ReportIdentityNumber reportIdentityNumber;

    public ReportIdentityNumber getServiceIdentity() {
        return reportIdentityNumber;
    }

    public void setServiceIdentity(ReportIdentityNumber reportIdentityNumber) {
        this.reportIdentityNumber = reportIdentityNumber;
    }

    public ReportAlreadyExistsError(ReportIdentityNumber reportIdentityNumber) {
        this.reportIdentityNumber = reportIdentityNumber;
    }


    @Override
    public String getMessage() {
        return "The service report with id number: " + reportIdentityNumber + " already exists.";
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
                "idNumber", reportIdentityNumber
        );
    }
}
