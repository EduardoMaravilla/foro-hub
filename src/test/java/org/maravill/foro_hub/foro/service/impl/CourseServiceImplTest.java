package org.maravill.foro_hub.foro.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.maravill.foro_hub.foro.dto.RequestCourse;
import org.maravill.foro_hub.foro.dto.ResponseCourse;
import org.maravill.foro_hub.foro.exception.ForoDataNotFoundException;
import org.maravill.foro_hub.foro.exception.ForoInvalidDataException;
import org.maravill.foro_hub.foro.models.Category;
import org.maravill.foro_hub.foro.models.Course;
import org.maravill.foro_hub.foro.repository.ICourseRepository;
import org.maravill.foro_hub.foro.utils.ForoMapperService;
import org.maravill.foro_hub.global.dto.ResponsePage;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test Course Service")
class CourseServiceImplTest {

    @InjectMocks
    private CourseServiceImpl courseService;

    @Mock
    private ICourseRepository courseRepository;

    @Mock
    private ForoMapperService foroMapperService;

    @Mock
    private Pageable pageable;

    private Course course;
    private RequestCourse requestCourse;
    private ResponseCourse responseCourse;

    @BeforeEach
    void setUp() {
        course = new Course(1L, "Spring Boot", Category.PROGRAMMING);
        requestCourse = new RequestCourse(1L, "Spring Boot","PROGRAMMING");
        responseCourse = new ResponseCourse(1L, "Spring Boot", "PROGRAMMING");
    }

    @Test
    @DisplayName("Test find entity course by ID")
    void findEntityCourseById() {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        Course result = courseService.findEntityCourseById(1L);
        assertEquals("Spring Boot", result.getName());
    }

    @Test
    @DisplayName("Test Find entity course by ID fail")
    void findEntityCourseByIdFail() {
        when(courseRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ForoDataNotFoundException.class, () -> courseService.findEntityCourseById(99L));
    }

    @Test
    @DisplayName("Test Find all courses")
    void findAllCourses() {
        Page<Course> page = new PageImpl<>(List.of(course));
        when(courseRepository.findAll(pageable)).thenReturn(page);
        when(foroMapperService.mapToCourseResponse(course)).thenReturn(responseCourse);

        ResponsePage<ResponseCourse> result = courseService.findAllCourses(pageable);
        assertEquals(1, result.content().size());
        assertEquals("Spring Boot", result.content().getFirst().name());
    }

    @Test
    @DisplayName("Test Find course by ID")
    void findCourseById() {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(foroMapperService.mapToCourseResponse(course)).thenReturn(responseCourse);

        ResponseCourse result = courseService.findCourseById(1L);
        assertEquals("Spring Boot", result.name());
    }

    @Test
    @DisplayName("Test Find course by ID fail")
    void findCourseByIdFail() {
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ForoDataNotFoundException.class, () -> courseService.findCourseById(1L));
    }

    @Test
    @DisplayName("Test Create new course")
    void createNewCourse() {
        when(foroMapperService.mapToCourse(requestCourse)).thenReturn(course);
        when(courseRepository.save(any())).thenReturn(course);
        when(foroMapperService.mapToCourseResponse(course)).thenReturn(responseCourse);

        ResponseCourse result = courseService.createNewCourse(requestCourse);
        assertEquals("Spring Boot", result.name());
    }

    @Test
    @DisplayName("Test Update course")
    void updateCourse() {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(courseRepository.existsByNameIgnoreCaseAndIdCourseNot("Spring Boot", 1L)).thenReturn(false);
        when(courseRepository.save(any())).thenReturn(course);
        when(foroMapperService.mapToCourseResponse(any())).thenReturn(responseCourse);

        ResponseCourse result = courseService.updateCourse(1L, requestCourse);
        assertEquals("Spring Boot", result.name());
    }

    @Test
    @DisplayName("Test Update course but name already exists")
    void updateCourseFail() {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(courseRepository.existsByNameIgnoreCaseAndIdCourseNot("Spring Boot", 1L)).thenReturn(true);

        assertThrows(ForoInvalidDataException.class, () -> courseService.updateCourse(1L, requestCourse));
    }

    @Test
    @DisplayName("Test Update course fail")
    void updateCourseNotFound() {
        when(courseRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ForoDataNotFoundException.class, () -> courseService.updateCourse(99L, requestCourse));
    }

    @Test
    @DisplayName("Test Delete course")
    void deleteCourse() {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        courseService.deleteCourse(1L);
        verify(courseRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Test Delete course not found")
    void deleteCourseNotFound() {
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ForoDataNotFoundException.class, () -> courseService.deleteCourse(1L));
    }
}

