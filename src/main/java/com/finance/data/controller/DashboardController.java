package com.finance.data.controller;

import com.finance.data.dto.DashboardDTO;
import com.finance.data.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/summary")
    public DashboardDTO getSummary(Authentication authentication) {
        return dashboardService.getSummary(authentication.getName());
    }

    @GetMapping("/category-wise")
    public Map<String, Double> getCategoryWise() {
        return dashboardService.getCategoryWiseExpenses();
    }

    @GetMapping("/monthly")
    public Map<String, Double> getMonthly() {
        return dashboardService.getMonthlyExpenses();
    }
}
