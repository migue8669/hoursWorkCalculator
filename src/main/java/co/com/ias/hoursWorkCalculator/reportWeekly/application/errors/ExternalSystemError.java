package co.com.ias.hoursWorkCalculator.reportWeekly.application.errors;

import co.com.ias.hoursWorkCalculator.commons.errors.ApplicationError;
import co.com.ias.hoursWorkCalculator.commons.errors.HttpStatusCode;

import java.util.Map;

public class ExternalSystemError extends ApplicationError {
    private final String message;
    private final Throwable throwable;

    @Override
    public Map<String, Object> metadata() {
        return Map.of(
                "throwable", throwable
        );
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public String errorCode() {
        return "EXTERNAL_SYSTEM_ERROR";
    }

    @Override
    public HttpStatusCode httpStatusCode() {
        return HttpStatusCode.INTERNAL_SERVER_ERROR;

    }

    public ExternalSystemError(String message, Throwable throwable) {
        this.message = message;
        this.throwable = throwable;
    }
}
