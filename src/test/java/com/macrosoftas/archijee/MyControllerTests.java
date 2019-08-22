package com.macrosoftas.archijee;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import com.macrosoftas.archijee.service.UserServiceImpl;
import com.macrosoftas.archijee.controller.LoginController;
import com.macrosoftas.archijee.model.User;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
/**
https://spring.io/guides/gs/testing-web/

In this test, the full Spring application context is started, but without the server.
 We can narrow down the tests to just the web layer by using @WebMvcTest  if only controller url test without
 autowired @Mockbean service:
**/
public class MyControllerTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserServiceImpl userService;
	
	/**
	
	 @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")));
    }
	
	**/

	@Test
	public void testExample()  {
		
		given(this.userService.findUserByEmail("admin@test.com"))
				.willReturn(new User());
				
	/*			
		this.mvc.perform(get("/").accept(MediaType.TEXT_PLAIN))
				.andExpect(status().isOk()).andExpect(content().string("Honda Civic"));
				
	*/
	}

}