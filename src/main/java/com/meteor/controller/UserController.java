package com.meteor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.meteor.user.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userSvc;
	
	@GetMapping("")
	public String userlist(Model model) {
		model.addAttribute("userList", userSvc.getUserList());
		return "userList";
	}
	
	@GetMapping("{id}")
	public String userlist(@PathVariable("id") String id) {
		return "index";
	}
	
	@GetMapping("userRegister")
	public String userRegister() {
		return "userRegister";
	}
	
}
