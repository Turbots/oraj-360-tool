package be.ordina.threeSixty.hystrixServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by stevedezitter on 15/04/15.
 */
@EnableAutoConfiguration
@Configuration
@ComponentScan
@EnableHystrixDashboard
@Controller
public class HystrixServerApplication {

    @RequestMapping("/")
    public String home() {
        return "forward:/hystrix/index.html";
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(HystrixServerApplication.class).web(true).run(args);
    }

}
