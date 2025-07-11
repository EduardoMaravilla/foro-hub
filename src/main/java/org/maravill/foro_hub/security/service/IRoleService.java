package org.maravill.foro_hub.security.service;

import org.maravill.foro_hub.global.dto.ResponsePage;
import org.maravill.foro_hub.security.dto.RequestOperation;
import org.maravill.foro_hub.security.dto.RequestRole;
import org.maravill.foro_hub.security.dto.ResponseRole;
import org.maravill.foro_hub.security.models.Role;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRoleService {
    ResponsePage<ResponseRole> getAllRoles(Pageable pageable);

    ResponseRole findRoleById(Long idRole);

    ResponseRole saveNewRole(RequestRole requestRole);

    ResponseRole updateRole(Long idRole,RequestRole requestRole);

    void addOperations(Long idRole, List<RequestOperation> operations);

    void deleteRole(Long idRole);

    Role findDefaultRole();

    Role findEntityRoleById(Long idRole);
}
