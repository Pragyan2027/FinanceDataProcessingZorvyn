package com.finance.data.dto;

public class DashboardDTO {
    private Double totalIncome;
    private Double totalExpense;
    private Double netBalance;
    private String warning;
    private Double remainingBudget;

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public Double getRemainingBudget() {
        return remainingBudget;
    }

    public void setRemainingBudget(Double remainingBudget) {
        this.remainingBudget = remainingBudget;
    }

    public DashboardDTO(Double totalIncome, Double totalExpense) {
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.netBalance = totalIncome - totalExpense;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public Double getNetBalance() {
        return netBalance;
    }

    public void setNetBalance(Double netBalance) {
        this.netBalance = netBalance;
    }
}
