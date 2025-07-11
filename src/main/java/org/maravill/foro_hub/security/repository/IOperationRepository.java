package org.maravill.foro_hub.security.repository;

import org.maravill.foro_hub.security.models.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOperationRepository extends JpaRepository<Operation,Long> {
    List<Operation> findAllByPermitAllIsTrue();

    Page<Operation> findByModule_IdModule(Long idModule, Pageable pageable);

    Page<Operation> findByRoles_IdRole(Long idRole, Pageable pageable);
}
