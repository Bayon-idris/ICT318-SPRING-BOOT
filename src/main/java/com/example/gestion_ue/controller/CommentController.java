package com.example.gestion_ue.controller;

import com.example.gestion_ue.dto.CommentDto;
import com.example.gestion_ue.dto.UserDto;
import com.example.gestion_ue.model.Comment;
import com.example.gestion_ue.model.Course;
import com.example.gestion_ue.model.User;
import com.example.gestion_ue.service.CommentService;
import com.example.gestion_ue.service.CourseService;
import com.example.gestion_ue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity<Comment> addComment(@RequestBody CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setCourseId(commentDto.getCourseId());
        comment.setComment(commentDto.getCommentText());
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);
        comment.setUserId(user.getId());
        Comment savedComment = commentService.saveComment(comment);
        return ResponseEntity.ok(savedComment);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<CommentDto>> getCommentsForCourse(@PathVariable Long courseId) {
        List<Comment> comments = commentService.getCommentsForCourse(courseId);
        List<CommentDto> commentDtos = new ArrayList<>();

        for (Comment comment : comments) {
            CommentDto commentDto = new CommentDto();
            commentDto.setCommentText(comment.getComment());
            commentDto.setCourseId(comment.getCourseId());
            commentDto.setCreatedAt(comment.getCreatedAt());

            // Fetch user details and set them in UserDto
            User user = userService.getUserById(comment.getUserId());
            if (user != null) {
                UserDto userDto = new UserDto();
                userDto.setId(user.getId());
                userDto.setLastName(user.getUsername());
                commentDto.setUser(userDto);
            }
            commentDtos.add(commentDto);
        }
        return ResponseEntity.ok(commentDtos);
    }

    @GetMapping("/course/{courseId}/comments")
    public String getCommentsPage(@PathVariable Long courseId, Model model) {
        Course course = courseService.findById(courseId);
        List<Comment> comments = commentService.getCommentsForCourse(courseId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("courseTitle", course.getTitle());
        model.addAttribute("comments", comments);
        return "dashboard/comments/index";
    }

}
