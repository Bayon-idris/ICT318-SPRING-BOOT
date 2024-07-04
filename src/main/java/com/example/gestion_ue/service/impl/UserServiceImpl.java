package com.example.gestion_ue.service.impl;

import com.example.gestion_ue.dto.UserDto;
import com.example.gestion_ue.model.User;
import com.example.gestion_ue.model.UserRole;
import com.example.gestion_ue.repository.RoleRepository;
import com.example.gestion_ue.repository.UserRepository;
import com.example.gestion_ue.repository.UserRoleRepository;
import com.example.gestion_ue.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    private UserRoleRepository userRoleRepository;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        User userResult = findByEmail(userDto.getEmail());
        userRoleRepository.save(new UserRole(userResult.getId(), userDto.getRole()));

    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public void deleteUser(Long userId) {
        userRepository.markUserAsDeleted(userId);
    }


    private UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();
        String[] name = user.getUsername().split(" ");
        userDto.setFirstName(name[0]);
        userDto.setLastName(name[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }


    @Override
    public boolean updateEmailAndUsername(User user, String newEmail , String newUsername) {
        user.setUsername(newUsername);
        user.setEmail(newEmail);
        userRepository.save(user);
        return false;
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

}
