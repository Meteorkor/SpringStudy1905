package com.meteor.dutch;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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

	public Optional<DutchDao> getDutch(long id) {
		return dutchRepo.findById(id);
	}
	
	public void modifyDutch(DutchDao modifyDao) {
		DutchDao dao = getDutch(modifyDao.getId()).get();
		dao.setCoffeeKind(modifyDao.getCoffeeKind());
		dao.setDesc(modifyDao.getDesc());
	}
	
	
}
