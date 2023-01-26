package com.examserver.examserver;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// import com.examserver.examserver.model.User;
// import com.examserver.examserver.model.UserRole;
import com.examserver.examserver.service.UserService;
// import com.examserver.examserver.model.Role;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("starting code");
		// createUser();	
	}

	// private void createFirstUser() throws Exception {
	// 	User user = new User();

	// 	user.setFirstName("Anatolii");
	// 	user.setLastName("Kandiuk");
	// 	user.setUsername("KandAnat");
	// 	user.setEmail("kandanat23@gmail.com");
	// 	user.setProfile("default.png");
	// 	user.setPassword(this.passwordEncoder.encode("qwerty"));
	// 	// user.setPassword(this.bCryptPasswordEncoder.encode("qwerty"));
	// 	// user.setPassword("qwerty");
	// 	Role role1 = new Role();
	// 	role1.setRoleName("ADMIN");

	// 	Set<UserRole> userRoleSet = new HashSet<>();
	// 	UserRole userRole = new UserRole();

	// 	userRole.setRole(role1);
	// 	userRole.setUser(user);

	// 	userRoleSet.add(userRole);

	// 	User user1 = this.userService.createUser(user, userRoleSet);
	// }

}
