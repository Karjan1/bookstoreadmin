package com.karoljanowski;

import com.karoljanowski.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookstoreadminApplication implements CommandLineRunner{

	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(BookstoreadminApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		User user1 = new User();
//		user1.setFirstName("John");
//		user1.setLastName("Adams");
//		user1.setUsername("w");
//		user1.setPassword(SecurityUtility.passwordEncoder().encode("q"));
//		user1.setEmail("karol.janowski22@gmail.com");
//		Set<UserRole> userRoles = new HashSet<>();
//		Role role1 = new Role();
//		role1.setRoleId(2);
//		role1.setName("ROLE_ADMIN");
//		userRoles.add(new UserRole(user1, role1));
//
//		userService.createUser(user1, userRoles);

	}
}










