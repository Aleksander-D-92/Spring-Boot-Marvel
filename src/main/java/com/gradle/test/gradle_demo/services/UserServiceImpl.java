package com.gradle.test.gradle_demo.services;

import com.gradle.test.gradle_demo.domain.Authority;
import com.gradle.test.gradle_demo.domain.User;
import com.gradle.test.gradle_demo.dto.user.binding.UserRegisterForm;
import com.gradle.test.gradle_demo.repositories.AuthorityRepository;
import com.gradle.test.gradle_demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username);
    }

    public void register(UserRegisterForm form) {
        User user = this.modelMapper.map(form, User.class);
        user.setPassword(this.passwordEncoder.encode(form.getPassword()));
        user.setRegistrationDate(LocalDateTime.now());
        user.setAccountNonLocked(true);
        Authority role_user = this.authorityRepository.getOne(1L);
        Authority role_admin = this.authorityRepository.getOne(2L);
        user.setAuthorities(new HashSet<>());
        user.getAuthorities().add(role_user);
        user.getAuthorities().add(role_admin);
        this.userRepository.save(user);
    }
}
