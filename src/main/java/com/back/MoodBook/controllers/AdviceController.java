package com.back.MoodBook.controllers;

import com.back.MoodBook.dtos.AdviceCreateRequest;
import com.back.MoodBook.dtos.AdviceResponse;
import com.back.MoodBook.entity.Advice;
import com.back.MoodBook.service.AdviceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<AdviceResponse>> getAdvice(
            @RequestParam("mood") String mood
    ) {
        System.out.println("Received mood: " + mood);

        try {
            List<Advice> book;
            if (mood.equals("default")) {
                System.out.println("Calling createBookAllAdvice...");
                book = adviceService.createBookAllAdvice();
                System.out.println("createBookAllAdvice() returned " + book.size() + " items.");
            } else {
                System.out.println("Calling createBook(" + mood + ")...");
                book = adviceService.createBook(mood);
                System.out.println("createBook(" + mood + ") returned " + book.size() + " items.");
            }

            List<AdviceResponse> adviceResponses = book.stream()
                    .map(this::response)
                    .collect(Collectors.toList());

            System.out.println("Returning " + adviceResponses.size() + " advice responses.");

            return ResponseEntity.ok()
                    .header("Content-Type", "application/json")
                    .body(adviceResponses);

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(null); // 500 Internal Server Error
        }
    }

    private AdviceResponse response(Advice advice) {
        return new AdviceResponse(
                advice.getId(),
                advice.getName(),
                advice.getContent(),
                advice.getMood());
    }
}