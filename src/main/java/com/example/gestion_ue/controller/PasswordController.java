package com.example.gestion_ue.controller;

import com.example.gestion_ue.dto.PasswordChangeDto;
import com.example.gestion_ue.model.User;
import com.example.gestion_ue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.validation.Valid;

@Controller
@RestController
@RequestMapping("/")
public class PasswordController {

    @Autowired
    private UserService userService;

    @PostMapping("change-password")
    public ResponseEntity<Void> changePassword(@ModelAttribute("passwordChangeDto") @Valid PasswordChangeDto passwordChangeDto, BindingResult bindingResult) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);
        userService.updatePassword(user, passwordChangeDto.getNewPassword());
        return ResponseEntity.noContent().build();
    }
}