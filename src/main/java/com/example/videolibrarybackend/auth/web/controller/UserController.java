package com.example.videolibrarybackend.auth.web.controller;

import com.example.videolibrarybackend.annotations.RestApiController;
import com.example.videolibrarybackend.auth.model.domain.UserTable;
import com.example.videolibrarybackend.auth.service.UserService;
import com.example.videolibrarybackend.auth.service.implementation.CustomUserDetailsService;
import com.example.videolibrarybackend.auth.utility.JwtUtil;
import com.example.videolibrarybackend.auth.web.dto.request.AuthRequestDto;
import com.example.videolibrarybackend.auth.web.dto.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestApiController
@RequestMapping(path = "api/user/")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping(path = "create")
    public UserTable saveUser(@RequestBody AuthRequestDto dto) {
        return userService.saveUser(dto);
    }

    @GetMapping(path = "get-by-email/{email}")
    public UserTable getByEmail(@PathVariable String email) {
        return userService.getByEmail(email);
    }


    @PostMapping(path = "token")
    public AuthResponse generateToken(@RequestBody AuthRequestDto authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    String.valueOf(authRequest.getEmail()), String.valueOf(authRequest.getPassword())
            ));
        } catch (BadCredentialsException e) {
            throw  new Exception("error");
        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authRequest.getEmail());

        final String token = jwtUtil.generateToken(String.valueOf(userDetails.getUsername()));

        return new AuthResponse(token);
    }
}
