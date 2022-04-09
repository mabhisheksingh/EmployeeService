package com.completablefuture.controller;


import com.completablefuture.pojo.Employee;
import com.completablefuture.repositery.DynamicEmployeeRepositery;
import com.completablefuture.repositery.EmployeeRepositery;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    //private final static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeRepositery employeeRespositery;

    @Autowired
    private DynamicEmployeeRepositery dynamicEmployeeRepositery;

    @PostConstruct
	public void PostConstruct(){
        log.info("on-load data insertion starting");
        List<Employee> employeeList = Arrays.asList(
                new Employee(1L,"Abhishek Singh",24,null),
                new Employee(2L,"Abhishek Singh1",25,100L),
                new Employee(3L,"Abhishek Singh2",26,101L),
                new Employee(4L,"Abhishek Singh3",28,102L),
                new Employee(5L,"Abhishek Singh4",28,103L)
        );
		employeeRespositery.saveList(employeeList);
        log.info("on-load data insertion Ending");
	}

    @PostMapping(value = "/saveEmp" )
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        log.info("find by Name method called for data creation");
        System.out.println("Employee : "+employee);
        return ResponseEntity.ok(employeeRespositery.save(employee) );
    }
    @GetMapping("/getAllEmp")
    public ResponseEntity<List<Employee>> getAllEmp(@RequestHeader Map<String,String> headers){
        headers.forEach((k, v) -> System.out.println((k + ":" + v)));
        return ResponseEntity.ok(employeeRespositery.getAllEmployee());
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> DeleteById(@PathVariable Long id){
        employeeRespositery.deleteById(id);
        log.info("Employee Id "+id+" deleted successfully ... ");
        return ResponseEntity.ok().build();
    }

    //traditional way to get list of employee
    @GetMapping(value = "/getListOfEmp")
    public ResponseEntity<List<Employee>> getListOfEmp(){
        List<Employee> list = dynamicEmployeeRepositery.getListOfEmployee();
        return ResponseEntity.ok().body(list);
    }


    ////new Asynchronus non blocking way to get list of employee
    @GetMapping(value = "/getListOfEmpStream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<Employee>> getListOfEmpStream(){
        Flux<Employee> list = dynamicEmployeeRepositery.getListOfEmployeeStream();
        return ResponseEntity.ok().body(list);
    }

}
