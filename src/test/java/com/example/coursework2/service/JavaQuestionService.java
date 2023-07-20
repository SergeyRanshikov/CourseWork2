package com.example.coursework2.service;

import com.example.coursework2.model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class JavaQuestionService implements QuestionService {
    private Set<Question> questionSet;

    public JavaQuestionService() {
        questionSet = new HashSet<>();
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        questionSet.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        questionSet.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        questionSet.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questionSet;
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        int index = random.nextInt(questionSet.size());
        int i = 0;
        for (Question question : questionSet) {
            if (i == index) {
                return question;
            }
            i++;
        }
        return null;

    }
}
