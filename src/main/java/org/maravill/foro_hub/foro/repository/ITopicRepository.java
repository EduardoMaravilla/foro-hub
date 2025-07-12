package org.maravill.foro_hub.foro.repository;

import org.maravill.foro_hub.foro.models.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITopicRepository extends JpaRepository<Topic, Long> {

    boolean existsByTitleIgnoreCaseAndMessageIgnoreCase(String title, String description);

    @Query("""
                SELECT t FROM Topic t
                WHERE LOWER(t.course.name) = LOWER(:courseName)
                  AND FUNCTION('YEAR', t.createdAt) = :year
            """)
    Page<Topic> findByCourse_NameIgnoreCaseAndCreatedAtYear(@Param("courseName") String courseName, @Param("year") Integer year, Pageable pageable);

    @Query("""
                SELECT t FROM Topic t
                WHERE FUNCTION('YEAR', t.createdAt) = :year
            """)
    Page<Topic> findByCreatedAtYear(@Param("year") Integer year, Pageable pageable);

    Page<Topic> findByCourse_NameIgnoreCase(String courseName, Pageable pageable);

    boolean existsByTitleIgnoreCaseAndMessageIgnoreCaseAndIdTopicNot(String title, String message, Long idTopic);
}
