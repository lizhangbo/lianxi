package com.lzb.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

    //这个可以了
//    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//        return builder.routes().route(p -> p.path("/customer/{segment}").uri("http://localhost:8002").id("customer")).build();
//    }

    //这个也可以
//    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//        return builder.routes().route(p -> p.path("/customer/**").uri("http://localhost:8002").id("customer")).build();
//    }

    //通过服务名
//    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//        return builder.routes().route(p -> p.path("/customer/**").uri("lb://CUSTOMER1").id("customer")).build();
//    }

}
