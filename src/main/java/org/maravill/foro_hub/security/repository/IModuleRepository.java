package org.maravill.foro_hub.security.repository;

import org.maravill.foro_hub.security.models.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IModuleRepository extends JpaRepository<Module,Long> {
}
