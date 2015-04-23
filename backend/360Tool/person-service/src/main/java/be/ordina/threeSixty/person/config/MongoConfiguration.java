package be.ordina.threeSixty.person.config;

import be.ordina.threeSixty.common.repository.converter.DbObjectToLocalDateConverter;
import com.mongodb.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by stevedezitter on 14/04/15.
 */
@Configuration
@ComponentScan("be.ordina.threeSixty.person.repository")
public class MongoConfiguration extends AbstractMongoConfiguration{

    @Bean
    public Mongo mongo() throws Exception {
        MongoCredential credential = MongoCredential.createCredential("threeSixtyAdmin",
                "threeSixty",
                "password".toCharArray());

//        MongoClient client = new MongoClient(new ServerAddress("macbook-pro-van-steve.local",27017)); // , Arrays.asList(credential));

        MongoClient client = new MongoClient(Collections.singletonList(new ServerAddress("macbook-pro-van-steve.local", 27017)),
                Collections.singletonList(credential));

        return client;
    }

    @Override
    public String getDatabaseName() {
        return "threeSixty";
    }

    @Override
    public String getMappingBasePackage() {
        return "be.ordina.threeSixty.person.model";
    }

    @Bean
    @Override
    public CustomConversions customConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<>();
        converterList.add(new DbObjectToLocalDateConverter());
//        converterList.add(new be.ordina.threeSixty.common.repository.converter.LocalDateToDbObjectConverter());
        return new CustomConversions(converterList);
    }

}
