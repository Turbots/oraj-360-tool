package be.ordina.threeSixty.common.config;

import be.ordina.threeSixty.common.json.deserializers.LocalDateDeserializer;
import be.ordina.threeSixty.common.json.serializers.LocalDateSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;

/**
 * Created by stevedezitter on 15/04/15.
 */
@Configuration
public class JacksonConfig {

    @Bean
    public LocalDateSerializer getLocalDateSerializer() {
        return new LocalDateSerializer();
    }

    @Bean
    public LocalDateDeserializer getLocalDateDeserializer() {
        return new LocalDateDeserializer();
    }

    @Bean
    @Primary
    public ObjectMapper customizedJacksonObjectMapper() {
        SimpleModule localDateModule =
                new SimpleModule("LocalDateSerializer");
        localDateModule.addSerializer(LocalDate.class, getLocalDateSerializer());
        localDateModule.addDeserializer(LocalDate.class, getLocalDateDeserializer());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(localDateModule);

        return objectMapper;
    }
}
