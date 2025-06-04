package com.employee.management.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.employee.management.model.Role;
import com.employee.management.repository.IRoleRepository;
import com.employee.management.service.IRoleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoleService implements IRoleService {

    private final IRoleRepository repo;

    @Override
    public Role saveRole(Role Role) {
        return repo.save(Role);
    }

    @Override
    public Role getRole(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Role updateRole(Role Role) {
        return repo.save(Role);
    }

    @Override
    public void deleteRole(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return repo.findAll();
    }

    @Override
    public Role findRoleByName(String name) {
        return repo.findByName(name);
    }
}
