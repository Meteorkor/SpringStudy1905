package com.meteor.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.introspect.MemberKey;

@Service
public class UserService {
	public UserService() {
		String[] names = {"kim", "lee","park"};
		for(String name : names) {
			userMap.put(name, new User(name,name));	
		}
	}
	private final Map<String, User> userMap = new ConcurrentHashMap<>();
	
	public List<User> getUserList(){
		return new ArrayList<>(userMap.values()); 
	}

}
