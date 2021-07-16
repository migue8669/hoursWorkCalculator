package co.com.ias.hoursWorkCalculator.reportWeekly.application.domain;

import co.com.ias.hoursWorkCalculator.commons.InputAttributeError;
import co.com.ias.hoursWorkCalculator.commons.NonEmptyString;
import co.com.ias.hoursWorkCalculator.commons.Validate;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.errors.InputDataError;
import io.vavr.control.Validation;

import java.util.List;

public class ReportWeekly {
    private final NonEmptyString technicianIdentity ;

    private final ReportIdentityNumber reportIdentityNumber;
    private final NonEmptyString hour;
    private final NonEmptyString nightHour;
    private final NonEmptyString sundayHour;
    private final NonEmptyString extraHour;
    private final NonEmptyString extraNightHour;
    private final NonEmptyString extraSundayHour;

    public NonEmptyString getTechnicianIdentity() {
        return technicianIdentity;
    }

    public ReportIdentityNumber getReportIdentityNumber() {
        return reportIdentityNumber;
    }

    public NonEmptyString getHour() {
        return hour;
    }

    public NonEmptyString getNightHour() {
        return nightHour;
    }

    public NonEmptyString getSundayHour() {
        return sundayHour;
    }

    public NonEmptyString getExtraHour() {
        return extraHour;
    }

    public NonEmptyString getExtraNightHour() {
        return extraNightHour;
    }

    public NonEmptyString getExtraSundayHour() {
        return extraSundayHour;
    }

    public ReportWeekly(NonEmptyString technicianIdentity, ReportIdentityNumber reportIdentityNumber, NonEmptyString hour, NonEmptyString nightHour, NonEmptyString sundayHour, NonEmptyString extraHour, NonEmptyString extraNightHour, NonEmptyString extraSundayHour) {
        Validate.notNull(technicianIdentity, "Technician identity  can not be null");
        Validate.notNull(reportIdentityNumber, "report identity  can not be null");
        Validate.notNull(hour, "hour  can not be null");
        Validate.notNull(nightHour, "nightHour can not be null");
        Validate.notNull(sundayHour, "sundayHour can not be null");
        Validate.notNull(extraHour, "extraHour can not be null");
        Validate.notNull(extraNightHour, "extraNightHour can not be null");

        Validate.notNull(extraSundayHour, "extraSundayHour can not be null");

        this.technicianIdentity = technicianIdentity;

        this.reportIdentityNumber = reportIdentityNumber;
        this.hour = hour;
        this.nightHour = nightHour;
        this.sundayHour = sundayHour;
        this.extraHour = extraHour;
        this.extraNightHour = extraNightHour;
        this.extraSundayHour = extraSundayHour;
    }

    public static Validation<InputDataError, ReportWeekly> WeeklyReport(
            String technicianIdentity,
            String reportIdentityNumber,
            String hour,
            String nightHour,
            String sundayHour,
            String extraHour,
            String extraNightHour,
            String extraSundayHour
    ){
        var technicianIdentityValidation = NonEmptyString.parse(
                technicianIdentity,
                "technicianIdentity"
        );
        var reportIdentityNumberValidation = ReportIdentityNumber.parse(
                reportIdentityNumber,
                "reportIdentityNumber"
        );




        var hourValidation = NonEmptyString.parse(
                hour,
                "hour"
        );

        var nightHourValidation = NonEmptyString.parse(
                nightHour,
                "nightHour"
        );

        var sundayHourValidation = NonEmptyString.parse(
                sundayHour,
                "sundayHour"
        );

        var extraHourValidation = NonEmptyString.parse(
                extraHour,
                "extraHour"
        );
        var extraNightHourValidation = NonEmptyString.parse(
                extraNightHour,
                "extraNightHour"
        );
        var extraSundayHourValidation = NonEmptyString.parse(
                extraSundayHour,
                "extraSundayHour"
        );
        return Validation.combine(
                technicianIdentityValidation,
                reportIdentityNumberValidation,
                hourValidation,
                nightHourValidation,
                sundayHourValidation,
                extraHourValidation,extraNightHourValidation,extraSundayHourValidation)
                .ap(ReportWeekly::new).mapError(inputAttributeErrors ->
                {
                    String message="There was an error with the input report service data.";
                    final List<InputAttributeError> errors = inputAttributeErrors.asJava();
                    return new InputDataError(message,errors);
                });
    }
}
