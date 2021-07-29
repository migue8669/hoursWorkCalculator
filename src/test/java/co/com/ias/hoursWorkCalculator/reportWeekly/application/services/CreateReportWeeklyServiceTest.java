package co.com.ias.hoursWorkCalculator.reportWeekly.application.services;

import co.com.ias.hoursWorkCalculator.commons.NonEmptyString;
import co.com.ias.hoursWorkCalculator.commons.ReportIdentityNumber;
import co.com.ias.hoursWorkCalculator.commons.TechnicianIdentityNumber;
import co.com.ias.hoursWorkCalculator.report.application.domain.ServiceReport;
import co.com.ias.hoursWorkCalculator.report.application.errors.ReportAlreadyExistsError;
import co.com.ias.hoursWorkCalculator.report.application.model.CreateReportRequest;
import co.com.ias.hoursWorkCalculator.report.application.model.CreateReportResponse;
import co.com.ias.hoursWorkCalculator.report.application.ports.out.ReportRepository;
import co.com.ias.hoursWorkCalculator.report.application.services.CreateReportService;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.domain.ReportWeekly;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.errors.ReportWeeklyAlreadyExistError;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.model.CreateReportWeeklyRequest;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.model.CreateReportWeeklyResponse;
import co.com.ias.hoursWorkCalculator.reportWeekly.application.ports.out.ReportWeeklyRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.text.ParseException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CreateReportWeeklyServiceTest {
    @Test
    void ifReportWeeklyDoesNotExistsItGetsCreated() throws ParseException {
        // arrange
        ReportRepository repository = Mockito.mock(ReportRepository.class);
        ReportWeeklyRepository reportWeeklyRepository = Mockito.mock(ReportWeeklyRepository.class);

        Mockito.when(repository.getReportById(ArgumentMatchers.any(ReportIdentityNumber.class)))
                .thenReturn(Optional.empty());
        CreateReportWeeklyService createReportWeeklyService = new CreateReportWeeklyService(reportWeeklyRepository,repository);
        final String idNumber = "12345678";
        CreateReportWeeklyRequest request = new CreateReportWeeklyRequest(
                "technicianIdentity",
                "hour",
                "nightHour",
                "sundayHour",
                "extraHour",
                "extraNightHour",
                "extraSundayHour",
                "numWeek");
        // act
        CreateReportWeeklyResponse response = createReportWeeklyService.execute(request);
        // assert
        assertAll(
                () -> assertDoesNotThrow(() -> createReportWeeklyService.execute(request)),
                () -> assertEquals(
                        response.getReportWeekly().getTechnicianIdentity().getValue(),
                        idNumber
                ),
                () -> Mockito.verify(repository, Mockito.times(2))
                        .getReportById(ArgumentMatchers.any(ReportIdentityNumber.class))
        );
    }
    @Test
    void ifReportWeeklyExistsItThrowsAnException() {
        ReportWeekly reportWeekly = new ReportWeekly(
                new TechnicianIdentityNumber("12345678"),
                new NonEmptyString("hour"),
                new NonEmptyString("nightHour"),
                new NonEmptyString("sundayHour"),
                new NonEmptyString("extraHour"),
                new NonEmptyString("extraNightHour"),
                new NonEmptyString("extraSundayHour"),
                new NonEmptyString("numWeek")




        );
        ReportWeeklyRepository weeklyRepository = Mockito.mock(ReportWeeklyRepository.class);
        ReportRepository repository = Mockito.mock(ReportRepository.class);

        Mockito.when(weeklyRepository.getReportWeeklyById(ArgumentMatchers.any(TechnicianIdentityNumber.class)))
                .thenReturn(Optional.of(reportWeekly));
        CreateReportWeeklyService createReportService = new CreateReportWeeklyService(weeklyRepository,repository);
        CreateReportWeeklyRequest request = new CreateReportWeeklyRequest(
                reportWeekly.getTechnicianIdentity().getValue(),
                reportWeekly.getHour().getValue(),
                reportWeekly.getNightHour().getValue(),
                reportWeekly.getSundayHour().getValue(),
                reportWeekly.getExtraHour().getValue(),
                reportWeekly.getExtraNightHour().getValue(),
                reportWeekly.getExtraSundayHour().getValue(),

                reportWeekly.getNumWeek().getValue()


        );
        assertAll(
                () -> assertThrows(ReportWeeklyAlreadyExistError.class, () -> createReportService.execute(request)),
                () -> Mockito.verify(weeklyRepository, Mockito.times(0))
                        .storeReportWeekly(ArgumentMatchers.any(ReportWeekly.class))
        );
    }
}
