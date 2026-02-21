package com.example.demo.repository;

import com.example.demo.Entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends JpaRepository<Courses , Integer> {
    Courses findById(int courseId);
}
