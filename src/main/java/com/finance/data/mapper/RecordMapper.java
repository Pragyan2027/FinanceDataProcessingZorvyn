package com.finance.data.mapper;

import com.finance.data.dto.RecordDTO;
import com.finance.data.model.FinanceRecord;
import com.finance.data.model.TransactionType;

public class RecordMapper {

    public static FinanceRecord toEntity(RecordDTO dto, Long userId) {
        FinanceRecord record = new FinanceRecord();

        record.setAmount(dto.getAmount());
        record.setType(TransactionType.valueOf(dto.getType().toUpperCase()));
        record.setCategory(dto.getCategory());
        record.setDate(dto.getDate());
        record.setDescription(dto.getDescription());
        record.setUserId(userId);

        return record;
    }
}
