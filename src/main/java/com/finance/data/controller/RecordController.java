package com.finance.data.controller;

import com.finance.data.dto.RecordDTO;
import com.finance.data.model.FinanceRecord;
import com.finance.data.repository.RecordRepository;
import com.finance.data.service.RecordService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/records")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @Autowired
    private RecordRepository recordRepository;

    @PostMapping
    public FinanceRecord createRecord(@RequestBody RecordDTO dto, Authentication authentication) {

        String email = authentication.getName();
        return recordService.createRecord(dto, email);
    }

    @GetMapping
    public List<FinanceRecord> getRecords(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate
    ) {
        return recordService.getRecords(type, category, startDate, endDate);
    }

    @GetMapping("/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=records.csv");

        List<FinanceRecord> records = recordRepository.findAll();

        PrintWriter writer = response.getWriter();

        writer.println("ID,Type,Amount,Category,Date");

        for (FinanceRecord r : records) {
            writer.println(r.getId() + "," +
                    r.getType() + "," +
                    r.getAmount() + "," +
                    r.getCategory() + "," +
                    r.getDate());
        }

        writer.flush();
    }
}
