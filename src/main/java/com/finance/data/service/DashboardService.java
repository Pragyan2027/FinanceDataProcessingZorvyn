package com.finance.data.service;

import com.finance.data.dto.DashboardDTO;
import com.finance.data.model.FinanceRecord;
import com.finance.data.model.TransactionType;
import com.finance.data.model.User;
import com.finance.data.repository.RecordRepository;
import com.finance.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {
    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private UserRepository userRepository;

    // 🔹 Summary
    public DashboardDTO getSummary(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<FinanceRecord> records = recordRepository.findByUserId(user.getId());

        double income = 0;
        double expense = 0;

        for (FinanceRecord r : records) {
            if (r.getType() == TransactionType.INCOME) {
                income += r.getAmount();
            } else {
                expense += r.getAmount();
            }
        }

        DashboardDTO dto = new DashboardDTO(income, expense);

        // 🔥 ADD THIS (missing earlier)
        if (user.getMonthlyBudget() != null) {

            if (expense > user.getMonthlyBudget()) {
                dto.setWarning("⚠ Budget exceeded!");
            } else {
                dto.setRemainingBudget(user.getMonthlyBudget() - expense);
            }
        }

        return dto;
    }

    // 🔹 Category-wise expense
    public Map<String, Double> getCategoryWiseExpenses() {
        List<FinanceRecord> records = recordRepository.findAll();

        return records.stream()
                .filter(r -> r.getType() == TransactionType.EXPENSE)
                .collect(Collectors.groupingBy(
                        FinanceRecord::getCategory,
                        Collectors.summingDouble(FinanceRecord::getAmount)
                ));
    }


    public Map<String, Double> getMonthlyExpenses() {
        List<FinanceRecord> records = recordRepository.findAll();

        return records.stream()
                .filter(r -> r.getType() == TransactionType.EXPENSE)
                .collect(Collectors.groupingBy(
                        r -> r.getDate().getMonth().toString(),
                        Collectors.summingDouble(FinanceRecord::getAmount)
                ));
    }

    public DashboardDTO getDashboard(Long userId) {

        // 🔹 Get user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔹 Get all records of user
        List<FinanceRecord> records = recordRepository.findByUserId(userId);

        double totalIncome = 0;
        double totalExpense = 0;

        // 🔹 Calculate totals
        for (FinanceRecord r : records) {
            if (r.getType() == TransactionType.INCOME) {
                totalIncome += r.getAmount();
            } else {
                totalExpense += r.getAmount();
            }
        }

        double balance = totalIncome - totalExpense;

        // 🔹 Prepare response
        DashboardDTO response = new DashboardDTO(totalIncome, totalExpense);

        // 🔥 Budget Logic
        if (user.getMonthlyBudget() != null) {

            if (totalExpense > user.getMonthlyBudget()) {
                response.setWarning("⚠ Budget exceeded!");
            } else {
                double remaining = user.getMonthlyBudget() - totalExpense;
                response.setRemainingBudget(remaining);
            }
        }

        return response;
    }
}


