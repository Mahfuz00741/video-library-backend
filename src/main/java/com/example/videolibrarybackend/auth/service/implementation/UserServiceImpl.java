package com.example.videolibrarybackend.auth.service.implementation;

import com.example.videolibrarybackend.auth.model.domain.User;
import com.example.videolibrarybackend.auth.model.repository.UserRepository;
import com.example.videolibrarybackend.auth.service.UserService;
import com.example.videolibrarybackend.auth.web.dto.request.UserRequestDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(UserRequestDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        userRepository.save(user);
        return user;
    }

    @Override
    public String loginUser(UserRequestDto dto) {
        return null;
    }

    @Override
    public void logoutUser(Long userId) {
    }

}
