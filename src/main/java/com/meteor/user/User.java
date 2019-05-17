package com.meteor.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class User {
	
	
	private final String userId;
	private final String userName;
	private String email;
	private String pwd;

}
