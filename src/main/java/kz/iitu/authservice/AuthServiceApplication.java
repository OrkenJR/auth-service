package kz.iitu.authservice;

import kz.iitu.cfaslib.config.EnableCfasLib;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class},
        scanBasePackages = {"kz.iitu.cfaslib", "kz.iitu.authservice"}
)
@EnableFeignClients
@EnableEurekaClient
@EnableCfasLib
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

}
