package org.maravill.foro_hub.foro.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@DisplayName("Test Course Repository")
class ICourseRepositoryTest {

    @Autowired
    private ICourseRepository courseRepository;

    @Test
    @DisplayName("Test exists by name and different course id")
    void existsByNameIgnoreCaseAndIdCourseNot() {

        String testCourseName = "Programación en Java";
        Long testIdCourse = 4L;
        Long testIdCourseInf = Long.MAX_VALUE;
        boolean testFalseExistsCourse = courseRepository
                .existsByNameIgnoreCaseAndIdCourseNot(testCourseName,testIdCourse);
        boolean testTrueExistsCourse = courseRepository
                .existsByNameIgnoreCaseAndIdCourseNot(testCourseName,testIdCourseInf);
        assertFalse(testFalseExistsCourse, "Deber ser falso");
        assertTrue(testTrueExistsCourse, "Debe ser verdadero");
    }
}