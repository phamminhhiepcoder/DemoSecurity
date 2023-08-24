package com.minhhiep.demosecurity1.service;

import com.minhhiep.demosecurity1.controller.request.AuthenticationRequest;
import com.minhhiep.demosecurity1.controller.request.RegisterRequest;
import com.minhhiep.demosecurity1.controller.response.AuthenticationResponse;
import com.minhhiep.demosecurity1.entity.User;
import com.minhhiep.demosecurity1.enums.Role;
import com.minhhiep.demosecurity1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER);
//        userRepository.save(user);
        var jwtToken = jwtService.generateToken((UserDetails) user);

            return AuthenticationResponse.builder()
                    .token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return null;
    }
}
