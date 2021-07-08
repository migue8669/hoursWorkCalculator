package co.com.ias.hoursWorkCalculator.reportWeekly.infraestructure.configuration;


import co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.in.CreateReportWeeklyUseCase;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.in.ListReportWeeklyUseCase;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.out.ReportWeeklyRepository;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.services.CreateReportWeeklyService;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.services.ListWeeklyReportService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportWeeklyApplicationConfiguration {
    @Bean
    public CreateReportWeeklyUseCase createReportWeeklyServiceBean(
              ReportWeeklyRepository reportWeeklyRepository
    ) {
        return new CreateReportWeeklyService(reportWeeklyRepository);
    }
    @Bean
    public ListReportWeeklyUseCase listStudentsUseCase(
            ReportWeeklyRepository repository
    ) {
        return new ListWeeklyReportService(repository);
    }


}