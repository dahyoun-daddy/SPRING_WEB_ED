package com.sist.user;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"
   ,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})

@WebAppConfiguration
public class TDD_User {
	private Logger log= LoggerFactory.getLogger(TDD_User.class);
	
	@Autowired
	private WebApplicationContext ctx;
	
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
//		log.debug("==================");
//		log.debug("ctx: "+ctx.toString());
//		log.debug("==================");
		
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
//		log.info("==================");
//		log.info("mockMvc: "+mockMvc.toString());
//		log.info("==================");
		
	}
	
	@Test
	public void setSetUp() {
//		log.debug("==================");
//		log.debug("ctx: "+ctx.toString());
//		log.debug("==================");		
	}
	
	//@Test
	public void hello()throws Exception{
		mockMvc.perform( post("/test/hello.do")
				.param("name","james"))
				//상태값은 OK가 나와야 함.
				.andExpect(status().isOk())
		        //처리내용 출력
				.andDo(print())
				.andExpect(model().attribute("greeting", "james")
				);
	}
	
	//@Test
	public void do_save()throws Exception{
		//Param
		MockHttpServletRequestBuilder createMessage =
				post("/user/do_save.do")
				.param("id", "SpMockMvc8")
				.param("u_level", "1")
				.param("login", "18")
				.param("recommend", "35")
				.param("mail", "jamesol@naver.com")
				.param("name", "이상무")
				.param("password", "81")
				.param("workDiv", "")
				;
		
		mockMvc.perform(createMessage)
		.andDo(print())
		.andExpect(status().is3xxRedirection()
		);
				
	}
	
	@Test
	public void doSelectOne()throws Exception{
		MockHttpServletRequestBuilder createMessage =
				post("/user/userForm.do")
				.param("id", "SpMockMvc8");
		
		mockMvc.perform(createMessage)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id", is("SpMockMvc8")))
		.andExpect(jsonPath("$.login", is(18))		
				
		);
		
	}
	
	
	
	
	
	
}
