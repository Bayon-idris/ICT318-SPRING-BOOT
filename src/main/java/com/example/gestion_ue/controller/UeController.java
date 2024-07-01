package com.example.gestion_ue.controller;

import com.example.gestion_ue.dto.UeDto;
import com.example.gestion_ue.service.UeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("dashboard")
public class UeController {

    private final UeService ueService;

    @Autowired
    public UeController(UeService ueService) {
        this.ueService = ueService;
    }

    @PostMapping("add-ues")
    public String saveUe(@Valid @ModelAttribute("ueDto") UeDto ueDto, BindingResult result, Model model) {
        ueDto.setCode(ueDto.getCode().replaceAll("\\s+", "").toLowerCase());

        if (ueService.checkIfCodeExists(ueDto.getCode())) {
            result.rejectValue("code", null, "Cette UE existe déjà.");
        }

        if (result.hasErrors()) {
            model.addAttribute("ues", ueDto);
            return "redirect:/dashboard/index";
        }

        ueService.saveUe(ueDto);
        return "redirect:/dashboard/index";
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
            return ResponseEntity.noContent().build(); // Retourne un statut 204 No Content si réussi
        } else {
            return ResponseEntity.notFound().build(); // Retourne un statut 404 Not Found si l'UE n'est pas trouvée
        }
    }
}

