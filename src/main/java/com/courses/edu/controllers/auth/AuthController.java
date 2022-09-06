package com.courses.edu.controllers.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.courses.edu.controllers.auth.dto.LoginDto;
import com.courses.edu.controllers.auth.dto.RegisterDto;
import com.courses.edu.entities.Users;
import com.courses.edu.enums.Role;
import com.courses.edu.enums.Roles;
import com.courses.edu.helper.EncryptionDecryption;
import com.courses.edu.models.ResponseObject;
import com.courses.edu.providers.JWTProvider;
import com.courses.edu.repositories.UserRepository;
import com.courses.edu.services.UserService;

@RestController
@RequestMapping(value = "api/auth")
public class AuthController {

	@Autowired
	private JWTProvider jwtTokenUtil;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository UserRepo;

	@PostMapping("/login")
	@CrossOrigin
	ResponseEntity<ResponseObject> Login(@RequestBody LoginDto loginbody) {
		try {
			final UserDetails user = userService.loadUserByUsername(loginbody.getEmail());
			if (EncryptionDecryption.checkPassword(loginbody.getPassword(), user.getPassword())) {
				Users u = UserRepo.findByUsername(user.getUsername());
				final String token = jwtTokenUtil.generateToken(user, u.getRole());
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject("200", "Login successfuly!", token));
			}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject("401", "UnAuthorized!", ""));
		} catch (UsernameNotFoundException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ResponseObject("401", "UnAuthorized!", "username not found"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject("500", "Internal server error!", ""));
		}

	}

	@PostMapping("/register")
	@CrossOrigin
	ResponseEntity<ResponseObject> Register(@RequestBody RegisterDto registerdto) {
		try {
			Users user = UserRepo.findByUsername(registerdto.getEmail().trim());
			if (user != null) {
				return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
						.body(new ResponseObject("NotImplemented", "Email a readly exits", ""));
			}
			Users newuser = new Users(registerdto.getEmail(), registerdto.getFullname(),
					EncryptionDecryption.encryptPassword(registerdto.getPassword()), Role.USER);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "Register successfuly!", UserRepo.save(newuser)));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject("500", "Internal server error!", ""));
		}
	}

	@GetMapping("/user")
	@Roles({ Role.ADMIN })
	@CrossOrigin
	ResponseEntity<ResponseObject> User() {
		List<Users> user = UserRepo.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Query Successfuly!", user));

	}
}
