package org.maravill.foro_hub.foro.repository;

import org.maravill.foro_hub.foro.models.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITopicRepository extends JpaRepository<Topic, Long> {

    boolean existsByTitleIgnoreCaseAndMessageIgnoreCase(String title, String description);

    Page<Topic> findByCourse_NameIgnoreCaseAndCreatedAtYear(String courseName, Integer year, Pageable pageable);

    Page<Topic> findByCourse_NameIgnoreCase(String courseName, Pageable pageable);

    boolean existsByTitleIgnoreCaseAndMessageIgnoreCaseAndIdTopicNot(String title, String message, Long idTopic);
}
