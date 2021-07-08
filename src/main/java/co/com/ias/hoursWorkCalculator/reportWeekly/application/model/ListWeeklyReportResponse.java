package co.com.ias.hoursWorkCalculator.reportWeekly.application.model;

import co.com.ias.hoursWorkCalculator.commons.operation.ApplicationResponse;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.ReportWeekly;

import java.util.Collection;

public class ListWeeklyReportResponse implements ApplicationResponse {
    private final Collection<ReportWeekly> items;
    private final Integer total;

    public ListWeeklyReportResponse(Collection<ReportWeekly> items, Integer total) {
        this.items = items;
        this.total = total;
    }

    public Collection<ReportWeekly> getItems() {
        return items;
    }

    public Integer getTotal() {
        return total;
    }
}
