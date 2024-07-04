package com.example.gestion_ue.service;


import com.example.gestion_ue.dto.UserDto;
import com.example.gestion_ue.model.User;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    void deleteUser(Long userId);

    boolean updateEmailAndUsername(User user, String newEmail, String newUsername);

    void updatePassword(User user, String newPassword);


}
