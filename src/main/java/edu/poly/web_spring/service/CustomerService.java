package edu.poly.web_spring.service;

import edu.poly.web_spring.dto.CustomerDto;
import edu.poly.web_spring.model.Customer;

public interface CustomerService {
	Customer save(CustomerDto customerDto);
	
	Customer findByUsername(String username);
	
	Customer update(CustomerDto customerDto);
	
	Customer changePass(CustomerDto customerDto);
	
	CustomerDto getCustomer(String username);
}
