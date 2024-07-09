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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UeServiceImpl implements UeService {

    private final UeRepository ueRepository;

    private final UserRepository userRepository;

    private final UserService userService;

    @Autowired
    public UeServiceImpl(UeRepository ueRepository, UserService userService, UserRepository userRepository, UserService userService1) {
        this.ueRepository = ueRepository;
        this.userRepository = userRepository;
        this.userService = userService1;
    }

    @Override
    public void saveUe(UeDto ueDto) {
        Ue ue = new Ue();
        BeanUtils.copyProperties(ueDto, ue);
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(currentUserEmail);
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
    public Page<Ue> getAllUes(Pageable pageable) {
        return ueRepository.findAll(pageable);
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

    @Override
    public List<Ue> findByCreatedById(Long userId) {
        return ueRepository.findByCreatedById(userId);
    }
}
