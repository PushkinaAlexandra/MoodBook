package com.back.MoodBook.service;

import com.back.MoodBook.dtos.AdviceCreateRequest;
import com.back.MoodBook.entity.Advice;
import com.back.MoodBook.repository.AdviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class AdviceService {
    public AdviceRepository adviceRepository;

    @Autowired
    public AdviceService(AdviceRepository adviceRepository){
        this.adviceRepository = adviceRepository;
    }

    public List<Advice> createBook(String keyMood){
        return adviceRepository.findByMood(keyMood);
    }

    public List<Advice> createBookAllAdvice(){
        return adviceRepository.findAll();
    }

    public Advice createAdvice(AdviceCreateRequest adviceCreateRequest){
        Advice advice = new Advice();
        advice.setName(adviceCreateRequest.getName());
        advice.setContent(adviceCreateRequest.getContent());
        advice.setMood(adviceCreateRequest.getMood());
        adviceRepository.save(advice);
        return advice;
    }

    public Advice getAdvice(Long id){
        return adviceRepository.findById(id).orElseThrow();
    }

    public Advice getRandomAdvice(String mood, String extraMood){
        List<Advice> listOfChoose = adviceRepository.findByMood(mood);
        List<Advice> listOfExtras = adviceRepository.findByMood(extraMood);
        listOfChoose = Stream.concat(listOfChoose.stream(), listOfExtras.stream()).toList();
        Random rand = new Random();
        int index = rand.nextInt(listOfChoose.size());
        return listOfChoose.get(index);
    }

}
