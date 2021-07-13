package co.com.ias.hoursWorkCalculator.report.application.services;

import co.com.ias.hoursWorkCalculator.report.application.domain.ReportWeekly;
import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;
import co.com.ias.hoursWorkCalculator.report.application.model.ListReportRequest;
import co.com.ias.hoursWorkCalculator.report.application.model.ListReportResponse;
import co.com.ias.hoursWorkCalculator.report.application.model.ListWeeklyReportRequest;
import co.com.ias.hoursWorkCalculator.report.application.model.ListWeeklyReportResponse;
import co.com.ias.hoursWorkCalculator.report.application.ports.in.ListReportWeeklyUseCase;
import co.com.ias.hoursWorkCalculator.report.application.ports.out.ReportRepository;
import co.com.ias.hoursWorkCalculator.report.application.ports.out.ReportWeeklyRepository;

import java.util.Collection;

public class ListWeeklyReportService  implements ListReportWeeklyUseCase {
        private final ReportWeeklyRepository repository;
    private final ReportRepository reportRepository;

        public ListWeeklyReportService(ReportWeeklyRepository repository, ReportRepository reportRepository) {
            this.repository = repository;
            this.reportRepository = reportRepository;
        }

        @Override
        public ListWeeklyReportResponse execute(ListWeeklyReportRequest request) {
            Collection<ReportWeekly> reportWeeklies = repository.listReportsWeekly(
                    request.getLimit(),
                    request.getSkip()
            );
            System.out.println(reportWeeklies);
            Integer total = repository.countReportsWeekly();
            return new ListWeeklyReportResponse(reportWeeklies, total);


        }


    public ListReportResponse execute(ListReportRequest request) {
        Collection<ServiceReport> reports = reportRepository.listReports(
                request.getLimit(),
                request.getSkip()
        );
        Integer total = reportRepository.countReports();
        return new ListReportResponse(reports, total);
    }
}