package com.completablefuture.repositery;

import com.completablefuture.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepositery {

    @Autowired
    private RedisTemplate redisTemplate;

    public static final String HASH_KEY = "EMPLOYEE";

    public Employee save(Employee employee) {
        redisTemplate.opsForHash().put(HASH_KEY, employee.getId(), employee);
        return employee;
    }
    public void saveList(List<Employee> employeeList){
        for(Employee emp : employeeList)
            redisTemplate.opsForHash().put(HASH_KEY,emp.getId(),emp);
    }

    public List<Employee> getAllEmployee(){
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    public void deleteById(Long id){
        redisTemplate.opsForHash().delete(HASH_KEY,id);
    }
}