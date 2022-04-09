package com.completablefuture.functionalendpoint.handler;

import com.completablefuture.pojo.Employee;
import com.completablefuture.repositery.DynamicEmployeeRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HandlerConfig {

    @Autowired
    private DynamicEmployeeRepositery dynamicEmployeeRepositery;

    public Mono<ServerResponse> getAllEmp(ServerRequest serverRequest){
        Flux<Employee> list = dynamicEmployeeRepositery.getListOfEmployeeStream();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(list,Employee.class);
    }


}
