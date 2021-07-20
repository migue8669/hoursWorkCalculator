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
    private final NonEmptyString numWeek ;

    public NonEmptyString getNumWeek() {
        return numWeek;
    }

    public TechnicianIdentityNumber getTechnicianIdentity() {
        return technicianIdentity;
    }


    public ReportWeekly(TechnicianIdentityNumber technicianIdentity,NonEmptyString numWeek) {
        Validate.notNull(technicianIdentity, "Technician identity  can not be null");
        Validate.notNull(numWeek, "Technician identity  can not be null");

this.numWeek=numWeek;
        this.technicianIdentity = technicianIdentity;


    }

    public static Validation<InputDataError, ReportWeekly> WeeklyReport(
            String technicianIdentity, String numWeek

            ){
        var technicianIdentityValidation = TechnicianIdentityNumber.parse(
                technicianIdentity,
                "technicianIdentity"
        );
        var numWeekValidation = NonEmptyString.parse(
                numWeek,
                "numWeek"
        );

        return Validation.combine(
                technicianIdentityValidation,numWeekValidation)
                .ap(ReportWeekly::new).mapError(inputAttributeErrors ->
                {
                    String message="There was an error with the input report service data.";
                    final List<InputAttributeError> errors = inputAttributeErrors.asJava();
                    return new InputDataError(message,errors);
                });
    }
}
