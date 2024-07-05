package com.example.gestion_ue.service.impl;

import com.example.gestion_ue.dto.UeDto;
import com.example.gestion_ue.model.Ue;
import com.example.gestion_ue.model.User;
import com.example.gestion_ue.repository.UeRepository;
import com.example.gestion_ue.repository.UserRepository;
import com.example.gestion_ue.service.UeService;
import com.example.gestion_ue.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UeServiceImpl implements UeService {

    private final UeRepository ueRepository;

    private final UserRepository userRepository;

    @Autowired
    public UeServiceImpl(UeRepository ueRepository, UserService userService, UserRepository userRepository) {
        this.ueRepository = ueRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveUe(UeDto ueDto) {
        Ue ue = new Ue();
        BeanUtils.copyProperties(ueDto, ue);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = getUserByUsername(currentUsername);
        ue.setCreatedBy(user);
        ueRepository.save(ue);
    }


    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean checkIfCodeExists(String code) {
        return ueRepository.existsByCode(code);
    }

    @Override
    public List<Ue> getAllUes() {
        return ueRepository.findAll();
    }

    @Override
    public boolean deleteUe(Long id) {
        if (ueRepository.existsById(id)) {
            ueRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateUe(Long id, String code, String title, String description) {
        Optional<Ue> ueOptional = ueRepository.findById(id);
        if (ueOptional.isPresent()) {
            Ue ue = ueOptional.get();
            ue.setCode(code);
            ue.setTitle(title);
            ue.setDescription(description);
            ueRepository.save(ue);
            return true;
        }
        return false;
    }

    @Override
    public Ue findById(Long id) {
        return ueRepository.findById(id).orElse(null);
    }
}
