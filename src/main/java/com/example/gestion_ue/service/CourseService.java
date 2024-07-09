package com.example.gestion_ue.service;

import com.example.gestion_ue.enums.CourseStatus;
import com.example.gestion_ue.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> getCoursesByUserId(Long userId);
    List<Course> findByUeId(Long ueId);
    void saveCourse(Course course);
    void deleteCourse(Long courseId);
    void updateCourseStatus(Long courseId, CourseStatus status);
}
