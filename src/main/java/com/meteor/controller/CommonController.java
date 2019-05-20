package com.meteor.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.meteor.dutch.DutchDao;

@Controller
public class CommonController {
	private Logger logger = LoggerFactory.getLogger(CommonController.class);
	@GetMapping("")
	public String index(Model model, HttpServletRequest req, SessionStatus ss) {
		Object dutchObj = req.getSession().getAttribute("dutch");
		logger.info("dutchObj : {}", dutchObj);
		
		Object obj = model.asMap().get("dutch");
		if(obj==null) {
			model.addAttribute("dutch", new DutchDao());
		}
		logger.info("obj : {}", obj);
		ss.setComplete();
		
		return "index";
	}
	@GetMapping("login")
	public String login() {
		return "login";
	}
	

}
