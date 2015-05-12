package be.ordina.threesixty.timeline.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by stevedezitter on 21/04/15.
 */
@Configuration
@Profile("default")
@EnableMongoRepositories(basePackages={"be.ordina.threesixty.timeline.repository"})
public class MongoConfiguration extends AbstractMongoConfiguration{

    @Bean
    public Mongo mongo() throws Exception {
        MongoCredential credential = MongoCredential.createCredential("threesixtyAdmin",
                "threesixty",
                "password".toCharArray());

        MongoClient client = new MongoClient(Collections.singletonList(new ServerAddress("macbook-pro-van-steve.local", 27017)),
                Collections.singletonList(credential));

        return client;
    }

    @Override
    public String getDatabaseName() {
        return "threesixty";
    }

    @Override
    public String getMappingBasePackage() {
        return "be.ordina.threesixty.timeline.model";
    }

    @Bean
    @Override
    public CustomConversions customConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<>();
        converterList.add(new be.ordina.threesixty.common.repository.converter.DbObjectToLocalDateConverter());
        return new CustomConversions(converterList);
    }
}
