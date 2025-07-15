package org.maravill.foro_hub.foro.service.impl;

import lombok.RequiredArgsConstructor;
import org.maravill.foro_hub.foro.dto.RequestCourse;
import org.maravill.foro_hub.foro.dto.ResponseCourse;
import org.maravill.foro_hub.foro.exception.ForoDataNotFoundException;
import org.maravill.foro_hub.foro.exception.ForoInvalidDataException;
import org.maravill.foro_hub.foro.models.Category;
import org.maravill.foro_hub.foro.models.Course;
import org.maravill.foro_hub.foro.repository.ICourseRepository;
import org.maravill.foro_hub.foro.utils.ForoMapperService;
import org.maravill.foro_hub.foro.service.ICourseService;
import org.maravill.foro_hub.global.dto.ResponsePage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements ICourseService {

    private final ICourseRepository courseRepository;
    private final ForoMapperService foroMapperService;

    @Override
    public Course findEntityCourseById(Long idCourse) {
        return courseRepository.findById(idCourse)
                .orElseThrow(() -> new ForoDataNotFoundException("Course not found"));
    }

    @Override
    public ResponsePage<ResponseCourse> findAllCourses(Pageable pageable) {
        Page<Course> courses = courseRepository.findAll(pageable);
        return ResponsePage.from(courses.map(foroMapperService::mapToCourseResponse));
    }

    @Override
    public ResponseCourse findCourseById(Long idCourse) {
        return courseRepository.findById(idCourse).map(foroMapperService::mapToCourseResponse)
                .orElseThrow(() -> new ForoDataNotFoundException("Course not found"));
    }

    @Transactional
    @Override
    public ResponseCourse createNewCourse(RequestCourse requestCourse) {
        Course course = foroMapperService.mapToCourse(requestCourse);
        course.setIdCourse(null);
        return foroMapperService.mapToCourseResponse(courseRepository.save(course));
    }

    @Transactional
    @Override
    public ResponseCourse updateCourse(Long idCourse, RequestCourse requestCourse) {
        Course course = courseRepository.findById(idCourse)
                .orElseThrow(() -> new ForoDataNotFoundException("Course not found"));
        if(courseRepository.existsByNameIgnoreCaseAndIdCourseNot(requestCourse.name(),idCourse)){
            throw new ForoInvalidDataException("Name already exists");
        }
        course.setName(requestCourse.name());
        course.setCategory(Category.getCategory(requestCourse.category()));
        return foroMapperService.mapToCourseResponse(courseRepository.save(course));
    }

    @Transactional
    @Override
    public void deleteCourse(Long idCourse) {
       Course course = courseRepository.findById(idCourse)
               .orElseThrow(() -> new ForoDataNotFoundException("Course not found"));
       courseRepository.deleteById(course.getIdCourse());
    }
}
