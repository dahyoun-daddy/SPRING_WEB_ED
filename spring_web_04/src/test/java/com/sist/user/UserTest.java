package com.sist.user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration({
	 "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
	,"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@WebAppConfiguration
public class UserTest {
	private Logger log = LoggerFactory.getLogger(UserTest.class);
	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		log.debug("setUp================================");
		log.debug("ctx:"+ctx.toString());
		log.debug("setUp================================");
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		
		log.debug("mockMvc:"+mockMvc.toString());
		
	}
	
	@Test
	public void do_hello()throws Exception{
		mockMvc.perform(post("/test/hello.do").param("name", "james"))
		// 상태값은 OK가 나와야 함.
		.andExpect(status().isOk())
		// 처리 내용을 출력합니다.
		.andDo(print())
		
		.andExpect(model().attribute("greeting", "james"))
		// "greeting"이라는 attribute가 존재해야 합니다.
		.andExpect(model().attributeExists("greeting")
		);
	}

	@Test
	public void do_save()throws Exception{
		
		String id="SpMockMVC4";
		do_delete(id);
		//param
		MockHttpServletRequestBuilder createMessage = 
				post("/user/do_save.do").param("id", "SpMockMVC4")
				                  .param("u_level", "1")
				                  .param("login", "28")
				                  .param("recommend", "35")
				                  .param("mail", "jamesol@paran.com")
				                  .param("name", "이상무")
				                  .param("password", "888")
				                  .param("workDiv", "");
		
		mockMvc.perform(createMessage)
			.andDo(print())
			.andExpect(status().isOk()
			);

		
	}
	
	@Test
	public void do_update()throws Exception{
		String id="SpMockMVC4";
		//param
		MockHttpServletRequestBuilder messageUpdate = 
				post("/user/do_save.do").param("id", "SpMockMVC3")
				                  .param("u_level", "10")
				                  .param("login", "38")
				                  .param("recommend", "35")
				                  .param("mail", "jamesol@paran.com1")
				                  .param("name", "이상무1")
				                  .param("password", "8881")
				                  .param("workDiv", "update");
		
		mockMvc.perform(messageUpdate)
			.andDo(print())
			.andExpect(status().is2xxSuccessful()
			);

		//단건수정
		MockHttpServletRequestBuilder selectOne = 
				post("/user/userForm.do").param("id", "SpMockMVC3");	
		
		mockMvc.perform(selectOne)
		.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id", is("SpMockMVC3")))
        .andExpect(jsonPath("$.ulevel", is(10))) 
        .andExpect(jsonPath("$.login", is(38))) 	
        .andExpect(jsonPath("$.recommend", is(35)))
        .andExpect(jsonPath("$.mail", is("jamesol@paran.com1")))  
        .andExpect(jsonPath("$.name", is("이상무1")))          
        .andExpect(jsonPath("$.password", is("8881"))             		
        		);
		
		
	}
	
	@Test
	public void do_selectOne()throws Exception{
		MockHttpServletRequestBuilder createMessage = 
				post("/user/userForm.do").param("id", "SpMockMVC3");	
		
		mockMvc.perform(createMessage)
		.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id", is("SpMockMVC3")))
        .andExpect(jsonPath("$.ulevel", is(10))) 
        .andExpect(jsonPath("$.login", is(38))) 	
        .andExpect(jsonPath("$.recommend", is(35)))
        .andExpect(jsonPath("$.mail", is("jamesol@paran.com1")))  
        .andExpect(jsonPath("$.name", is("이상무1")))          
        .andExpect(jsonPath("$.password", is("8881"))             		
        		);
        
	}
	
	public void do_delete(String id)throws Exception{
		//삭제
		MockHttpServletRequestBuilder delMessage = 
		post("/user/do_delete.do").param("id", id);
	    ResultActions result = mockMvc.perform(delMessage);
	    result.andDo(print());
	    result.andExpect(status().isOk());
	    result.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	    //result.andExpect(jsonPath("$.flag", is(0)));
	}
}
