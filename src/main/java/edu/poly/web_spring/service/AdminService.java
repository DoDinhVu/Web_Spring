package edu.poly.web_spring.service;

import edu.poly.web_spring.dto.AdminDto;
import edu.poly.web_spring.model.Admin;

public interface AdminService {
	public Admin save(AdminDto adminDto);
	public Admin findByUsername(String username);
}
