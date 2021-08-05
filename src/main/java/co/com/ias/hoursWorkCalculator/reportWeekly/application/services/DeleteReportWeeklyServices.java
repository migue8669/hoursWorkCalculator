package co.com.ias.hoursWorkCalculator.reportWeekly.application.services;

import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.ReportWeekly;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.model.ListDeletedReportWeeklyRequest;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.model.ListDeletedReportWeeklyResponse;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.in.DeleteReportWeeklyUseCase;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.out.ReportWeeklyRepository;

import java.util.Collection;
import java.util.Optional;

public class DeleteReportWeeklyServices implements DeleteReportWeeklyUseCase {
    private final ReportWeeklyRepository repository;

    public DeleteReportWeeklyServices(ReportWeeklyRepository repository) {
        this.repository = repository;
    }


    @Override
    public ListDeletedReportWeeklyResponse execute(ListDeletedReportWeeklyRequest request) {
        System.out.println(request);
        Collection<ReportWeekly> reportWeeklies = repository.listReportsWeekly();
        reportWeeklies= repository.remove(reportWeeklies);
       // boolean reportWeekly =  reportWeeklies.removeAll(reportWeeklies);

        System.out.println("reportWeeklies "+reportWeeklies);


        Integer total = repository.countReportsWeekly();
        return new ListDeletedReportWeeklyResponse(reportWeeklies);
    }


}
