package com.back.MoodBook.service;

import com.back.MoodBook.dtos.RecordCreateRequest;
import com.back.MoodBook.entity.Advice;
import com.back.MoodBook.entity.Record;
import com.back.MoodBook.entity.User;
import com.back.MoodBook.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecordService {
    private final RecordRepository recordRepository;
    private final AdviceService adviceService;
    private final UserService userService;

    public RecordService(RecordRepository recordRepository, AdviceService adviceService,
                         UserService userService) {
        this.recordRepository = recordRepository;
        this.adviceService = adviceService;
        this.userService = userService;
    }

    public Record createRecord(RecordCreateRequest request) {
        User user = userService.findByUsername(request.getUsername());
        Advice advice = adviceService.getRandomAdvice(request.getMood(), request.getExtraMood());
        Record createdRecord = new Record();
        createdRecord.setMood(request.getMood());
        createdRecord.setExtraMood(request.getExtraMood());
        createdRecord.setReason(request.getReason());
        createdRecord.setAdviceId(advice.getId());
        createdRecord.setUserId(user.getId());
        return recordRepository.save(createdRecord);
    }

    public List<Record> getRecordsByUserId(Long userId) {
        return recordRepository.findByUserId(userId);
    }

    public List<Record> getCalendarData(int month, int year, String username) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDateTime startOfMonth = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = yearMonth.atEndOfMonth().atTime(23, 59, 59);
        Long userId = userService.findByUsername(username).getId();
        return recordRepository.findByMonth(startOfMonth, endOfMonth, userId);
    }

    public List<Record> getLast30Days(Long userId) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime lastDay = today.minusDays(30);
        return recordRepository.findLast30DaysRecords(today, lastDay, userId);
    }

    public double getStatistic(String username) {
        Long userId = userService.findByUsername(username).getId();
        List<Record> records = getLast30Days(userId);
        Map<String, Double> names = new HashMap<>();
        names.put("worst", -1.0);
        names.put("bad", -0.5);
        names.put("norm", 0.0);
        names.put("good", 0.5);
        names.put("excellent", 1.0);
        List<Double> points = records.stream().map(item -> names.get(item.getMood().toLowerCase())).toList();
        return points.stream().mapToDouble(Double::doubleValue).sum();
    }


}
