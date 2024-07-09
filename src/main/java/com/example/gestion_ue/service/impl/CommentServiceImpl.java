package com.example.gestion_ue.service.impl;

import com.example.gestion_ue.model.Comment;
import com.example.gestion_ue.model.Course;
import com.example.gestion_ue.repository.CommentRepository;
import com.example.gestion_ue.repository.CourseRepository;
import com.example.gestion_ue.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentsForCourse(Long courseId) {
        return commentRepository.findByCourseId(courseId);
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

}
