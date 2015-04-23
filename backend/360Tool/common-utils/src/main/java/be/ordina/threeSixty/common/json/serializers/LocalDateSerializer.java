package be.ordina.threeSixty.common.json.serializers;

import be.ordina.threeSixty.common.utils.dateutils.Java8DateTimeFormatter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by stevedezitter on 15/04/15.
 */
public class LocalDateSerializer extends JsonSerializer<LocalDate>{

    @Autowired
    private Java8DateTimeFormatter dateTimeFormatter;

    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeString(dateTimeFormatter.formatDate(localDate));
    }
}
