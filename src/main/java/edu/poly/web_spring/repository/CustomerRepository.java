package edu.poly.web_spring.repository;

import edu.poly.web_spring.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public Customer findByUsername(String username);
}
