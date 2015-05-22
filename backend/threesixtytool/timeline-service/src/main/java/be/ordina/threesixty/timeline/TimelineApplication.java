package be.ordina.threesixty.timeline;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;

/**
 * Created by stevedezitter on 15/04/15.
 */
@SpringCloudApplication
@ComponentScan({"be.ordina.threesixty.timeline","be.ordina.threesixty.common", "be.ordina.threesixty.timeline.config"})
@EnableRetry
public class TimelineApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimelineApplication.class, args);
    }
}
