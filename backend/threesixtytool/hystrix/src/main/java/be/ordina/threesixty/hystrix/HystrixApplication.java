package be.ordina.threesixty.hystrix;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by stevedezitter on 15/04/15.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard
@Controller
public class HystrixApplication {

	@RequestMapping("/")
	public String home() {
		return "forward:/hystrix";
	}

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HystrixApplication.class).web(true);
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(HystrixApplication.class).web(true).run(args);
    }

    @Bean
    public ServletRegistrationBean mockStreamServlet() {
        return new ServletRegistrationBean(new MockStreamServlet(), "/mock.stream");
    }

}
