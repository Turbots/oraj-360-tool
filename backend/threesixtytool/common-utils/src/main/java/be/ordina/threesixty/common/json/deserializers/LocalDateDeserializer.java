package be.ordina.threesixty.common.json.deserializers;

import be.ordina.threesixty.common.utils.dateutils.Java8DateTimeFormatter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by stevedezitter on 15/04/15.
 */
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    @Autowired
    private Java8DateTimeFormatter dateTimeFormatter;

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return dateTimeFormatter.fromString(jsonParser.getValueAsString());
    }
}
