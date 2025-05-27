package com.practice.sharemate.user.service;

import com.practice.sharemate.user.dto.UserCreateDto;
import com.practice.sharemate.user.dto.UserDto;
import com.practice.sharemate.user.dto.UserUpdateDto;
import com.practice.sharemate.user.mapper.UserMapper;
import com.practice.sharemate.user.model.User;
import com.practice.sharemate.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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

        User user = userRepository.findById(userId);
        if (user == null) throw new RuntimeException("Пользователь не найден");

        if (updateDto.getEmail() != null && user.getEmail() != null && !updateDto.getEmail().equals(user.getEmail())) {
            if (userRepository.findByEmail(updateDto.getEmail()) != null) {
                throw new RuntimeException("Пользователь с такой электронной почтой уже существует");
            }
        }

        User updated = userRepository.save(userMapper.toEntity(userId, updateDto));
        return userMapper.toDto(updated);
    }

    @Override
    public UserDto getUserById(Long userId) {

        if (userId == null) {
            throw new NullPointerException("Задан некорректный ID пользователя");
        }

        User user = userRepository.findById(userId);

        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public UserDto deleteUser(Long userId) {
        if (userId == null || userRepository.findById(userId) == null) {
            throw new NullPointerException("Задан некорректный ID пользователя");
        }
        return userMapper.toDto(userRepository.delete(userId));
    }
}
