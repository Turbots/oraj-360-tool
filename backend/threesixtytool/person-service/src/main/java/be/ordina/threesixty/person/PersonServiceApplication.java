package be.ordina.threesixty.person;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;

/**
 * Created by stevedezitter on 14/04/15.
 */
@SpringCloudApplication
@ComponentScan({"be.ordina.threesixty.person","be.ordina.threesixty.common"})
@EnableRetry
public class PersonServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonServiceApplication.class, args);
    }
}
