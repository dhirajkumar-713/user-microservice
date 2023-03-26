package com.usermicroservice.usermicroservice.service;

import com.usermicroservice.usermicroservice.dto.ResponseDTO;
import com.usermicroservice.usermicroservice.entity.User;

public interface UserService {
    User saveUser(User user);
    ResponseDTO getUser(Long userId);
}
