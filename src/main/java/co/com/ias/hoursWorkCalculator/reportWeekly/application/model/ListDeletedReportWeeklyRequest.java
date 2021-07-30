package co.com.ias.hoursWorkCalculator.reportWeekly.application.model;

import co.com.ias.hoursWorkCalculator.commons.operation.ApplicationRequest;

import java.util.Objects;

public class ListDeletedReportWeeklyRequest implements ApplicationRequest {
    //   private final Integer limit;
    private final Integer skip;

//    public Integer getLimit() {
    //      return limit;
    //   }

    public Integer getSkip() {
        return skip;
    }

    public ListDeletedReportWeeklyRequest( Integer skip) {

        this.skip = skip;
    }
}