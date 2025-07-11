package org.maravill.foro_hub.security.repository;

import org.maravill.foro_hub.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(String user);
}
