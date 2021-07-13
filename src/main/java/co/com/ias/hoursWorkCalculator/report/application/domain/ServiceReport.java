package co.com.ias.hoursWorkCalculator.report.application.domain;

import co.com.ias.hoursWorkCalculator.commons.InputAttributeError;
import co.com.ias.hoursWorkCalculator.commons.NonEmptyString;
import co.com.ias.hoursWorkCalculator.commons.Validate;
import co.com.ias.hoursWorkCalculator.report.application.errors.InputDataError;
import io.vavr.control.Validation;
import java.util.List;

public class ServiceReport {
    private final TechnicianIdentityNumber technicianIdentity ;
    private final ReportIdentityNumber reportIdentityNumber;
    private final NonEmptyString hourInit;
    private final NonEmptyString dateInit;
    private final NonEmptyString hourFinish;
    private final NonEmptyString dateFinish;
    private final NonEmptyString numWeek;


    public ServiceReport(ReportIdentityNumber reportIdentityNumber, TechnicianIdentityNumber technicianIdentity, NonEmptyString hourInit, NonEmptyString dateInit, NonEmptyString hourFinish, NonEmptyString dateFinish, NonEmptyString numWeek, NonEmptyString week) {

        Validate.notNull(reportIdentityNumber, "report identity  can not be null");

        Validate.notNull(technicianIdentity, "Technician identity  can not be null");
        Validate.notNull(hourInit, "hour Init  can not be null");
        Validate.notNull(dateInit, "date Init can not be null");
        Validate.notNull(hourFinish, "hour Finish can not be null");
        Validate.notNull(dateFinish, "date Finish can not be null");
        Validate.notNull(numWeek, "num week can not be null");


        this.reportIdentityNumber = reportIdentityNumber;
        this.technicianIdentity = technicianIdentity;
        this.hourInit = hourInit;
        this.hourFinish = hourFinish;
        this.dateInit = dateInit;
        this.dateFinish = dateFinish;
        this.numWeek=numWeek;
    }

    public NonEmptyString getNumWeek() {
        return numWeek;
    }

    public TechnicianIdentityNumber getTechnicianIdentity() {
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
            String numWeek

            ){
        var reportIdentityNumberValidation = ReportIdentityNumber.parse(
                reportIdentityNumber,
                "serviceIdentity"
        );
        var technicianIdentityValidation = TechnicianIdentityNumber.parse(
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
                dateFinishValidation,
                numWeekValidation
           )
                .ap((reportIdentityNumber1, technicianIdentity1, hourInit1, dateInit1, hourFinish1, dateFinish1, numWeek1) -> new ServiceReport(reportIdentityNumber1, technicianIdentity1, hourInit1, dateInit1, hourFinish1, dateFinish1, numWeek1, new NonEmptyString("numWeek"))).mapError(inputAttributeErrors ->
                {
                    String message="There was an error with the input report service data.";
                    final List<InputAttributeError> errors = inputAttributeErrors.asJava();
                    return new InputDataError(message,errors);
                });
    }


}
