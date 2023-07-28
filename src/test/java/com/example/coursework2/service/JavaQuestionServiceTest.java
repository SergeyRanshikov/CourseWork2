package com.example.coursework2.service;

import com.example.coursework2.model.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class JavaQuestionServiceTest {

    @InjectMocks
    private JavaQuestionService javaQuestionService;

    @Mock
    private Question question;

    @Before
    public void setup() {
        javaQuestionService = new JavaQuestionService();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddQuestionWithTextAndAnswer() {
        Question addedQuestion = javaQuestionService.add("Самый лучший язык программирования?", "Java");
        assertNotNull(addedQuestion);
        assertEquals("Самый лучший язык программирования?", addedQuestion.getQuestion());
        assertEquals("Java", addedQuestion.getAnswer());
    }

    @Test
    public void testAddQuestion() {
        Question addedQuestion = javaQuestionService.add(question);
        assertNotNull(addedQuestion);
        assertEquals(question, addedQuestion);
    }

    @Test
    public void testRemoveQuestion() {
        javaQuestionService.add(question);
        Question removedQuestion = javaQuestionService.remove(question);
        assertNotNull(removedQuestion);
        assertEquals(question, removedQuestion);
    }

    @Test
    public void testGetAllQuestions() {
        javaQuestionService.add(question);
        Collection<Question> allQuestions = javaQuestionService.getAll();
        assertNotNull(allQuestions);
        assertEquals(1, allQuestions.size());
        assertTrue(allQuestions.contains(question));
    }

    @Test
    public void testGetRandomQuestion() {
        javaQuestionService.add(question);
        Question randomQuestion = javaQuestionService.getRandomQuestion();
        assertNotNull(randomQuestion);
        assertEquals(question, randomQuestion);
    }
}
