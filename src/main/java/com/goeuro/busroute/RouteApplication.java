package com.goeuro.busroute;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RouteApplication {
    @Value("${routesFile}")
    private String routesFile;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RouteApplication.class, args);
    }

}
