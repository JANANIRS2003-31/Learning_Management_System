package com.cts.lms;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.lms.dto.*;
import com.cts.lms.feignclient.EnrollmentClient;
import com.cts.lms.feignclient.QuizClient;
import com.cts.lms.service.ProgressTrackingServiceImpl;

@SpringBootTest
class ProgressTrackingServiceImplTests {

    @Mock
    private EnrollmentClient enrollmentClient;

    @Mock
    private QuizClient quizClient;

    @InjectMocks
    private ProgressTrackingServiceImpl service;

    private List<CourseDTO> courses;
    private List<QuizDTO> quizzes;
    private QuizSubmissionDTO submissionML;
    private QuizSubmissionDTO submissionSecurity;

    @BeforeEach
    void setUp() {
        int userId = 5;
        
        // Sample Courses
        courses = List.of(
            new CourseDTO(101, "Data Science", "Intro to Data", "ML Basics", 1, "Basic Math"),
            new CourseDTO(102, "Cybersecurity", "Network Security", "Threat Detection", 2, "Networking Basics")
        );

        // Sample Quizzes
        quizzes = List.of(
            new QuizDTO(201, 101, "ML Test", 100),
            new QuizDTO(202, 102, "Security Quiz", 100)
        );

        // Sample Submissions with fixed constructor
        submissionML = new QuizSubmissionDTO(301, 201, userId, new ArrayList<>(), 80, true);
        submissionSecurity = new QuizSubmissionDTO(302, 202, userId, new ArrayList<>(), 50, false);
    }

    @Test
    void getCourseByUserIdTest() {
        int userId = 5;
        
        when(enrollmentClient.getCoursesByUserId(userId)).thenReturn(courses);

        List<CourseDTO> retrievedCourses = service.getCourseByUserId(userId);

        assertEquals(courses.size(), retrievedCourses.size());
        assertEquals(courses, retrievedCourses);
    }

    @Test
    void getProgressByUserIdTest() {
        int userId = 5;

        when(enrollmentClient.getCoursesByUserId(userId)).thenReturn(courses);
        when(quizClient.getQuizByCourseId(101)).thenReturn(List.of(quizzes.get(0)));
        when(quizClient.getQuizByCourseId(102)).thenReturn(List.of(quizzes.get(1)));
        when(quizClient.getQuizSubmissionByUserId(userId, 201)).thenReturn(submissionML);
        when(quizClient.getQuizSubmissionByUserId(userId, 202)).thenReturn(submissionSecurity);

        UserDTO progress = service.getProgressByUserId(userId);

        assertEquals(userId, progress.getUserId());
        assertEquals(2, progress.getCourses().size());

        CourseProgressDTO dataScienceProgress = progress.getCourses().get(0);
        assertEquals(101, dataScienceProgress.getCourseId());
        assertEquals(80.0, dataScienceProgress.getQuizzes().get(0).getProgressPercentage(), 0.01); // 80/100 * 100

        CourseProgressDTO securityProgress = progress.getCourses().get(1);
        assertEquals(102, securityProgress.getCourseId());
        assertEquals(50.0, securityProgress.getQuizzes().get(0).getProgressPercentage(), 0.01); // 50/100 * 100
    }
}
