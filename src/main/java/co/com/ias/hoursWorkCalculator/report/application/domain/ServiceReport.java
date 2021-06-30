package co.com.ias.hoursWorkCalculator.report.application.domain;

import co.com.ias.hoursWorkCalculator.commons.InputAttributeError;
import co.com.ias.hoursWorkCalculator.commons.NonEmptyString;
import co.com.ias.hoursWorkCalculator.commons.Validate;
import co.com.ias.hoursWorkCalculator.report.application.errors.InputDataError;
import io.vavr.control.Validation;
import java.util.List;

public class ServiceReport {
    private final TechnicianIdentityNumber technicianIdentity ;
    private final ReportdentityNumber serviceIdentity;
    private final NonEmptyString dateAndTimeInit;
    private final NonEmptyString dateAndTimeFinish;

    public ServiceReport(TechnicianIdentityNumber technicianIdentity, ReportdentityNumber serviceIdentity, NonEmptyString dateAndTimeInit, NonEmptyString dateAndTimeFinish) {
        Validate.notNull(technicianIdentity, "Technician identity  can not be null");
        Validate.notNull(serviceIdentity, "Service identity  can not be null");
        Validate.notNull(dateAndTimeInit, "Initial Date and time can not be null");
        Validate.notNull(dateAndTimeFinish, "Finish Date and time can not be null");

        this.technicianIdentity = technicianIdentity;
        this.serviceIdentity = serviceIdentity;
        this.dateAndTimeInit = dateAndTimeInit;
        this.dateAndTimeFinish = dateAndTimeFinish;

    }

    public TechnicianIdentityNumber getTechnicianIdentity() {
        return technicianIdentity;
    }

    public ReportdentityNumber getServiceIdentity() {
        return serviceIdentity;
    }

    public NonEmptyString getDateAndTimeInit() {
        return dateAndTimeInit;
    }

    public NonEmptyString getDateAndTimeFinish() {
        return dateAndTimeFinish;
    }

    public static Validation<InputDataError,ServiceReport> parseReport(
            String technicianIdentity,
            String serviceIdentity,
            String dateAndTimeInit,
            String dateAndTimeFinish
            ){
        var technicianIdentityValidation = TechnicianIdentityNumber.parse(
                technicianIdentity,
                "technicianIdentity"
        );

        var serviceIdentityValidation = ReportdentityNumber.parse(
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
        return Validation.combine(technicianIdentityValidation,
                serviceIdentityValidation,
                dateAndTimeInitValidation,
                dateAndTimeFinishValidation)
                .ap(ServiceReport::new).mapError(inputAttributeErrors ->
                {
                    String message="There was an error with the input report service data.";
                    final List<InputAttributeError> errors = inputAttributeErrors.asJava();
                    return new InputDataError(message,errors);
                });
    }


}
