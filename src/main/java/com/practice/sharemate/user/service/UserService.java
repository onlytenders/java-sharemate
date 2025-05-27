package com.practice.sharemate.user.service;

import com.practice.sharemate.user.dto.UserCreateDto;
import com.practice.sharemate.user.dto.UserDto;
import com.practice.sharemate.user.dto.UserUpdateDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserCreateDto createDto);
    UserDto updateUser(Long userId, UserUpdateDto updateDto);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers();
    UserDto deleteUser(Long userId);
}
