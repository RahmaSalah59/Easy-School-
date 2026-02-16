package com.example.demo.repository;

import com.example.demo.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Integer> {
    Roles findByRoleName(String roleName);
}
