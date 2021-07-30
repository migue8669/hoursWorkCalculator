package co.com.ias.hoursWorkCalculator.reportWeekly.application.model;

import co.com.ias.hoursWorkCalculator.commons.operation.ApplicationResponse;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.ReportWeekly;

import java.util.Collection;

public class ListDeletedReportWeeklyResponse implements ApplicationResponse {
    private final Collection<ReportWeekly> items;


    public ListDeletedReportWeeklyResponse(Collection<ReportWeekly> items) {
            this.items = items;
            //  this.total = total;
        }

        public Collection<ReportWeekly> getItems() {
            return items;
        }

        // public Integer getTotal() {
        //   return total;
        //}
    }