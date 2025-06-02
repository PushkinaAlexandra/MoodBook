package com.back.MoodBook.service;

import com.back.MoodBook.dtos.AdviceCreateRequest;
import com.back.MoodBook.entity.Advice;
import com.back.MoodBook.repository.AdviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AdviceService {

    private final AdviceRepository adviceRepository;

    @Autowired
    public AdviceService(AdviceRepository adviceRepository) {
        this.adviceRepository = adviceRepository;
    }

    public List<Advice> createBook(String keyMood) {
        System.out.println("Вызван createBook с mood: " + keyMood); // Log
        List<Advice> advices = adviceRepository.findByMood(keyMood);
        System.out.println("Найдено " + advices.size() + " советов."); // Log
        return advices;
    }

    public List<Advice> createBookAllAdvice() {
        System.out.println("Вызван createBookAllAdvice"); // Log
        List<Advice> advices = adviceRepository.findAll();
        System.out.println("Найдено " + advices.size() + " советов."); // Log
        return advices;
    }

    public Advice createAdvice(AdviceCreateRequest adviceCreateRequest) {
        Advice advice = new Advice();
        advice.setName(adviceCreateRequest.getName());
        advice.setContent(adviceCreateRequest.getContent());
        advice.setMood(adviceCreateRequest.getMood());
        adviceRepository.save(advice);
        return advice;
    }

    public Advice getAdvice(Long id) {
        return adviceRepository.findById(id).orElseThrow();
    }

    public Advice getRandomAdvice(String mood, String extraMood) {
        // TODO: Add logging and error handling here!
        List<Advice> listOfChoose = adviceRepository.findByMood(mood);
        List<Advice> listOfExtras = adviceRepository.findByMood(extraMood);
        List<Advice> combinedList = new ArrayList<>(listOfChoose);
        combinedList.addAll(listOfExtras);

//        if (combinedList.isEmpty()) {
//            System.warn("No advices found for mood: " + mood + " and extraMood: " + extraMood);
//            return null; // Or throw an exception, depending on your needs
//        }
        Random rand = new Random();
        int index = rand.nextInt(combinedList.size());
        return combinedList.get(index);
    }
}