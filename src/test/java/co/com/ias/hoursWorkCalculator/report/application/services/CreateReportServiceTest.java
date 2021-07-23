package co.com.ias.hoursWorkCalculator.report.application.services;

import co.com.ias.hoursWorkCalculator.commons.NonEmptyString;
import co.com.ias.hoursWorkCalculator.commons.ReportIdentityNumber;
import co.com.ias.hoursWorkCalculator.commons.TechnicianIdentityNumber;
import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;
import co.com.ias.hoursWorkCalculator.report.application.errors.ReportAlreadyExistsError;
import co.com.ias.hoursWorkCalculator.report.application.model.CreateReportRequest;
import co.com.ias.hoursWorkCalculator.report.application.model.CreateReportResponse;
import co.com.ias.hoursWorkCalculator.report.application.ports.out.ReportRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.text.ParseException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CreateReportServiceTest {
    @Test
    void ifReportDoesNotExistsItGetsCreated() throws ParseException {
        // arrange
        ReportRepository repository = Mockito.mock(ReportRepository.class);
        Mockito.when(repository.getReportById(ArgumentMatchers.any(ReportIdentityNumber.class)))
                .thenReturn(Optional.empty());
        CreateReportService createStudentService = new CreateReportService(repository);
        final String idNumber = "12345678";
        CreateReportRequest request = new CreateReportRequest(
                "reportIdentityNumber",
                "technicianIdentity",
                "hourInit",
                "hourFinish",
                "dateInit",
                "dateFinish",
                "numWeek");
        // act
        CreateReportResponse response = createStudentService.execute(request);
        // assert
        assertAll(
                () -> assertDoesNotThrow(() -> createStudentService.execute(request)),
                () -> assertEquals(
                        response.getServiceReport().getReportIdentityNumber().getValue(),
                        idNumber
                ),
                () -> Mockito.verify(repository, Mockito.times(2))
                        .getReportById(ArgumentMatchers.any(ReportIdentityNumber.class))
        );
    }
    @Test
    void ifStudentExistsItThrowsAnException() {
        ServiceReport serviceReport = new ServiceReport(
                new ReportIdentityNumber("12345678"),
                new TechnicianIdentityNumber("12345678"),
                new NonEmptyString("hourInit"),
                new NonEmptyString("dateInit"),
                new NonEmptyString("hourFinish"),
                new NonEmptyString("dateFinish"),
                new NonEmptyString("numWeek")


        );
        ReportRepository repository = Mockito.mock(ReportRepository.class);
        Mockito.when(repository.getReportById(ArgumentMatchers.any(ReportIdentityNumber.class)))
                .thenReturn(Optional.of(serviceReport));
        CreateReportService createReportService = new CreateReportService(repository);
        CreateReportRequest request = new CreateReportRequest(
                serviceReport.getReportIdentityNumber().getValue(),
                serviceReport.getTechnicianIdentity().getValue(),
                serviceReport.getHourInit().getValue(),
                serviceReport.getDateInit().getValue(),
                serviceReport.getHourFinish().getValue(),
                serviceReport.getDateFinish().getValue(),
                serviceReport.getNumWeek().getValue()


        );
        assertAll(
                () -> assertThrows(ReportAlreadyExistsError.class, () -> createReportService.execute(request)),
                () -> Mockito.verify(repository, Mockito.times(0))
                        .storeReport(ArgumentMatchers.any(ServiceReport.class))
        );
    }
}
