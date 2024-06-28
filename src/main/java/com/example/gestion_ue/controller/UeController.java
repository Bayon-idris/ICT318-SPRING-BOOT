package com.example.gestion_ue.controller;

import com.example.gestion_ue.dto.UeDto;
import com.example.gestion_ue.service.UeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dashboard/add-ues")
public class UeController {

    private final UeService ueService;

    @Autowired
    public UeController(UeService ueService) {
        this.ueService = ueService;
    }

    @PostMapping
    public String saveUe(@ModelAttribute("ueDto") UeDto ueDto, BindingResult result) {
        if (ueService.checkIfCodeExists(ueDto.getCode())) {
            result.rejectValue("code" ,null, "Cette Ue existe deja ");
        }
        ueService.saveUe(ueDto);
        return "redirect:/dashboard/index";
    }

}
