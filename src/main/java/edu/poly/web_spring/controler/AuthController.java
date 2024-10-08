package edu.poly.web_spring.controler;

import edu.poly.web_spring.dto.AdminDto;
import edu.poly.web_spring.model.Admin;
import edu.poly.web_spring.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AuthController {
	
	private final AdminService adminService;
	
	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("title", "Login Page");
		return "login";
	}
	
	@PostMapping("/login")
	public String loginSuccess(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
		Admin admin = adminService.findByUsername(username);
		if (admin == null) {
			redirectAttributes.addFlashAttribute("error", "Username not found");
			return "redirect:/login";
		}
		if (admin.getPassword().equals(password)) {
			return "redirect:/index";
		}
		redirectAttributes.addFlashAttribute("error", "Invalid password");
		return "redirect:/login";
	}
	
	
	
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("title", "Home Page");
		
		return "/fragments";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("title", "Register");
		model.addAttribute("adminDto", new AdminDto());
		return "/Register";
	}
	
	@PostMapping("/register-new")
	public String addNewAdmin(@Valid @ModelAttribute("adminDto") AdminDto adminDto,
	                          BindingResult result,
	                          Model model) {
		
		try {
			
			if (result.hasErrors()) {
				model.addAttribute("adminDto", adminDto);
				return "Register";
			}
			String username = adminDto.getUsername();
			Admin admin = adminService.findByUsername(username);
			if (admin != null) {
				model.addAttribute("adminDto", adminDto);
				System.out.println("admin not null");
				model.addAttribute("emailError", "Your email has been registered!");
				return "Register";
			}
			if (adminDto.getPassword().equals(adminDto.getRepeatPassword())) {
				
				adminService.save(adminDto);
				System.out.println("success");
				model.addAttribute("success", "Register successfully!");
				model.addAttribute("adminDto", adminDto);
			} else {
				model.addAttribute("adminDto", adminDto);
				model.addAttribute("passwordError", "Your password maybe wrong! Check again!");
				System.out.println("password not same");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errors", "The server has been wrong!");
		}
		return "Register";
		
	}
	
}
