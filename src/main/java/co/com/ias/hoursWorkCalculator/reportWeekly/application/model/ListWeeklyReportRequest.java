package co.com.ias.hoursWorkCalculator.reportWeekly.application.model;

import co.com.ias.hoursWorkCalculator.commons.operation.ApplicationRequest;

public class ListWeeklyReportRequest implements ApplicationRequest {
    private final Integer limit;
    private final Integer skip;

    public Integer getLimit() {
        return limit;
    }

    public Integer getSkip() {
        return skip;
    }

    public ListWeeklyReportRequest(Integer limit, Integer skip) {
        this.limit = limit;
        this.skip = skip;
    }
}
