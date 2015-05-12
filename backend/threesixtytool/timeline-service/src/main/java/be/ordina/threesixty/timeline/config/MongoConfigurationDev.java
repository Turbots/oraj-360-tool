package be.ordina.threesixty.timeline.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Collections;

/**
 * Created by stevedezitter on 12/05/15.
 */
@Configuration
@ComponentScan("be.ordina.threesixty.person.repository")
@Profile("dev")
public class MongoConfigurationDev extends AbstractTimelineServiceMongoConfiguration {
    @Override
    public Mongo mongo() throws Exception {
        MongoCredential credential = MongoCredential.createCredential("threeSixtyAdmin",
                "threeSixty",
                "password".toCharArray());

        MongoClient client = new MongoClient(Collections.singletonList(new ServerAddress("mongodb", 27017)),
                Collections.singletonList(credential));

        return client;
    }
}
