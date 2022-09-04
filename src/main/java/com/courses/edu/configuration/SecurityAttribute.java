package com.courses.edu.configuration;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.ConfigAttribute;

import com.courses.edu.enums.Role;

public class SecurityAttribute implements ConfigAttribute {
    private final List<Role> roles;

    public SecurityAttribute(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String getAttribute() {
        return roles.stream().map(p -> p.name()).collect(Collectors.joining(","));
    }
}
