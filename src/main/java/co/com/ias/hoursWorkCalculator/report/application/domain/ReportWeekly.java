package co.com.ias.hoursWorkCalculator.report.application.domain;

import co.com.ias.hoursWorkCalculator.commons.InputAttributeError;
import co.com.ias.hoursWorkCalculator.commons.NonEmptyString;
import co.com.ias.hoursWorkCalculator.report.application.errors.InputDataError;
import io.vavr.control.Validation;

import java.util.List;

public class ReportWeekly {
    private final TechnicianIdentityNumber technicianIdentity ;
    private final ReportdentityNumber reportdentityNumber;
    private final NonEmptyString hour;
    private final NonEmptyString nightHour;
    private final NonEmptyString sundayHour;
    private final NonEmptyString extraHour;
    private final NonEmptyString extraNightHour;
    private final NonEmptyString extraSundayHour;

    public TechnicianIdentityNumber getTechnicianIdentity() {
        return technicianIdentity;
    }

    public ReportdentityNumber getReportdentityNumber() {
        return reportdentityNumber;
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

    public ReportWeekly(TechnicianIdentityNumber technicianIdentity, ReportdentityNumber reportdentityNumber, NonEmptyString hour, NonEmptyString nightHour, NonEmptyString sundayHour, NonEmptyString extraHour, NonEmptyString extranightHour, NonEmptyString extraSundayHour) {
        this.technicianIdentity = technicianIdentity;
        this.reportdentityNumber = reportdentityNumber;
        this.hour = hour;
        this.nightHour = nightHour;
        this.sundayHour = sundayHour;
        this.extraHour = extraHour;
        this.extraNightHour = extranightHour;
        this.extraSundayHour = extraSundayHour;
    }

    public static Validation<InputDataError,ReportWeekly> parseReport(
            String reportIdentityNumber,
            String technicianIdentity,
            String hour,
             String nightHour,
                  String sundayHour,
                    String extraHour,
                    String extraNightHour,
                    String extraSundayHour
    ){
        var technicianIdentityValidation = TechnicianIdentityNumber.parse(
                technicianIdentity,
                "technicianIdentity"
        );

        var reportIdentityNumberValidation = ReportdentityNumber.parse(
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
        return Validation.combine(technicianIdentityValidation,
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
