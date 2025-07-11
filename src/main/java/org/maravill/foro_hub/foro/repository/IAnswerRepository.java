package org.maravill.foro_hub.foro.repository;

import org.maravill.foro_hub.foro.models.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnswerRepository extends JpaRepository<Answer,Long> {
    Page<Answer> findAllByTopic_IdTopic(Long idTopic, Pageable pageable);
}
