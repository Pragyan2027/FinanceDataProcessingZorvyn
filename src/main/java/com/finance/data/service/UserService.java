package com.finance.data.service;

import com.finance.data.dto.UserRequestDTO;
import com.finance.data.dto.UserResponseDTO;
import com.finance.data.mapper.UserMapper;
import com.finance.data.model.Role;
import com.finance.data.model.RoleType;
import com.finance.data.model.User;
import com.finance.data.repository.RoleRepository;
import com.finance.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(UserRequestDTO dto) {
        RoleType roleType = RoleType.valueOf(dto.getRole().toUpperCase());

        Role role = roleRepository.findByName(roleType)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        user.getRoles().add(role);

        return userRepository.save(user);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserMapper.toDTO(user);
    }
}