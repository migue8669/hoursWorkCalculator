package co.com.ias.hoursWorkCalculator.report.application.errors;

import co.com.ias.hoursWorkCalculator.commons.TechnicianIdentityNumber;
import co.com.ias.hoursWorkCalculator.commons.errors.ApplicationError;
import co.com.ias.hoursWorkCalculator.commons.errors.HttpStatusCode;

import java.util.Map;

public class ReportWeeklyAlreadyExistError extends ApplicationError {
    private TechnicianIdentityNumber technicianIdentityNumber;

    public ReportWeeklyAlreadyExistError(TechnicianIdentityNumber technicianIdentityNumber) {
        this.technicianIdentityNumber = technicianIdentityNumber;
    }

    @Override
    public String errorCode() {
        return "WEEKLY_REPORT_ALREADY_EXISTS_ERROR";
    }


    @Override
    public HttpStatusCode httpStatusCode() {
        return HttpStatusCode.BAD_REQUEST;
    }


    @Override
    public Map<String, Object> metadata() {
        return Map.of(
                "idNumber", technicianIdentityNumber
        );    }

    public TechnicianIdentityNumber getTechnicianIdentityNumber() {
        return technicianIdentityNumber;
    }

    public void setTechnicianIdentityNumber(TechnicianIdentityNumber technicianIdentityNumber) {
        this.technicianIdentityNumber = technicianIdentityNumber;
    }
}