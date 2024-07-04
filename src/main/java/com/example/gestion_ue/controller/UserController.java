package com.example.gestion_ue.controller;

import com.example.gestion_ue.dto.UeDto;
import com.example.gestion_ue.dto.UserDto;
import com.example.gestion_ue.dto.UserUpdateDto;
import com.example.gestion_ue.model.Ue;
import com.example.gestion_ue.model.User;
import com.example.gestion_ue.service.UeService;
import com.example.gestion_ue.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final UeService ueService;


    public UserController(UserService userService, UeService ueService) {
        this.userService = userService;
        this.ueService = ueService;
    }

    @GetMapping("index")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model) {
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "cet email est deja associe a un compte !.");
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            result.rejectValue("confirmPassword", null, "Les mots de passe ne correspondent pas");
        }
        if (!isPasswordValid(user.getPassword())) {
            result.rejectValue("password", null, "Le mot de passe doit contenir au moins une lettre majuscule, une lettre minuscule, un chiffre, un caractère spécial, et avoir au moins 8 caractères");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/login";
    }


    @GetMapping("dashboard/index")
    public String listRegisteredUsers(Model model) {
        UeDto ueDto = new UeDto();
        model.addAttribute("ues", ueDto);
        List<Ue> listUes = ueService.getAllUes();
        model.addAttribute("listUes", listUes);
        return "dashboard/index";
    }

    public boolean isPasswordValid(String password) {
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return password.matches(passwordPattern);
    }

    @GetMapping("/profile")
    public String showProfilePage(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "dashboard/profile";
    }

    @DeleteMapping("/user/delete")
    public ResponseEntity<String> deleteUserAccount() {
        User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        userService.deleteUser(user.getId());
        return ResponseEntity.ok("User deleted successfully");
    }

    @PostMapping("/update-user")
    public ResponseEntity<String> updateUser(@ModelAttribute("userUpdateDto") @Valid UserUpdateDto userUpdateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Erreur de validation");
        }

        String email = userUpdateDto.getEmail();
        String username = userUpdateDto.getUsername();

        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(currentUserEmail);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Utilisateur non trouvé");
        }

        boolean updated = userService.updateEmailAndUsername(user, email, username);

        if (updated) {
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la mise à jour de l'utilisateur");
        }
    }




}
