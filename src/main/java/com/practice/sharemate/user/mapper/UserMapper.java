package com.practice.sharemate.user.mapper;

import com.practice.sharemate.user.dto.UserCreateDto;
import com.practice.sharemate.user.dto.UserDto;
import com.practice.sharemate.user.dto.UserUpdateDto;
import com.practice.sharemate.user.model.User;
import com.practice.sharemate.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final UserRepository userRepository;

    @Autowired
    public UserMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        return dto;
    }

    public User toEntity(@Valid UserCreateDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }

    public User toEntity(Long id, UserUpdateDto dto) {

        User user = new User();
        User existing = userRepository.findById(id);

        user.setId(id);
        user.setName(dto.getName()==null ? existing.getName() : dto.getName());
        user.setEmail(dto.getEmail()==null ? existing.getEmail() : dto.getEmail());
        return user;

    }

}
