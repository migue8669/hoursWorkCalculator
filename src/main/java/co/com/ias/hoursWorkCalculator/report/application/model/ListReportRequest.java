package co.com.ias.hoursWorkCalculator.report.application.model;

import co.com.ias.hoursWorkCalculator.commons.operation.ApplicationRequest;

public class ListReportRequest implements ApplicationRequest {
    private final Integer limit;
    private final Integer skip;

    public Integer getLimit() {
        return limit;
    }

    public Integer getSkip() {
        return skip;
    }

    public ListReportRequest(Integer limit, Integer skip) {
        this.limit = limit;
        this.skip = skip;
    }
}
