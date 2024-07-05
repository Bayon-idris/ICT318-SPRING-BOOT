package com.example.gestion_ue.repository;

import com.example.gestion_ue.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByUeId(Long ueId);


}
