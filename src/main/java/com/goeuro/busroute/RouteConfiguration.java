package com.goeuro.busroute;

import com.goeuro.busroute.data.RouteData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class RouteConfiguration {

    @Bean
    public RouteData getRouteData(@Value("${routesFile}") String routesFile) throws IOException {
        return new RouteData(routesFile);
    }
}
