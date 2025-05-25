package com.back.MoodBook.service;

import com.back.MoodBook.dtos.RecordCreateRequest;
import com.back.MoodBook.entity.Advice;
import com.back.MoodBook.entity.Record;
import com.back.MoodBook.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.summingDouble;

@Service
public class RecordService {
    public RecordRepository recordRepository;
    public AdviceService adviceService;

    @Autowired
    public RecordService(RecordRepository recordRepository, AdviceService adviceService){
        this.recordRepository = recordRepository;
        this.adviceService = adviceService;
    }

    public Record createRecord(RecordCreateRequest request){
        Advice advice = adviceService.getRandomAdvice(request.getMood(), request.getExtraMood());
        Record createdRecord = new Record();
        createdRecord.setMood(request.getMood());
        createdRecord.setExtraMood(request.getExtraMood());
        createdRecord.setReason(request.getReason());
        createdRecord.setAdvice(advice);
        return recordRepository.save(createdRecord);
    }

    public List<Record> getCalendarData(int month, int year){
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDateTime startOfMonth = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = yearMonth.atEndOfMonth().atTime(23, 59, 59);

        return recordRepository.findByMonth(startOfMonth, endOfMonth);
    }

    public List<Record> getLast30Days (){
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime lastDay = today.minusDays(30);
        return recordRepository.findLast30DaysRecords(today, lastDay);
    }

    public double getStatistic(){
        List<Record> records = getLast30Days();
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
