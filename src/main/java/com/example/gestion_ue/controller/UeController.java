package com.example.gestion_ue.controller;

import com.example.gestion_ue.dto.UeDto;
import com.example.gestion_ue.model.Ue;
import com.example.gestion_ue.service.UeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("dashboard")
@Validated
public class UeController {

    private final UeService ueService;

    @Autowired
    public UeController(UeService ueService) {
        this.ueService = ueService;
    }

    @PostMapping("/add-ues")
    public RedirectView saveUe(@Valid @ModelAttribute("ueDto") UeDto ueDto, HttpServletRequest request, Model model) {
        ueDto.setCode(ueDto.getCode().replaceAll("\\s+", "").toLowerCase());
        ueService.saveUe(ueDto);
        return new RedirectView("/dashboard/index");
    }

    @GetMapping("/check-ue-code/{code}")
    public ResponseEntity<Map<String, Boolean>> checkUeCodeExists(@PathVariable String code) {
        boolean exists = ueService.checkIfCodeExists(code);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("delete-ue/{id}")
    public ResponseEntity<Void> deleteUe(@PathVariable Long id) {
        if (ueService.deleteUe(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update-ue/{id}")
    public ResponseEntity<Void> updateUe(@PathVariable Long id, @RequestBody UeDto ueDto) {
        boolean updated = ueService.updateUe(id, ueDto.getCode(), ueDto.getTitle(), ueDto.getDescription());
        if (updated) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

