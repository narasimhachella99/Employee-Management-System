package com.employee.management.service;

import java.util.List;

import com.employee.management.model.Role;

public interface IRoleService {

    Role saveRole(Role role);
    
    Role getRole(Long id);

    Role updateRole(Role Role);

    void deleteRole(Long id);

    List<Role> getAllRoles();

    Role findRoleByName(String name);
}
