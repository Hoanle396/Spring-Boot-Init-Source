package com.courses.edu.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.courses.edu.entities.Users;
import com.courses.edu.enums.Role;
import com.courses.edu.helper.EncryptionDecryption;
import com.courses.edu.repositories.UserRepository;

@EnableAutoConfiguration
@Configuration
public class InitialDatabase {
	@Bean
	CommandLineRunner initDatabase(UserRepository users) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				Users user = new Users("hoanle396@gmail.com", "le huu hoan", "123456", Role.USER);
				Users admin = new Users("lhhoan.20@gmail.com", "le huu hoan", "123456", Role.ADMIN);
				user.setPassword(EncryptionDecryption.encryptPassword(user.getPassword()));
				admin.setPassword(EncryptionDecryption.encryptPassword(admin.getPassword()));
				System.out.println("insert: " + users.save(user));
				System.out.println("insert: " + users.save(admin));
			}
		};
	}
}
