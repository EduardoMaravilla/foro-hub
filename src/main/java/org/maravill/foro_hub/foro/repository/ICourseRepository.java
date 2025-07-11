package org.maravill.foro_hub.foro.repository;

import org.maravill.foro_hub.foro.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRepository extends JpaRepository<Course,Long> {

    boolean existsByNameIgnoreCaseAndIdCourseNot(String name, Long idCourse);
}
