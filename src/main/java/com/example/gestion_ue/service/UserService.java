package com.example.gestion_ue.service;


import com.example.gestion_ue.dto.UserDto;
import com.example.gestion_ue.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();



}
