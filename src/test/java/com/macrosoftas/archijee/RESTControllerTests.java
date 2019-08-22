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
import org.springframework.http.HttpStatus;
import static org.hamcrest.CoreMatchers.*;

/**
https://spring.io/guides/gs/testing-web/

In this test, the full Spring application context is started, but without the server.
 We can narrow down the tests to just the web layer by using @WebMvcTest  if only controller url test without
 autowired @Mockbean service:
 
 https://httpstatuses.com/     (Full HTTP Status)
 
 https://springbootdev.com/2018/02/22/spring-boot-test-writing-unit-tests-for-the-controller-layers-with-webmvctest/
**/
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
public class RESTControllerTests {

	//@Autowired
	//private MockMvc mockMvc;


	//https://www.baeldung.com/rest-assured-authentication
	
	//https://github.com/rest-assured/rest-assured/wiki/usage  +++
	
	/*
	@Test
	public void testRestUsers()  {
		
		//given().auth().preemptive().basic(username, password).when().get("{yourApiURL}").then().statusCode(200);
		
		//given().auth()
		//.basic("admin@test.com", "admin2017")
		//.when()
		get("http://localhost:8080/api/users");
		//.then()
		//.assertThat()
		//.statusCode(HttpStatus.OK.value());
		
	}*/
	
	/*
	@Test
	public void testRestLivres()  {
		
		//given()
		//.when()
		get("http://localhost:8080/api/users");
		//assertEquals
		//.then()
		//.assertThat()
		//.statusCode(HttpStatus.OK.value());
		
	}
   */

	/*
	 @Test
    public void shouldReturnisUnauthorized() throws Exception {
       // this.mockMvc.perform(get("/api/users")).andExpect(status().isOk());
		
		this.mockMvc.perform(get("/api/users")).andExpect(status().isUnauthorized());
    }
	*/
	
	/*
	@Test
    public void shouldReturnisOK() throws Exception {
		String username = "admin@test.com";
        String password = "admin2017";
		
        this.mockMvc.perform(get("/api/users").with(httpBasic(username, password))).andExpect(status().isOk());

    }
	*/
	

	

}