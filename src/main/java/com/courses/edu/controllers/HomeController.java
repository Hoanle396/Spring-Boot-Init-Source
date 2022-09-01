package com.courses.edu.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.courses.edu.models.ResponseObject;

@RestController
@RequestMapping(path = "/")
public class HomeController {

	@GetMapping("/")
	ResponseEntity<ResponseObject> getHome() {
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Query successfuly!", ""));
	}
}
