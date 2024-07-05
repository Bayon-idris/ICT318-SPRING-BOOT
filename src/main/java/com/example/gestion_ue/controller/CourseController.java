package com.example.gestion_ue.controller;

import com.example.gestion_ue.dto.CourseDto;
import com.example.gestion_ue.enums.CourseStatus;
import com.example.gestion_ue.model.Course;
import com.example.gestion_ue.model.Ue;
import com.example.gestion_ue.model.User;
import com.example.gestion_ue.repository.DocumentRepository;
import com.example.gestion_ue.service.CourseService;
import com.example.gestion_ue.service.DocumentService;
import com.example.gestion_ue.service.UeService;
import com.example.gestion_ue.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ues")
public class CourseController {

    private final CourseService courseService;
    private final UeService ueService;

    private final UserService userService;

    private final DocumentService documentService;


    public CourseController(CourseService courseService, UeService ueService, UserService userService, DocumentRepository documentRepository, DocumentService documentService) {
        this.courseService = courseService;
        this.ueService = ueService;
        this.userService = userService;
        this.documentService = documentService;
    }

    @GetMapping("/{ueId}/courses")
    public String getCoursesByUeId(@PathVariable Long ueId, Model model) {
        List<Course> courses = courseService.findByUeId(ueId);
        Ue ue = ueService.findById(ueId);

        List<Course> activeCourses = courses.stream()
                .filter(course -> course.getStatus() == CourseStatus.ACTIVE || course.getStatus() == CourseStatus.ONGOING)
                .toList();


        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(currentUserEmail);
        if (ue != null) {
            model.addAttribute("courses", activeCourses);
            model.addAttribute("ue", ue);
            model.addAttribute("courseDto", new CourseDto());
            model.addAttribute("user", user);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                String role = authority.getAuthority();
                model.addAttribute("userRole", role);
            }
            return "dashboard/courses/index";
        } else {
            return "redirect:/dashboard/index";
        }
    }


    @PostMapping("/{ueId}/courses/save")
    public String saveCourse(@PathVariable Long ueId, @Valid @ModelAttribute("courseDto") CourseDto courseDto,
                             @RequestParam("courseFiles") MultipartFile[] courseFiles, Model model) {

        Course course = new Course();
        course.setTitle(courseDto.getTitle());
        course.setDescription(courseDto.getDescription());
        course.setCourseType(courseDto.getCourseType());
        course.setDuration(courseDto.getDuration());
        course.setNumberOfParticipants(courseDto.getNumberOfParticipants());
        course.setPrerequisites(courseDto.getPrerequisites());
        course.setObjectives(courseDto.getObjectives());
        course.setStatus(CourseStatus.CREATED);
        course.setLanguage(courseDto.getLanguage());
        if (course.getCreatedAt() == null) {
            course.setCreatedAt(new Date());
        }
        Ue ue = ueService.findById(ueId);
        if (ue != null) {
            course.setUe(ue);
            courseService.saveCourse(course);
            if (course.getId() != null) {
                try {
                   if(courseFiles != null){
                       documentService.saveDocuments(courseFiles, course);
                   }
                } catch (IOException e) {
                    System.out.println("Failed to save course files. " + e.getMessage());
                }
            } else {
                System.out.println("Failed to save course, no ID generated.");
            }
        } else {
            System.out.println("UE with ID " + ueId + " not found.");
        }
        return "redirect:/ues/" + ueId + "/courses";
    }

}