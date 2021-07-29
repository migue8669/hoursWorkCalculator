package co.com.ias.hoursWorkCalculator.reportWeekly.application.domain;

import co.com.ias.hoursWorkCalculator.commons.InputAttributeError;
import co.com.ias.hoursWorkCalculator.commons.NonEmptyString;
import co.com.ias.hoursWorkCalculator.commons.TechnicianIdentityNumber;
import co.com.ias.hoursWorkCalculator.commons.Validate;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.errors.InputDataError;
import io.vavr.control.Validation;

import java.util.List;

public class ReportWeekly {
    private final TechnicianIdentityNumber technicianIdentity ;
    private final NonEmptyString hour ;
    private final NonEmptyString nightHour ;
    private final NonEmptyString sundayHour ;
    private final NonEmptyString extraHour ;
    private final NonEmptyString extraNightHour ;
    private final NonEmptyString extraSundayHour ;
    private final NonEmptyString numWeek ;





    public ReportWeekly(TechnicianIdentityNumber technicianIdentity,  NonEmptyString hour, NonEmptyString nightHour,NonEmptyString sundayHour, NonEmptyString extraHour, NonEmptyString extraNightHour, NonEmptyString extraSundayHour, NonEmptyString numWeek) {
        Validate.notNull(technicianIdentity, "Technician identity  can not be null");
        Validate.notNull(hour, "hour  can not be null");
        Validate.notNull(nightHour, "nightHour  can not be null");
        Validate.notNull(sundayHour, "sundayHour  can not be null");
        Validate.notNull(extraHour, "extraHour can not be null");
        Validate.notNull(extraNightHour, "extraNightHour can not be null");
        Validate.notNull(extraSundayHour, "extraSundayHour can not be null");
                Validate.notNull(numWeek, "numWeek  can not be null");

        this.technicianIdentity = technicianIdentity;
        this.hour = hour;
        this.nightHour = nightHour;
        this.sundayHour = sundayHour;
        this.extraHour = extraHour;
        this.extraNightHour = extraNightHour;
        this.extraSundayHour = extraSundayHour;
        this.numWeek=numWeek;



    }


    public TechnicianIdentityNumber getTechnicianIdentity() {
        return technicianIdentity;
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
    public NonEmptyString getNumWeek() {
        return numWeek;
    }




    public static Validation<InputDataError, ReportWeekly> WeeklyReport(
            String technicianIdentity,
            String hour,
            String nightHour,
            String sundayHour,
            String extraHour,
            String extraNightHour,
            String extraSundayHour,
            String numWeek

            ){
        var technicianIdentityValidation = TechnicianIdentityNumber.parse(
                technicianIdentity,
                "technicianIdentity"
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
        var numWeekValidation = NonEmptyString.parse(
                numWeek,
                "numWeek"
        );

        return Validation.combine(
                technicianIdentityValidation,
                hourValidation,
                nightHourValidation,
                sundayHourValidation,
                extraHourValidation,
                extraNightHourValidation,
                extraSundayHourValidation,
                numWeekValidation)
                .ap(ReportWeekly::new).mapError(inputAttributeErrors ->
                {
                    String message="There was an error with the input report service data.";
                    final List<InputAttributeError> errors = inputAttributeErrors.asJava();
                    return new InputDataError(message,errors);
                });
    }
}
