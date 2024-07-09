package com.example.gestion_ue.repository;

import com.example.gestion_ue.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByUeId(Long ueId);
    @Query("SELECT c FROM Course c WHERE c.ue.createdBy.id = :userId")
    List<Course> findCoursesByUserId(Long userId);
}
