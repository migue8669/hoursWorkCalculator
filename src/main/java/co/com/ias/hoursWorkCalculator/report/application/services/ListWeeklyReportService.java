package co.com.ias.hoursWorkCalculator.report.application.services;

import co.com.ias.hoursWorkCalculator.report.application.domain.ReportWeekly;
import co.com.ias.hoursWorkCalculator.report.application.model.ListWeeklyReportRequest;
import co.com.ias.hoursWorkCalculator.report.application.model.ListWeeklyReportResponse;
import co.com.ias.hoursWorkCalculator.report.application.ports.in.ListReportWeeklyUseCase;
import co.com.ias.hoursWorkCalculator.report.application.ports.out.ReportWeeklyRepository;

import java.util.Collection;

public class ListWeeklyReportService  implements ListReportWeeklyUseCase {
        private final ReportWeeklyRepository repository;

        public ListWeeklyReportService(ReportWeeklyRepository repository) {
            this.repository = repository;
        }

        @Override
        public ListWeeklyReportResponse execute(ListWeeklyReportRequest request) {
            Collection<ReportWeekly> reportWeeklies = repository.listReportsWeekly(
                    request.getLimit(),
                    request.getSkip()
            );
            Integer total = repository.countReportsWeekly();
            return new ListWeeklyReportResponse(reportWeeklies, total);
        }
}