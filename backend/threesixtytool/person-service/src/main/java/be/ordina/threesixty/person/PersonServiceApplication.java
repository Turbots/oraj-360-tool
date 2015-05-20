package be.ordina.threesixty.person;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by stevedezitter on 14/04/15.
 */
@SpringCloudApplication
@ComponentScan({"be.ordina.threesixty.person","be.ordina.threesixty.common"})
public class PersonServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonServiceApplication.class, args);
    }
}
