package com.finance.data.repository;

import com.finance.data.model.FinanceRecord;
import com.finance.data.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RecordRepository extends JpaRepository<FinanceRecord, Long> {

    List<FinanceRecord> findByType(TransactionType type);

    List<FinanceRecord> findByCategory(String category);

    List<FinanceRecord> findByDateBetween(LocalDate start, LocalDate end);

    List<FinanceRecord> findByUserId(Long userId);
}