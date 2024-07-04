package com.example.gestion_ue.service;
import com.example.gestion_ue.model.Comment;
import java.util.List;

public interface CommentService {
    List<Comment> getCommentsForCourse(Long courseId);
    Comment saveComment(Comment comment);
}
