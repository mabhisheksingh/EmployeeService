package com.completablefuture.repositery;

import com.completablefuture.pojo.Employee;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class DynamicEmployeeRepositery {

    //Thread to stop process for 1 sec
    private static void delayExecution(int i)  {
        try{
            System.out.println(i);
            Thread.sleep(1000);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
    //traditional way to get employee
    public List<Employee> getListOfEmployee(){
        List<Employee> listEmployee = IntStream.rangeClosed(1, 50)
                .peek(DynamicEmployeeRepositery::delayExecution)
                .mapToObj(emp -> new Employee((long) emp, "Employee" + emp, (emp * 20) % 80, (long) emp))
                .collect(Collectors.toList());
        return listEmployee;
    }

    //using Flux we can send data in Asynchronous and non-blocking way
    public Flux<Employee> getListOfEmployeeStream(){
        Flux<Employee> listEmployee = Flux.range(1,50).log()
                .doOnNext(DynamicEmployeeRepositery::delayExecution)
                .map(emp -> new Employee((long) emp, "Employee" + emp, (emp * 20) % 80, (long) emp));
        return listEmployee;
    }

}
