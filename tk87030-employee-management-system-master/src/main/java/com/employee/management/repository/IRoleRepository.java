package com.employee.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.management.model.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role , Long>{

    Role findByName(String name);
}
