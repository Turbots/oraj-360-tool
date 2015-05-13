package be.ordina.threesixty.person.config;

import be.ordina.threesixty.common.repository.converter.DbObjectToLocalDateConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stevedezitter on 12/05/15.
 */
@EnableMongoRepositories(basePackages={"be.ordina.threesixty.person.repository"})
public abstract class AbstractPersonServiceMongoConfiguration extends AbstractMongoConfiguration {

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
