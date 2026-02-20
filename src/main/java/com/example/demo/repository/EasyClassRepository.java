package com.example.demo.repository;

import com.example.demo.Entity.EazyClass;
import com.example.demo.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EasyClassRepository extends JpaRepository<EazyClass,Integer> {
    EazyClass findById(int ID);
}
