package be.ordina.threesixty.person.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;

import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;

@Configuration
@EnableMongoRepositories(basePackages = { "be.ordina.threesixty.person.repository" })
public abstract class MongoTestConfiguration extends AbstractMongoConfiguration {

	@Override
	public String getDatabaseName() {
		return "threesixty-test";
	}

	@Bean(destroyMethod = "close")
	public Mongo mongo() throws IOException {
		return new EmbeddedMongoBuilder().version("2.4.5").bindIp("127.0.0.1")
				.port(12345).build();
	}

}
