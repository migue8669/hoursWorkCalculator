package co.com.ias.hoursWorkCalculator.serviceReport.application.domain;

import co.com.ias.hoursWorkCalculator.commons.InputAttributeError;
import co.com.ias.hoursWorkCalculator.commons.NonEmptyString;
import co.com.ias.hoursWorkCalculator.commons.Validate;
import io.vavr.control.Validation;
import java.util.List;

public class ServiceReport {
    private final NonEmptyString technicianIdentity ;
    private final NonEmptyString serviceIdentity;
    private final NonEmptyString dateAndTimeInit;
    private final NonEmptyString dateAndTimeFinish;

    public ServiceReport(NonEmptyString technicianIdentity, NonEmptyString serviceIdentity, NonEmptyString dateAndTimeInit, NonEmptyString dateAndTimeFinish) {
        Validate.notNull(technicianIdentity, "Technician identity  can not be null");
        Validate.notNull(technicianIdentity, "Service identity  can not be null");
        Validate.notNull(technicianIdentity, "Technician identity  can not be null");
        Validate.notNull(technicianIdentity, "Initial Date and time can not be null");
        Validate.notNull(technicianIdentity, "Finish Date and time can not be null");

        this.technicianIdentity = technicianIdentity;
        this.serviceIdentity = serviceIdentity;
        this.dateAndTimeInit = dateAndTimeInit;
        this.dateAndTimeFinish = dateAndTimeFinish;

    }
    public static Validation<InputDataError,ServiceReport> parseReport(
            String technicianIdentity,
            String serviceIdentity,
            String dateAndTimeInit,
            String dateAndTimeFinish
            ){
        var technicianIdentityValidation = NonEmptyString.parse(
                technicianIdentity,
                "technicianIdentity"
        );

        var serviceIdentityValidation = NonEmptyString.parse(
                serviceIdentity,
                "serviceIdentity"
        );

        var dateAndTimeInitValidation = NonEmptyString.parse(
                dateAndTimeInit,
                "dateAndTimeInit"
        );

        var dateAndTimeFinishValidation = NonEmptyString.parse(
                dateAndTimeFinish,
                "dateAndTimeFinish"
        );
        return Validation.combine(technicianIdentityValidation,serviceIdentityValidation,dateAndTimeInitValidation,dateAndTimeFinishValidation)
                .ap(ServiceReport::new).mapError(inputAttributeErrors ->
                {
                    String message="There was an error with the input report service data.";
                    final List<InputAttributeError> errors = inputAttributeErrors.asJava();
                    return new InputDataError(message,errors);
                });
    }
}
