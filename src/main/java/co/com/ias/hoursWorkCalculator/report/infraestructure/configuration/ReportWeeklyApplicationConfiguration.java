package co.com.ias.hoursWorkCalculator.report.infraestructure.configuration;

import co.com.ias.hoursWorkCalculator.report.application.ports.in.CreateReportWeeklyUseCase;
import co.com.ias.hoursWorkCalculator.report.application.ports.in.ListReportWeeklyUseCase;
import co.com.ias.hoursWorkCalculator.report.application.ports.out.ReportRepository;
import co.com.ias.hoursWorkCalculator.report.application.ports.out.ReportWeeklyRepository;
import co.com.ias.hoursWorkCalculator.report.application.services.CreateReportWeeklyService;
import co.com.ias.hoursWorkCalculator.report.application.services.ListWeeklyReportService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportWeeklyApplicationConfiguration {
    @Bean
    public CreateReportWeeklyUseCase createReportWeeklyServiceBean(
            ReportWeeklyRepository reportWeeklyRepository,
            ReportRepository reportRepository
    ) {
        return new CreateReportWeeklyService(reportWeeklyRepository, reportRepository);
    }
    @Bean
    public ListReportWeeklyUseCase listReportWeeklyUseCase(
            ReportWeeklyRepository reportWeeklyRepository,
            ReportRepository reportRepository
    ) {
        return new ListWeeklyReportService(reportWeeklyRepository, reportRepository);
    }


}