package com.practice.sharemate.user.service;

import com.practice.sharemate.user.dto.UserCreateDto;
import com.practice.sharemate.user.dto.UserDto;
import com.practice.sharemate.user.dto.UserUpdateDto;
import com.practice.sharemate.user.mapper.UserMapper;
import com.practice.sharemate.user.model.User;
import com.practice.sharemate.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserCreateDto createDto) {
        User user = userMapper.toEntity(createDto);
        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }

    @Override
    public UserDto updateUser(Long userId, UserUpdateDto updateDto) {
        User user = userMapper.toEntity(userId, updateDto);
        User updated = userRepository.save(user);
        return userMapper.toDto(updated);
    }

    @Override
    public UserDto getUserById(Long userId) {

        User user = userRepository.findById(userId);

        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());

    }
}
