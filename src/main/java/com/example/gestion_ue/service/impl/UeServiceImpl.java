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

@Service
public class UeServiceImpl implements UeService {

    private final UeRepository ueRepository;
    private final UserService userService;

    private final UserRepository userRepository;

    @Autowired
    public UeServiceImpl(UeRepository ueRepository, UserService userService, UserRepository userRepository) {
        this.ueRepository = ueRepository;
        this.userService = userService;
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
}
