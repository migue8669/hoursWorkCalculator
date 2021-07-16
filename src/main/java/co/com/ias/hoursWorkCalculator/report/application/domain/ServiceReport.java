package co.com.ias.hoursWorkCalculator.report.application.domain;

import co.com.ias.hoursWorkCalculator.commons.InputAttributeError;
import co.com.ias.hoursWorkCalculator.commons.NonEmptyString;
import co.com.ias.hoursWorkCalculator.commons.Validate;
import co.com.ias.hoursWorkCalculator.report.application.errors.InputDataError;
import io.vavr.control.Validation;
import java.util.List;

public class ServiceReport {
    private final NonEmptyString technicianIdentity ;
    private final ReportIdentityNumber reportIdentityNumber;
    private final NonEmptyString hourInit;
    private final NonEmptyString dateInit;
    private final NonEmptyString hourFinish;
    private final NonEmptyString dateFinish;

    public NonEmptyString getNumWeek() {
        return numWeek;
    }

    private final NonEmptyString numWeek;

    public ServiceReport(ReportIdentityNumber reportIdentityNumber, NonEmptyString technicianIdentity, NonEmptyString hourInit, NonEmptyString dateInit, NonEmptyString hourFinish, NonEmptyString dateFinish, NonEmptyString numWeek) {
        Validate.notNull(reportIdentityNumber, "report identity  can not be null");

        Validate.notNull(technicianIdentity, "Technician identity  can not be null");
        Validate.notNull(hourInit, "hourInit  can not be null");
        Validate.notNull(dateInit, "dateInit can not be null");
        Validate.notNull(hourFinish, "hourFinish can not be null");
        Validate.notNull(dateFinish, "dateFinish can not be null");
        Validate.notNull(numWeek, "numWeek can not be null");

        this.reportIdentityNumber = reportIdentityNumber;
        this.technicianIdentity = technicianIdentity;
        this.hourInit = hourInit;
        this.hourFinish = hourFinish;
        this.dateInit = dateInit;
        this.dateFinish = dateFinish;
        this.numWeek = numWeek;

    }

    public NonEmptyString getTechnicianIdentity() {
        return technicianIdentity;
    }

    public ReportIdentityNumber getReportIdentityNumber() {
        return reportIdentityNumber;
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
            String reportIdentityNumber,
            String technicianIdentity,
            String hourInit,
            String dateInit,
            String hourFinish,
            String dateFinish,
            String numWeek){
        var reportIdentityNumberValidation = ReportIdentityNumber.parse(
                reportIdentityNumber,
                "serviceIdentity"
        );
        var technicianIdentityValidation = NonEmptyString.parse(
                technicianIdentity,
                "technicianIdentity"
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
        var numWeekValidation = NonEmptyString.parse(
                numWeek,
                "numWeek"
        );
        return Validation.combine(
                reportIdentityNumberValidation,
                technicianIdentityValidation,
                hourInitValidation,
                dateInitValidation,
                hourFinishValidation,
                dateFinishValidation,numWeekValidation)
                .ap(ServiceReport::new).mapError(inputAttributeErrors ->
                {
                    System.out.println();
                    String message="There was an error with the input report service data.";
                    final List<InputAttributeError> errors = inputAttributeErrors.asJava();
                    return new InputDataError(message,errors);
                });
    }


}
