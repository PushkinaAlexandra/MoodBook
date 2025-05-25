package com.back.MoodBook.controllers;

import com.back.MoodBook.dtos.AdviceCreateRequest;
import com.back.MoodBook.dtos.AdviceResponse;
import com.back.MoodBook.entity.Advice;
import com.back.MoodBook.service.AdviceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/advice")
public class AdviceController {
    private final AdviceService adviceService;

    @Autowired
    public AdviceController(AdviceService adviceService) {
        this.adviceService = adviceService;
    }

    @PostMapping("/createAdvice")
    public AdviceResponse createAdvice(
            @RequestBody @Valid AdviceCreateRequest request
    ) {
        Advice advice = adviceService.createAdvice(request);
        return response(advice);
    }

    @GetMapping
    public List<AdviceResponse> createBook (
            @RequestParam("mood") String mood
    ) {
        List<Advice> book = adviceService.createBook(mood);
        return book.stream().map(this::response).collect(Collectors.toList());
    }

    @GetMapping
    public List<AdviceResponse> createBook () {
        List<Advice> book = adviceService.createBookAllAdvice();
        return book.stream().map(this::response).collect(Collectors.toList());
    }

    public AdviceResponse response(Advice advice){
        return new AdviceResponse(
                advice.getId(),
                advice.getName(),
                advice.getContent(),
                advice.getMood());
    }
}
