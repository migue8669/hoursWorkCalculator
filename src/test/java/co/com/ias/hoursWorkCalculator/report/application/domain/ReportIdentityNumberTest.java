package co.com.ias.hoursWorkCalculator.report.application.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
public class ReportIdentityNumberTest {
    @Test
    @DisplayName("Null is not a valid id number")
    void nullIsNotValidIdNumber() {
        //AAA, [Arrange, Act, Assert]
        String nullId = null;
        // act
        Executable operation = () -> new ReportIdentityNumber(nullId);
        // assert
        assertThrows(NullPointerException.class, operation);
    }
    @Test
    void emptyStringIsNotValidIdNumber() {
        String emptyString = "";
        Executable operation = () -> new ReportIdentityNumber(emptyString);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, operation);
        assertEquals(illegalArgumentException.getMessage(), "IdentificationNumber can not be empty");
    }
    @Test
    void whiteSpaceStringIsNotValidIdNumber() {
        String whiteSpaceString = " ";
        Executable operation = () -> new ReportIdentityNumber(whiteSpaceString);
        assertThrows(IllegalArgumentException.class, operation);
    }
    @ParameterizedTest
    @ValueSource(strings = {
            "12345678",
            "123456789",
            "1234567890",
            "12345678901",
            "123456789012",
            "1234567890123",
            "12345678901234",
            "123456789012345",
            "1234567890123456",
            "12345678901234567",
            "123456789012345678",
            "1234567890123456789",
            "12345678901234567890",
    })
    void _validDigitsStringRangeIsValidIdNumber(String idNumberString) {
        Executable operation = () -> new ReportIdentityNumber(idNumberString);
        assertDoesNotThrow(operation);
    }
    @Test
    void _7DigitsStringIsValidIdNumber() {
        String idNumberStr = "1234567";
        Executable operation = () -> new ReportIdentityNumber(idNumberStr);
        assertThrows(IllegalArgumentException.class, operation);
    }
    @Test
    void _21DigitsStringIsValidIdNumber() {
        String idNumberStr = "123456789012345678901";
        Executable operation = () -> new ReportIdentityNumber(idNumberStr);
        assertThrows(IllegalArgumentException.class, operation);
    }

}

