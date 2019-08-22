package com.macrosoftas.archijee;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.macrosoftas.archijee.model.Role;
import com.macrosoftas.archijee.model.User;
import com.macrosoftas.archijee.repository.UserRepository;
import com.macrosoftas.archijee.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class ArchiJEEApplication implements CommandLineRunner{
	
	private static final Logger logger = LoggerFactory.getLogger(ArchiJEEApplication.class);
	
	@Autowired
	private UserRepository userRepository;
	
	/*@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;*/

	public static void main(String[] args) {
		SpringApplication.run(ArchiJEEApplication.class, args);
		logger.info("Start Archi Application ...");
	}

	public void run(String... args) throws Exception {
		
		List<String> userdata = Arrays.asList("ADMIN", "ADMIN", "admin@test.com","admin2017");
        logger.debug("**** Create default user  {}", userdata);
		
		User user = new User();
		user.setName("ADMIN");
		user.setLastName("ADMIN");
		user.setEmail("admin@test.com");
		//BCryptPasswordEncoder bCryptPasswordEncoderLocal = new BCryptPasswordEncoder();
		//String encodepwd = bCryptPasswordEncoderLocal.encode("admin2017");
		//user.setPassword(encodepwd);
		//System.out.println("admin2017  encoder = " +encodepwd);
		user.setPassword("$2a$10$fE7BKQcc.tesDzaptjL8luXZB6MV5rvUJ13ub5aVYKqnoPmMqYd8m");
		user.setActive(true);
		//Role
		HashSet<Role> roles = new HashSet<Role>();
		Role role = new Role();
		role.setRole("ADMIN");
		roles.add(role);
		user.setRoles(roles);
		userRepository.save(user);
	}
}
