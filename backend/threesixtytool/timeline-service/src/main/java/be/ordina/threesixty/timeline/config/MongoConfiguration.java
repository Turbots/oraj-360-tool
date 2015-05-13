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
public class MongoConfiguration extends AbstractTimelineServiceMongoConfiguration{

    @Bean
    public Mongo mongo() throws Exception {
        MongoCredential credential = MongoCredential.createCredential("threeSixtyAdmin",
                "threeSixty",
                "password".toCharArray());

        MongoClient client = new MongoClient(Collections.singletonList(new ServerAddress("localhost", 27017)),
                Collections.singletonList(credential));

        return client;
    }

}
