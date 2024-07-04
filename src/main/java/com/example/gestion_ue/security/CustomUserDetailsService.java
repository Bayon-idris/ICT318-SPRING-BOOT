package com.example.gestion_ue.security;

import com.example.gestion_ue.model.Role;
import com.example.gestion_ue.model.User;
import com.example.gestion_ue.repository.UserRepository;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Email ou mot de passe invalide");
        }

        if (user.isDeleted()) {
            throw new UsernameNotFoundException("Votre compte a été supprimé.");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), //if you want to use username fo principal.getName() change this setter and vice-versa
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles())
        );
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Collection<? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }
}