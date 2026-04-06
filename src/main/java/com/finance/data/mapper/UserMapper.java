package com.finance.data.mapper;

import com.finance.data.dto.UserResponseDTO;
import com.finance.data.model.User;

import java.util.stream.Collectors;

public class UserMapper {
    public static UserResponseDTO toDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());

        dto.setRoles(
                user.getRoles()
                        .stream()
                        .map(role -> role.getName().name())
                        .collect(Collectors.toSet())
        );

        return dto;
    }
}
