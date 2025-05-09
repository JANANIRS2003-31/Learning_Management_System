package com.cts.lms;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.lms.feignclient.CourseClient;
import com.cts.lms.model.Quiz;
import com.cts.lms.model.QuizSubmission;
import com.cts.lms.repository.QuizRepository;
import com.cts.lms.repository.QuizSubmissionRepository;
import com.cts.lms.service.QuizServiceImpl;

@SpringBootTest
class QuizServiceImplTests {

    @Mock
    QuizRepository quizRepository;

    @Mock
    QuizSubmissionRepository submissionRepository;

    @Mock
    CourseClient courseClient;

    @InjectMocks
    QuizServiceImpl service;

    @Test
    void createQuizTest() {
        Quiz quiz = new Quiz(1, 101, "Java Basics", 50, new ArrayList<>(List.of("Q1?", "Q2?", "Q3?")), new ArrayList<>(List.of("A", "B", "C")));

        when(courseClient.checkCourseExist(anyInt())).thenReturn(true);
        when(quizRepository.save(any(Quiz.class))).thenReturn(quiz);

        String response = service.createQuiz(quiz);
        assertEquals("Quiz Created", response);
    }

    @Test
    void updateQuizTest() {
        Quiz quiz = new Quiz(2, 102, "Spring Boot Basics", 40, new ArrayList<>(List.of("Is Spring Boot useful?", "Does it support REST?")), new ArrayList<>(List.of("Yes", "True")));

        when(quizRepository.save(any(Quiz.class))).thenReturn(quiz);

        Quiz updatedQuiz = service.updateQuiz(quiz);
        assertEquals(quiz, updatedQuiz);
    }

    @Test
    void deleteQuizTest() {
        int quizId = 3;
        Quiz quiz = new Quiz(quizId, 103, "Machine Learning", 60, new ArrayList<>(List.of("What is ML?", "Define AI.")), new ArrayList<>(List.of("Automation", "Human-like")));

        when(quizRepository.findById(quizId)).thenReturn(Optional.of(quiz));
        doNothing().when(quizRepository).delete(any(Quiz.class));

        String response = service.deleteQuiz(quizId);
        assertEquals("Quiz Deleted", response);
    }

    @Test
    void getQuizByIdTest() {
        int quizId = 4;
        Quiz quiz = new Quiz(quizId, 104, "Cybersecurity Principles", 70, new ArrayList<>(List.of("What is encryption?", "What does a firewall do?")), new ArrayList<>(List.of("Secures Data", "Blocks Unauthorized Access")));

        when(quizRepository.findById(quizId)).thenReturn(Optional.of(quiz));

        Quiz retrievedQuiz = service.getQuizById(quizId);
        assertEquals(quiz, retrievedQuiz);
    }

    @Test
    void evaluateQuizTest() {
        Quiz quiz = new Quiz(5, 105, "AI & Data Science", 50, new ArrayList<>(List.of("What is AI?", "What is Big Data?", "Define Model Training")), new ArrayList<>(List.of("Automation", "Massive Data", "ML Process")));
        QuizSubmission submission = new QuizSubmission(1, 5, 105, new ArrayList<>(List.of("Automation", "Wrong Answer", "ML Process")), 0, false);

        when(quizRepository.findById(anyInt())).thenReturn(Optional.of(quiz));
        when(submissionRepository.save(any(QuizSubmission.class))).thenReturn(submission);

        QuizSubmission result = service.evaluateQuiz(submission);
        assertEquals(20, result.getScore()); // Assuming correct answers earn 10 points each
        assertFalse(result.isPassed()); // Passing requires 50% of total marks
    }

    @Test
    void getAllQuizzesTest() {
        List<Quiz> quizzes = List.of(
            new Quiz(6, 106, "AWS Cloud Computing", 40, new ArrayList<>(List.of("What is EC2?", "Define IAM.")), new ArrayList<>(List.of("Virtual Server", "User Access Control"))),
            new Quiz(7, 107, "Python Programming", 30, new ArrayList<>(List.of("What is Python?", "Define OOP.")), new ArrayList<>(List.of("Programming Language", "Object-Oriented Principles")))
        );

        when(quizRepository.findAll()).thenReturn(quizzes);

        List<Quiz> retrievedQuizzes = service.getAllQuizzes();
        assertEquals(quizzes, retrievedQuizzes);
    }

    @Test
    void getAllQuizSubmissionByUserIdTest() {
        int userId = 108;
        List<QuizSubmission> submissions = List.of(
            new QuizSubmission(2, 8, userId, new ArrayList<>(List.of("Correct", "Wrong")), 30, false),
            new QuizSubmission(3, 9, userId, new ArrayList<>(List.of("True", "False")), 40, true)
        );

        when(submissionRepository.findByUserId(anyInt())).thenReturn(submissions);

        List<QuizSubmission> retrievedSubmissions = service.getAllQuizSubmissionByUserId(userId);
        assertEquals(submissions, retrievedSubmissions);
    }

    @Test
    void getQuizByCourseIdTest() {
        int courseId = 109;
        List<Quiz> quizzes = List.of(
            new Quiz(10, courseId, "Cloud Security Essentials", 80, new ArrayList<>(List.of("What is Cloud Security?", "What is Data Encryption?")), new ArrayList<>(List.of("Secures Cloud Resources", "Protects Data"))),
            new Quiz(11, courseId, "AI Ethics", 60, new ArrayList<>(List.of("Define AI Bias", "What is Fair AI?")), new ArrayList<>(List.of("Unfair Decisions", "Balanced AI")))
        );

        when(quizRepository.findByCourseId(anyInt())).thenReturn(quizzes);

        List<Quiz> retrievedQuizzes = service.getQuizByCourseId(courseId);
        assertEquals(quizzes, retrievedQuizzes);
    }

    @Test
    void getQuizSubmissionByUserIdTest() {
        int userId = 110;
        int quizId = 12;
        QuizSubmission submission = new QuizSubmission(4, quizId, userId, new ArrayList<>(List.of("Right", "Wrong")), 20, false);

        when(submissionRepository.findByUserIdAndQuizId(anyInt(), anyInt())).thenReturn(submission);

        QuizSubmission retrievedSubmission = service.getQuizSubmissionByUserId(userId, quizId);
        assertEquals(submission, retrievedSubmission);
    }
}
