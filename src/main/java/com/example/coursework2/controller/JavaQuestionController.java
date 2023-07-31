package com.example.coursework2.controller;

import com.example.coursework2.model.Question;
import com.example.coursework2.service.QuestionService;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;

@RestController
public class JavaQuestionController {
    private final QuestionService service;


    public JavaQuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping("/exam/java/add")
    public Question addQuestion(@RequestParam String question, @RequestParam String answer) {
        return service.add(question, answer);
    }

    @GetMapping("/exam/java/remove")
    public Question removeQuestion(@RequestParam String question, @RequestParam String answer) {
        Question questionToRemove = new Question(question, answer);
        return service.remove(questionToRemove);
    }

    @GetMapping("/exam/java")
    public Collection<Question> getAllQuestions() {
        return service.getAll();
    }
}