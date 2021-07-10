package co.com.ias.hoursWorkCalculator.commons.operation;

import java.text.ParseException;

public interface ApplicationUseCase<IN extends ApplicationRequest, OUT extends ApplicationResponse> {
    OUT execute(IN request) throws ParseException;
}