package com.finance.data.controller;

import com.finance.data.dto.BudgetDTO;
import com.finance.data.dto.UserRequestDTO;
import com.finance.data.dto.UserResponseDTO;
import com.finance.data.model.User;
import com.finance.data.repository.UserRepository;
import com.finance.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody UserRequestDTO dto) {
        return userService.createUser(dto);
    }
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    @Autowired
    private UserRepository userRepository;

    @PutMapping("/{id}/budget")
    public String setBudget(@PathVariable Long id, @RequestBody BudgetDTO dto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setMonthlyBudget(dto.getMonthlyBudget());

        userRepository.save(user);

        return "Budget updated";
    }
}
