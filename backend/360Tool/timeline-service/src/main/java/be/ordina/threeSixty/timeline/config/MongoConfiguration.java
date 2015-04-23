package be.ordina.threeSixty.timeline.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
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
 * Created by stevedezitter on 21/04/15.
 */
@Configuration
@ComponentScan("be.ordina.threeSixty.person.repository")
public class MongoConfiguration extends AbstractMongoConfiguration{

    @Bean
    public Mongo mongo() throws Exception {
        MongoCredential credential = MongoCredential.createCredential("threeSixtyAdmin",
                "threeSixty",
                "password".toCharArray());

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
        return "be.ordina.threeSixty.timeline.model";
    }

    @Bean
    @Override
    public CustomConversions customConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<>();
        converterList.add(new be.ordina.threeSixty.common.repository.converter.DbObjectToLocalDateConverter());
        return new CustomConversions(converterList);
    }
}
