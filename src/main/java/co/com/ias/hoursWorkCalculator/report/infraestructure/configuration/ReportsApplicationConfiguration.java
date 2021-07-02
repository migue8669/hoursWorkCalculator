package co.com.ias.hoursWorkCalculator.report.infraestructure.configuration;

import co.com.ias.hoursWorkCalculator.report.application.ports.in.CreateReportUseCase;
import co.com.ias.hoursWorkCalculator.report.application.ports.in.CreateReportWeeklyUseCase;
import co.com.ias.hoursWorkCalculator.report.application.ports.in.ListReportsUseCase;
import co.com.ias.hoursWorkCalculator.report.application.ports.out.ReportRepository;
import co.com.ias.hoursWorkCalculator.report.application.ports.out.ReportWeeklyRepository;
import co.com.ias.hoursWorkCalculator.report.application.services.CreateReportService;
import co.com.ias.hoursWorkCalculator.report.application.services.CreateReportWeeklyService;
import co.com.ias.hoursWorkCalculator.report.application.services.ListReportService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportsApplicationConfiguration {
    @Bean
    public CreateReportUseCase createReportServiceBean(
            ReportRepository reportRepository
    ) {
        return new CreateReportService(reportRepository);
    }

    @Bean
    public CreateReportWeeklyUseCase createReportWeeklyServiceBean(
            ReportWeeklyRepository reportWeeklyRepository
    ) {
        return new CreateReportWeeklyService(reportWeeklyRepository);
    }

    @Bean
    public ListReportsUseCase listReportsUseCase(
            @Qualifier("sql") ReportRepository repository
    ) {
        return new ListReportService(repository);
    }
}