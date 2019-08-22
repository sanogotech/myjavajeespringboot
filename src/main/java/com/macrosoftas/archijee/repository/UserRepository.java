package com.macrosoftas.archijee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.macrosoftas.archijee.model.User;
import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	 User findByEmail(String email);
	 
	 List<User> findByName(String name);
}
