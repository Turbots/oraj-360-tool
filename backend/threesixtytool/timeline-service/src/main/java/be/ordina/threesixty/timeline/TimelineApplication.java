package be.ordina.threesixty.timeline;

import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.HAL;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.config.EnableHypermediaSupport;

/**
 * Created by stevedezitter on 15/04/15.
 */
@SpringCloudApplication
@ComponentScan({"be.ordina.threesixty.timeline","be.ordina.threesixty.common", "be.ordina.threesixty.timeline.config"})
public class TimelineApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimelineApplication.class, args);
    }
}
