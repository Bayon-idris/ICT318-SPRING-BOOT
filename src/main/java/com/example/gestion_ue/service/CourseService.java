package com.example.gestion_ue.service;

import com.example.gestion_ue.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> findByUeId(Long ueId);

    void saveCourse(Course course);


}
