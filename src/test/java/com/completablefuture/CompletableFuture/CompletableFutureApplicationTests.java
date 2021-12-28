package com.completablefuture.CompletableFuture;

import com.completablefuture.controller.EmployeeController;
import com.completablefuture.pojo.Employee;
import com.completablefuture.repositery.DynamicEmployeeRepositery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CompletableFutureApplicationTests {

	@Autowired
	private EmployeeController employeeController;

	@Autowired
	private DynamicEmployeeRepositery dynamicEmployeeRepositery;

	@Test
	void saveEmployee() {
		Employee  emp  = new Employee(100L,"testing",26,100L);
		ResponseEntity<Employee> e1 = employeeController.saveEmployee(emp);
		assertEquals(emp,e1.getBody());
	}

}
