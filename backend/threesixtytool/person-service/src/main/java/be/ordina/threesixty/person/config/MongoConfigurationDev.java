package be.ordina.threesixty.person.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collections;

/**
 * Configuration for MongoDB in DEV profile.
 */
@Configuration
@EnableMongoRepositories(basePackages = {"be.ordina.threesixty.timeline.repository"})
@Profile("dev")
public class MongoConfigurationDev extends AbstractPersonServiceMongoConfiguration {
    @Override
    public Mongo mongo() throws Exception {
        MongoCredential credential = MongoCredential.createCredential("threeSixtyAdmin",
                "threeSixty",
                "password".toCharArray());

        return new MongoClient(Collections.singletonList(new ServerAddress("localhost", 27017)),
                Collections.singletonList(credential));
    }
}
