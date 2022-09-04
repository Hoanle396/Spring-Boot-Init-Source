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
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.courses.edu.enums.Role;
import com.courses.edu.enums.Roles;

@Aspect
@Component
public class RolesAspect {
    @Around("@annotation(com.courses.edu.enums.Roles)")
    public Object doSomething(ProceedingJoinPoint jp) throws Throwable {

        Set<Role> roles = Arrays.stream(((MethodSignature) jp.getSignature()).getMethod()
                .getAnnotation(Roles.class).value()).collect(Collectors.toSet());

        HttpServletRequest httpServletRequest = getRequest();

        for(Role role : roles){
            if(httpServletRequest.isUserInRole(role.toString())){
                return jp.proceed();
            }
        }

        throw new AccessDeniedException("Access Denied");
    }

    private HttpServletRequest getRequest() {

        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        return servletRequestAttributes.getRequest();
    }
}