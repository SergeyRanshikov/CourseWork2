package com.example.coursework2.service;

import com.example.coursework2.exception.BadRequestException;
import com.example.coursework2.model.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private QuestionService questionService;
    private Random random;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
        this.random = new Random();
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > questionService.getAll().size() || amount <= 0) {
            throw new BadRequestException("Запрошенное количество вопросов превышает количество доступных вопросов либо равно или меньше 0");
        }

        Collection<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            Question randomQuestion = questionService.getRandomQuestion();
            if (randomQuestion != null) {
                questions.add(randomQuestion);
            }
        }
        return questions;
    }
}
