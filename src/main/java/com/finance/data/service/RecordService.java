package com.finance.data.service;

import com.finance.data.dto.RecordDTO;
import com.finance.data.mapper.RecordMapper;
import com.finance.data.model.FinanceRecord;
import com.finance.data.model.TransactionType;
import com.finance.data.model.User;
import com.finance.data.repository.RecordRepository;
import com.finance.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RecordService {
    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private UserRepository userRepository;

    public FinanceRecord createRecord(RecordDTO dto, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        FinanceRecord record = RecordMapper.toEntity(dto, user.getId());
        return recordRepository.save(record);
    }

    public List<FinanceRecord> getRecords(String type, String category, LocalDate start, LocalDate end) {

        if (type != null) {
            return recordRepository.findByType(TransactionType.valueOf(type.toUpperCase()));
        }

        if (category != null) {
            return recordRepository.findByCategory(category);
        }

        if (start != null && end != null) {
            return recordRepository.findByDateBetween(start, end);
        }

        return recordRepository.findAll();
    }

}
