package co.com.ias.hoursWorkCalculator.commons.operation;

public interface ApplicationUseCase<IN extends ApplicationRequest, OUT extends ApplicationResponse> {
    OUT execute(IN request);
}