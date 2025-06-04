package com.employee.management;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.employee.management.model.Employee;
import com.employee.management.model.Role;
import com.employee.management.service.IEmployeeService;
import com.employee.management.service.IRoleService;

@SpringBootApplication
public class ManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(IEmployeeService employeeService, IRoleService roleService) {
		return args -> {
			Role empRole = roleService.saveRole(Role.builder().name("EMPLOYEE").build());
			Role hrRole = roleService.saveRole(Role.builder().name("HR").build());

			employeeService.saveEmployee(
					Employee.builder()
							.firstName("abc")
							.lastName("abc")
							.email("abc@gmail.com")
							.contact("1234567890")
							.address("abc")
							.birthDate("01/01/2000")
							.qualification("abc")
							.password("abc")
							.role(empRole)
							.build());

			employeeService.saveEmployee(
					Employee.builder()
							.firstName("hr")
							.lastName("hr")
							.email("hr@gmail.com")
							.contact("1234567890")
							.address("abc")
							.birthDate("01/01/2000")
							.qualification("abc")
							.password("abc")
							.role(hrRole)
							.build());
		};
	}
}
