package com.meteor.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.meteor.ExcelViewConfig;
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
		
		Object obj = model.asMap().get("dutch");
		System.out.println("dutch : " + obj);
		
		return "dutch/dutchList";
	}

//	@GetMapping("*.xls")
//	public List<DutchDao> dutchXls(Model model, HttpServletRequest req) {
//		List<DutchDao> list = dutchService.getDutchList();
//		return list;
//		
//	}
	
	@GetMapping("*.xls")                                                 
	public ModelAndView dutchXls(ModelAndView modelAndView, HttpServletRequest req) {
		List<DutchDao> list = dutchService.getDutchList();
		modelAndView.addObject(  "dutchList", list);
		modelAndView.setView(new ExcelViewConfig());
		return modelAndView;
		
		                                                                 
	}                                                                    
	
	@GetMapping("add")
	public String dutchAddView(SessionStatus ss) {
		return "dutch/dutchRegister";
	}
	
	@PostMapping
	public String dutchAdd(
//			@ModelAttribute("dutch")
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
