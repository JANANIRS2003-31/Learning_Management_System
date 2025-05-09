package com.cts.lms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.lms.dto.Course;
import com.cts.lms.dto.User;
import com.cts.lms.dto.UserCourseEnrollResponseDTO;
import com.cts.lms.feignclient.CourseClient;
import com.cts.lms.feignclient.UserClient;
import com.cts.lms.model.Enrollment;
import com.cts.lms.repository.EnrollmentRepository;
import com.cts.lms.service.EnrollmentServiceImpl;

@SpringBootTest
class EnrollmentServiceTests {
    
    @Mock
    EnrollmentRepository repository;

    @Mock
    UserClient userClient;

    @Mock
    CourseClient courseClient;
    
    @InjectMocks
    EnrollmentServiceImpl service;

    @Test
    void saveEnrollmentTest() {
        Enrollment enrollment = new Enrollment(1, 1, 5);
        Mockito.when(userClient.checkUserExist(enrollment.getUserId())).thenReturn(true);
        Mockito.when(courseClient.checkCourseExist(enrollment.getCourseId())).thenReturn(true);
        Mockito.when(repository.save(enrollment)).thenReturn(enrollment);
        
        String response = service.saveEnrollment(enrollment);
        assertEquals("Enrollment Successfully Saved", response);
    }

    @Test
    void cancelEnrollmentTest() {
        int enrollmentId = 3;
        Enrollment enrollment = new Enrollment(enrollmentId, 3, 3);
        Mockito.when(repository.findById(enrollmentId)).thenReturn(Optional.of(enrollment));
        
        String response = service.cancelEnrollment(enrollmentId);
        assertEquals("Enrollment Deleted", response);
        
        Mockito.verify(repository, Mockito.times(1)).delete(enrollment);
    }
    
    @Test
    void cancelEnrollmentErrorHandlingTest() {
        int enrollmentId = 999;
        Mockito.when(repository.findById(enrollmentId)).thenReturn(Optional.empty());
        
        assertThrows(RuntimeException.class, () -> service.cancelEnrollment(enrollmentId));
    }

    @Test
    void getEnrollmentTest() {
        int enrollmentId = 10;
        Enrollment enrollment = new Enrollment(enrollmentId, 1010, 5010);
        User user = new User(1010, "John Doe", "john.doe@example.com");
        Course course = new Course(5010, "Data Science", "Intro to AI", "Python, ML Basics", "Basic programming");

        Mockito.when(repository.findById(enrollmentId)).thenReturn(Optional.of(enrollment));
        Mockito.when(userClient.getById(1010)).thenReturn(user);
        Mockito.when(courseClient.getCourse(5010)).thenReturn(course);

        UserCourseEnrollResponseDTO responseDTO = service.getEnrollment(enrollmentId);
        assertEquals(user, responseDTO.getUser());
        assertEquals(course, responseDTO.getCourse());
        assertEquals(enrollment, responseDTO.getEnroll());
    }
}
