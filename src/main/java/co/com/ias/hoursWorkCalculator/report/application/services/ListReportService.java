package co.com.ias.hoursWorkCalculator.report.application.services;

import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;
import co.com.ias.hoursWorkCalculator.report.application.model.ListReportRequest;
import co.com.ias.hoursWorkCalculator.report.application.model.ListReportResponse;
import co.com.ias.hoursWorkCalculator.report.application.ports.in.ListReportsUseCase;
import co.com.ias.hoursWorkCalculator.report.application.ports.out.ReportRepository;

import java.util.Collection;

public class ListReportService implements ListReportsUseCase {
    private final ReportRepository repository;

    public ListReportService(ReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public ListReportResponse execute(ListReportRequest request) {
        Collection<ServiceReport> students = repository.listReports(
                request.getLimit(),
                request.getSkip()
        );
        Integer total = repository.countReports();
        return new ListReportResponse(students, total);
    }
}
