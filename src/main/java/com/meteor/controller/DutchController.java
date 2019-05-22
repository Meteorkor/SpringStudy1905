package com.meteor.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.meteor.DutchDaoExcelView;
import com.meteor.coffee.CoffeeEnum;
import com.meteor.dutch.DutchDao;
import com.meteor.dutch.DutchService;

@RequestMapping("dutch")
@Controller
//@SessionAttributes("dutch")
public class DutchController {
	private Logger logger = LoggerFactory.getLogger(DutchController.class);
	
	@Autowired
	private DutchService dutchService;
	
	@GetMapping
	public String dutchList(Model model, HttpServletRequest req) {
		model.addAttribute("dutchList", dutchService.getDutchList());
		return "dutch/dutchList";
	}
//	
//	@GetMapping("*.xls")                                                 
//	public ModelAndView dutchXls(ModelAndView modelAndView, HttpServletRequest req) {
//		List<DutchDao> list = dutchService.getDutchList();
//		modelAndView.addObject(  "dutchList", list);
//		modelAndView.setView(new DutchDaoExcelView());
//		return modelAndView;
//		
//		                                                                 
//	}                                                                    
//	
	@GetMapping("add")
	public String dutchAddView(Model model) {
		model.addAttribute("formMethod","post");
		return "dutch/dutchRegister";
	}
	@GetMapping("{id}")
	public String dutchView(@PathVariable("id") long id, Model model) {
		Optional<DutchDao> daoOp = dutchService.getDutch(id); 
		 
		if(!daoOp.isPresent()) {
			return "redirect:add";
		}else {
			return "redirect:" + id + "/edit";
		}
	}
	
	@GetMapping("{id}/edit")
	public String dutchEditView(@PathVariable("id") long id, Model model) {
		Optional<DutchDao> daoOp = dutchService.getDutch(id);
		if(daoOp.isPresent()) {
			DutchDao dao = daoOp.get();
			model.addAttribute("dutch", dao);
			model.addAttribute("formMethod","put");
			return "dutch/dutchRegister";
		}else {
			return "redirect:dutch";
		}
	}
	
	@PostMapping
	public String dutchAdd(
			DutchDao entity) {
		Date date = entity.getProduceTime();
		Calendar cal = Calendar.getInstance();
				cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, 45);
		entity.setExpiredTime(cal.getTime());
		dutchService.dutchSave(entity);
		
		return "redirect:dutch";
	}
	@PutMapping
	public String dutchPut(
			DutchDao entity) {
		dutchService.modifyDutch(entity);
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
