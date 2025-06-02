package com.back.MoodBook.controllers;

import com.back.MoodBook.dtos.AdviceResponse;
import com.back.MoodBook.dtos.RecordCreateRequest;
import com.back.MoodBook.dtos.RecordResponse;
import com.back.MoodBook.entity.Advice;
import com.back.MoodBook.entity.Record;
import com.back.MoodBook.service.AdviceService;
import com.back.MoodBook.service.RecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; // Import ResponseEntity
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/records")
public class RecordController {
    private final RecordService recordService;
    private final AdviceService adviceService;

    @Autowired
    public RecordController(RecordService recordService, AdviceService adviceService) {
        this.recordService = recordService;
        this.adviceService = adviceService;
    }

    @PostMapping("/newRecord")
    public ResponseEntity<AdviceResponse> createRecord( // Use ResponseEntity
                                                        @RequestBody @Valid RecordCreateRequest request
    ) {
        Record record = recordService.createRecord(request);
        Advice advice = adviceService.getAdvice(record.getAdviceId());
        AdviceResponse adviceResponse = new AdviceResponse(
                advice.getId(),
                advice.getName(),
                advice.getContent(),
                advice.getMood());

        return ResponseEntity.ok(adviceResponse); // Return ResponseEntity
    }

    @GetMapping("/calendar")
    public List<RecordResponse> getCalendarData(
            @RequestParam("month") int month,
            @RequestParam("year") int year
    ){
        List<Record> records = recordService.getCalendarData(month, year);
        return records.stream().map(this::response).collect(Collectors.toList());
    }

    @GetMapping("/statistic")
    public String getStatistic() {
        double point = recordService.getStatistic();
        return "\"point\": " + point;
    }


    public RecordResponse response(Record record){
        return new RecordResponse(
                record.getId(),
                record.getCreatedAt(),
                record.getMood(),
                record.getExtraMood(),
                record.getReason()
        );
    }
}