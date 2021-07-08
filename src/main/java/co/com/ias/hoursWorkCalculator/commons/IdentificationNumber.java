package co.com.ias.hoursWorkCalculator.commons;

import io.vavr.control.Validation;
import org.apache.commons.lang3.StringUtils;
import java.util.Objects;

public class IdentificationNumber{
        private final String value;
        private static final String NULL_MESSAGE = "IdentificationNumber can not be null";
        private static final String EMPTY_MESSAGE = "IdentificationNumber can not be empty";
        private static final String INVALID_PATTERN_MESSAGE = "Invalid identification number";
        public IdentificationNumber(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "IdentificationNumber{" +
                    "value='" + value + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IdentificationNumber that = (IdentificationNumber) o;
            return Objects.equals(value, that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        public String getValue() {
            return value;
        }

        public static Validation<InputAttributeError, IdentificationNumber> parse(
                String unsafeValue,
                String attributeName
        ) {
            Objects.requireNonNull(attributeName);

            if(unsafeValue == null) {
                return Validation.invalid(new InputAttributeError(attributeName, NULL_MESSAGE));
            }
            if(StringUtils.isBlank(unsafeValue)) {
                return Validation.invalid(new InputAttributeError(attributeName, EMPTY_MESSAGE));
            }


            return Validation.valid(new IdentificationNumber(unsafeValue));
        }



}