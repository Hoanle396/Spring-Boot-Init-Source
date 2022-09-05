package com.courses.edu.helper;

import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.courses.edu.enums.Role;
import com.courses.edu.enums.Roles;
import com.courses.edu.models.ResponseObject;
import com.courses.edu.providers.JWTProvider;

@Aspect
@Component
public class RolesAspect {
	@Autowired
	private JWTProvider jwtTokenProvider;

	@Around("@annotation(com.courses.edu.enums.Roles)")
	public Object doSomething(ProceedingJoinPoint jp) throws Throwable {

		Set<Role> roles = Arrays.stream(((MethodSignature) jp.getSignature()).getMethod()
	                .getAnnotation(Roles.class).value()).collect(Collectors.toSet());

		HttpServletRequest httpServletRequest = getRequest();

		String token = JWTTokenFilter.getToken(httpServletRequest);
		String userRole="GUEST";
		if (StringUtils.hasText(token) && jwtTokenProvider.validateJwtToken(token)) {
			userRole = jwtTokenProvider.getRoleFromToken(token);
			
			for (Role role : roles) {
				if (userRole.equals(role.toString())) {
					return jp.proceed();
				}
			}
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseObject("403", "Access Denied", "You do not have permission to access this URL"));
	}

	private HttpServletRequest getRequest() {

		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();

		return servletRequestAttributes.getRequest();
	}
}