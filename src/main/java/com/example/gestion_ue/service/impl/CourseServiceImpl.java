package com.example.gestion_ue.service.impl;

import com.example.gestion_ue.model.Course;
import com.example.gestion_ue.repository.CourseRepository;
import com.example.gestion_ue.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findByUeId(Long ueId) {
        return courseRepository.findByUeId(ueId);
    }

    @Override
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }
}
