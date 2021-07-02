package co.com.ias.hoursWorkCalculator.report.application.domain;

import co.com.ias.hoursWorkCalculator.commons.InputAttributeError;
import co.com.ias.hoursWorkCalculator.commons.NonEmptyString;
import co.com.ias.hoursWorkCalculator.commons.Validate;
import co.com.ias.hoursWorkCalculator.report.application.errors.InputDataError;
import io.vavr.control.Validation;
import java.util.List;

public class ServiceReport {
    private final TechnicianIdentityNumber technicianIdentity ;
    private final ReportdentityNumber reportdentityNumber ;
    private final NonEmptyString hourInit;
    private final NonEmptyString dateInit;
    private final NonEmptyString hourFinish;
    private final NonEmptyString dateFinish;

    public ServiceReport(TechnicianIdentityNumber technicianIdentity,ReportdentityNumber reportdentityNumber, NonEmptyString hourInit, NonEmptyString dateInit, NonEmptyString hourFinish, NonEmptyString dateFinish) {
        Validate.notNull(technicianIdentity, "Technician identity  can not be null");
        Validate.notNull(reportdentityNumber, "report identity  can not be null");
        Validate.notNull(hourInit, "hourInit  can not be null");
        Validate.notNull(dateInit, "dateInit can not be null");
        Validate.notNull(hourFinish, "hourFinish can not be null");
        Validate.notNull(dateFinish, "dateFinish can not be null");
this.reportdentityNumber=reportdentityNumber;
        this.technicianIdentity = technicianIdentity;
        this.hourInit = hourInit;
        this.hourFinish = hourFinish;
        this.dateInit = dateInit;
        this.dateFinish = dateFinish;
    }

    public TechnicianIdentityNumber getTechnicianIdentity() {
        return technicianIdentity;
    }

    public ReportdentityNumber getReportdentityNumber() {
        return reportdentityNumber;
    }

    public NonEmptyString getHourInit() {
        return hourInit;
    }

    public NonEmptyString getDateInit() {
        return dateInit;
    }

    public NonEmptyString getHourFinish() {
        return hourFinish;
    }

    public NonEmptyString getDateFinish() {
        return dateFinish;
    }

    public static Validation<InputDataError,ServiceReport> parseReport(
            String technicianIdentity,
            String reportdentityNumber,
            String hourInit,
            String dateInit,
            String hourFinish,
            String dateFinish
            ){
        var technicianIdentityValidation = TechnicianIdentityNumber.parse(
                technicianIdentity,
                "technicianIdentity"
        );

        var reportdentityNumberValidation = ReportdentityNumber.parse(
                reportdentityNumber,
                "serviceIdentity"
        );

        var hourInitValidation = NonEmptyString.parse(
                hourInit,
                "hourInit"
        );

        var dateInitValidation = NonEmptyString.parse(
                dateInit,
                "dateInit"
        );

        var hourFinishValidation = NonEmptyString.parse(
                hourFinish,
                "hourFinish"
        );

        var dateFinishValidation = NonEmptyString.parse(
                dateFinish,
                "dateFinish"
        );
        return Validation.combine(technicianIdentityValidation,
                reportdentityNumberValidation,
                hourInitValidation,
                dateInitValidation,
                hourFinishValidation,
                dateFinishValidation)
                .ap(ServiceReport::new).mapError(inputAttributeErrors ->
                {
                    String message="There was an error with the input report service data.";
                    final List<InputAttributeError> errors = inputAttributeErrors.asJava();
                    return new InputDataError(message,errors);
                });
    }


}
