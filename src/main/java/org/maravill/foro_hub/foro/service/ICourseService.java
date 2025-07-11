package org.maravill.foro_hub.foro.service;

import org.maravill.foro_hub.foro.dto.RequestCourse;
import org.maravill.foro_hub.foro.dto.ResponseCourse;
import org.maravill.foro_hub.foro.models.Course;
import org.maravill.foro_hub.global.dto.ResponsePage;
import org.springframework.data.domain.Pageable;

public interface ICourseService {
    Course findEntityCourseById(Long idCourse);

    ResponsePage<ResponseCourse> findAllCourses(Pageable pageable);

    ResponseCourse findCourseById(Long idCourse);

    ResponseCourse createNewCourse(RequestCourse requestCourse);

    ResponseCourse updateCourse(Long idCourse,RequestCourse requestCourse);

    void deleteCourse(Long idCourse);
}
