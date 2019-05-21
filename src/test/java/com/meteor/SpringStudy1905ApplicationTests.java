package com.meteor;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteor.coffee.CoffeeEnum;
import com.meteor.dutch.DutchDao;
import com.meteor.dutch.DutchService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpringStudy1905ApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	MessageSource msgSource;
	
	@Autowired
	DutchService service;
	
	
	@Test
	public void contextLoads() throws Exception {
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect( content().string(containsString("hello") ) )
		;
	}
	
	@Test
	public void redirectTest() throws Exception {
		
		mockMvc.perform(post("/dutch")
				.param("coffeeKind", "Indonesia_Mandheling")
				.param("desc", "asdasd")
				)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("dutch"))
		;
		
		mockMvc.perform(get("/dutch")
				)
		.andDo(print())
		.andExpect(status().is2xxSuccessful())
//		.andExpect(content().string(containsString(msgSource.getMessage("Indonesia_Mandheling" + ".name", null, null))))
		.andExpect(content().string(containsString("Indonesia_Mandheling")))
		;
		
		
	}

}
