package com.completablefuture.functionalendpoint.router;

import com.completablefuture.functionalendpoint.handler.HandlerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;


///Can't use RouterFunction and RestController together [SPR-15405]

@Configuration
public class RouterConfig {

    private Logger log = LoggerFactory.getLogger(RouterConfig.class);
    @Autowired
    private HandlerConfig handlerConfig;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        log.info("Router function calling");
        return RouterFunctions.route()
                .GET("/GetAllEmps",handlerConfig::getAllEmp)
                .build();
    }
}
