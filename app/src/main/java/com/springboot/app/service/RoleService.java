package com.springboot.app.service;

import java.util.List;

import com.springboot.app.model.Role;

public interface RoleService {
    Role findById(Long id);
    List<Role> findByName(String name);
}
