package com.meteor.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.meteor.coffee.CoffeeEnum;
import com.meteor.dutch.DutchDao;
import com.meteor.dutch.DutchService;

@RequestMapping("dutch")
@Controller
@SessionAttributes("dutch")
public class DutchController {
	private Logger logger = LoggerFactory.getLogger(DutchController.class);
	
	@Autowired
	private DutchService dutchService;
	
	@GetMapping
	public String dutchList(Model model, HttpServletRequest req) {
		model.addAttribute("dutchList", dutchService.getDutchList());
		return "dutch/dutchList";
	}
	
	
	@GetMapping("add")
	public String dutchAddView(SessionStatus ss) {
		return "dutch/dutchRegister";
	}
	
	@PostMapping
	public String dutchAdd(
			@ModelAttribute("inputDutch")
			DutchDao entity) {
		
		Date date = entity.getProduceTime();
		Calendar cal = Calendar.getInstance();
				cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, 45);
		entity.setExpiredTime(cal.getTime());
		
		dutchService.dutchSave(entity);
		
		return "redirect:dutch";
	}

	@ModelAttribute("dutchMap")
	public Map<String,DutchDao> getDutchMap(){
		Map<String,DutchDao> map = new HashMap<>();
		map.put("k11", new DutchDao());
		map.put("k12", new DutchDao());
		map.put("k13", new DutchDao());
		return map;
	}
}
