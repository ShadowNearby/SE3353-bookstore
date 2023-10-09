package com.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableDiscoveryClient
class GatewayApplication {
    @Bean
    fun routeLocator(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
            .route { r ->
                r.path("/microservice/**")
                    .filters { f -> f.rewritePath("/microservice", "") }
                    .uri("lb://BOOK-SERVICE")
            }
            .build()
    }
}

fun main(args: Array<String>) {
    runApplication<GatewayApplication>(*args)
}

