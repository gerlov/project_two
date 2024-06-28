package com.kth.project_dollarstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kth.project_dollarstore.controller.CustomerRepository;
import com.kth.project_dollarstore.model.*;
import com.kth.project_dollarstore.controller.*;
import java.util.*;


@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class ProjectDollarstoreApplication {


	// private final CustomerRepository customerRepository;
	// public Main(CustomerRepository customerRepository){
	// 	this.customerRepository = customerRepository;
	// }



	public static void main(String[] args) {
		SpringApplication.run(ProjectDollarstoreApplication.class, args);
	}


	@GetMapping
	public List<Customer> getCusomters(){
		return java.util.List.of();
	}

}
