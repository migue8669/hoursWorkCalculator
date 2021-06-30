package co.com.ias.hoursWorkCalculator.report.application.model;

import co.com.ias.hoursWorkCalculator.commons.operation.ApplicationResponse;
import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;

import java.util.Collection;

public class ListReportResponse implements ApplicationResponse {
    private final Collection<ServiceReport> items;
    private final Integer total;

    public ListReportResponse(Collection<ServiceReport> items, Integer total) {
        this.items = items;
        this.total = total;
    }

    public Collection<ServiceReport> getItems() {
        return items;
    }

    public Integer getTotal() {
        return total;
    }
}
