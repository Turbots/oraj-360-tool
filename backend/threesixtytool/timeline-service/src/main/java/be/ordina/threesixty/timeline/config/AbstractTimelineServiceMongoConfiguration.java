package be.ordina.threesixty.timeline.config;

import be.ordina.threesixty.common.repository.converter.DbObjectToLocalDateConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stevedezitter on 12/05/15.
 */
public abstract class AbstractTimelineServiceMongoConfiguration extends AbstractMongoConfiguration {

    @Override
    public String getDatabaseName() {
        return "threeSixty";
    }

    @Override
    public String getMappingBasePackage() {
        return "be.ordina.threesixty.person.model";
    }

    @Bean
    @Override
    public CustomConversions customConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<>();
        converterList.add(new DbObjectToLocalDateConverter());
        return new CustomConversions(converterList);
    }

}
