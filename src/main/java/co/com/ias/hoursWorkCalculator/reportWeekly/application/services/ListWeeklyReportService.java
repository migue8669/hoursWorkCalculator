package co.com.ias.hoursWorkCalculator.reportWeekly.application.services;

import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.ReportWeekly;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.model.ListWeeklyReportRequest;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.model.ListWeeklyReportResponse;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.in.ListReportWeeklyUseCase;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.out.ReportWeeklyRepository;

import java.util.Collection;

public class ListWeeklyReportService implements ListReportWeeklyUseCase {
    private final ReportWeeklyRepository repository;

    public ListWeeklyReportService(ReportWeeklyRepository repository) {
        this.repository = repository;
    }

    @Override
    public ListWeeklyReportResponse execute(ListWeeklyReportRequest request) {
        System.out.println(request);
        Collection<ReportWeekly> reportWeeklies = repository.listReportsWeekly();
        System.out.println("reportWeeklies"+reportWeeklies);

        Integer total = repository.countReportsWeekly();
        return new ListWeeklyReportResponse(reportWeeklies);
    }
}
