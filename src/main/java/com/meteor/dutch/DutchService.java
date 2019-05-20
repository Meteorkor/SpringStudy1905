package com.meteor.dutch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DutchService {

	@Autowired
	private DutchRepository dutchRepo;
	
	public List<DutchDao> getDutchList(){
		return dutchRepo.findAll();
	}
	
	public DutchDao dutchSave(DutchDao entity){
		return dutchRepo.save(entity);
	}
	
	
}
