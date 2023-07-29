package com.example.coursework2.service;

import com.example.coursework2.exception.BadRequestException;
import com.example.coursework2.model.Question;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;

public class ExaminerServiceImplTest {
    private ExaminerService examinerService;

    @Mock
    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        examinerService = new ExaminerServiceImpl(questionService);
    }

    @Test
    public void testGetQuestionsWithValidAmount() {

        Question question1 = new Question("Question 1", "Answer 1");
        Question question2 = new Question("Question 2", "Answer 2");
        Collection<Question> javaQuestions = new HashSet<>(Set.of(question1, question2));
        when(questionService.getAll()).thenReturn(javaQuestions);
        when(questionService.getRandomQuestion()).thenReturn(question1, question2);


        Collection<Question> questions = examinerService.getQuestions(javaQuestions.size());


        Assertions.assertIterableEquals(List.of(question1, question2), questions);
        verify(questionService, times(2)).getAll();

    }

    @Test
    void testGetQuestions_LessThanAllQuestionsRequested() {
        Question question1 = new Question("Java Question 1", "Java Answer 1");
        Question question2 = new Question("Java Question 2", "Java Answer 2");
        Collection<Question> javaQuestions = new HashSet<>(Set.of(question1, question2));
        when(questionService.getAll()).thenReturn(javaQuestions);


        Collection<Question> selectedQuestions = examinerService.getQuestions(2);


        assertIterableEquals(new HashSet<>(List.of(javaQuestions.get(0))), selectedQuestions);


        assertEquals(2, selectedQuestions.size());

    }
    @Test
    public void testGetQuestionsWithInvalidAmount() {

        int amount = 3;
        when(questionService.getAll()).thenReturn(new HashSet<Question>());


        Assertions.assertThrows(BadRequestException.class, () -> examinerService.getQuestions(amount));
        verify(questionService, times(1)).getAll();
        verify(questionService, never()).getRandomQuestion();
    }
}

