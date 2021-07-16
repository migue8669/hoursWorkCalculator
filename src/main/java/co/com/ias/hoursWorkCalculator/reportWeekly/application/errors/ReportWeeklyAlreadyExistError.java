package co.com.ias.hoursWorkCalculator.reportWeekly.application.errors;

import co.com.ias.hoursWorkCalculator.commons.NonEmptyString;
import co.com.ias.hoursWorkCalculator.commons.errors.ApplicationError;
import co.com.ias.hoursWorkCalculator.commons.errors.HttpStatusCode;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.TechnicianIdentityNumber;

import java.util.Map;

public class ReportWeeklyAlreadyExistError extends ApplicationError {
    private NonEmptyString technicianIdentityNumber;

    public ReportWeeklyAlreadyExistError(NonEmptyString technicianIdentityNumber) {
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

    public NonEmptyString getTechnicianIdentityNumber() {
        return technicianIdentityNumber;
    }

    public void setTechnicianIdentityNumber(NonEmptyString technicianIdentityNumber) {
        this.technicianIdentityNumber = technicianIdentityNumber;
    }
}
