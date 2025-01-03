package com.example.demo;

import com.example.demo.client.EmployeeClient;
import com.example.demo.dto.EmployeeDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DemoApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;

	@Test
	@Order(3)
	void getAllEmployeesTest(){
		List<EmployeeDTO> employeeDTOList = employeeClient.getAllEmployees();
	    System.out.println(employeeDTOList);
	}

	@Test
	@Order(2)
	void getEmployeeByIdTest(){
		EmployeeDTO employeeDTO = employeeClient.getEmployeeById(1giL);
		System.out.println(employeeDTO);
	}

	@Test
	@Order(1)
	void createNewEmployee(){
		EmployeeDTO employeeDTO1 = new EmployeeDTO(null, 20, "Krishna", "a@g.com",
				LocalDate.of(2020, 3, 3), true);
		EmployeeDTO savedEmployeeDTO = employeeClient.createNewEmployee(employeeDTO1);
		System.out.println(savedEmployeeDTO);
	}

}
