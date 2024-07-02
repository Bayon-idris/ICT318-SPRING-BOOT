package com.example.gestion_ue.controller;

import com.example.gestion_ue.dto.CourseDto;
import com.example.gestion_ue.model.Course;
import com.example.gestion_ue.model.Ue;
import com.example.gestion_ue.service.CourseService;
import com.example.gestion_ue.service.UeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/ues")
public class CourseController {

    private final CourseService courseService;
    private final UeService ueService;

    public CourseController(CourseService courseService, UeService ueService) {
        this.courseService = courseService;
        this.ueService = ueService;
    }

    @GetMapping("/{ueId}/courses")
    public String getCoursesByUeId(@PathVariable Long ueId, Model model) {
        List<Course> courses = courseService.findByUeId(ueId);
        Ue ue = ueService.findById(ueId);
        CourseDto courseDto = new CourseDto();
        if (ue != null) {
            model.addAttribute("courses", courses);
            model.addAttribute("ue", ue);
            model.addAttribute("courseDto", courseDto);
            return "dashboard/courses/index";
        } else {
            return "redirect:/dashboard/index";
        }
    }

    @PostMapping("/{ueId}/courses/save")
    public String saveCourse(@PathVariable Long ueId, @Valid @ModelAttribute("courseDto") CourseDto courseDto, Model model) {
        Course course = new Course();
        course.setTitle(courseDto.getTitle());
        course.setDescription(courseDto.getDescription());
        Ue ue = ueService.findById(ueId);
        if (ue != null) {
            course.setUe(ue);
            courseService.saveCourse(course);
        }
        return "redirect:/ues/" + ueId + "/courses";
    }
}