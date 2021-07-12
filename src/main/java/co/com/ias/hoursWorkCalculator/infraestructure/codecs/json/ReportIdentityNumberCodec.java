package co.com.ias.hoursWorkCalculator.infraestructure.codecs.json;

import co.com.ias.hoursWorkCalculator.report.application.domain.ReportIdentityNumber;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ReportIdentityNumberCodec {

    public static class ReportIdentityNumberEncoder extends JsonSerializer<ReportIdentityNumber> {

        @Override
        public void serialize(ReportIdentityNumber identificationNumber, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(identificationNumber.getValue());
        }
    }

    public static class IdentificationNumberDecoder extends JsonDeserializer<ReportIdentityNumber> {

        @Override
        public ReportIdentityNumber deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            final String valueAsString = jsonParser.getValueAsString();
            return new ReportIdentityNumber(valueAsString);
        }
    }
}
