package org.maravill.foro_hub.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.maravill.foro_hub.global.dto.ResponsePage;
import org.maravill.foro_hub.security.dto.RequestOperation;
import org.maravill.foro_hub.security.dto.RequestRole;
import org.maravill.foro_hub.security.dto.ResponseRole;
import org.maravill.foro_hub.security.exception.SecurityDataNotFoundException;
import org.maravill.foro_hub.security.models.Operation;
import org.maravill.foro_hub.security.models.Role;
import org.maravill.foro_hub.security.repository.IRoleRepository;
import org.maravill.foro_hub.security.service.IRoleService;
import org.maravill.foro_hub.security.utils.SecurityMapperService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final IRoleRepository roleRepository;
    private final SecurityMapperService securityMapperService;

    @Override
    public ResponsePage<ResponseRole> getAllRoles(Pageable pageable) {
        Page<Role> roles = roleRepository.findAll(pageable);
        return ResponsePage.from(roles.map(securityMapperService::mapToRoleResponse));
    }

    @Override
    public ResponseRole findRoleById(Long idRole) {
        Role role = roleRepository.findById(idRole)
                .orElseThrow(() -> new SecurityDataNotFoundException("Role does not exist"));
        return securityMapperService.mapToRoleResponse(role);
    }

    @Transactional
    @Override
    public ResponseRole saveNewRole(RequestRole requestRole) {
        Role role = securityMapperService.mapToRole(requestRole);
        role.setIdRole(null);
        Role saved = roleRepository.save(role);
        return securityMapperService.mapToRoleResponse(saved);
    }

    @Transactional
    @Override
    public ResponseRole updateRole(Long idRole, RequestRole requestRole) {
        Role role = roleRepository.findById(idRole)
                .orElseThrow(() -> new SecurityDataNotFoundException("Role does not exist"));
        role.setName(requestRole.name());
        return securityMapperService.mapToRoleResponse(roleRepository.save(role));
    }

    @Transactional
    @Override
    public void addOperations(Long idRole, List<RequestOperation> operations) {
        Role role = roleRepository.findById(idRole)
                .orElseThrow(() -> new SecurityDataNotFoundException("Role does not exist"));
        List<Long> existingIds = role.getOperations()
                .stream()
                .map(Operation::getIdOperation)
                .toList();
        List<Operation> toAdd = operations.stream()
                .map(securityMapperService::mapToOperation)
                .filter(op -> op.getIdOperation() != null && !existingIds.contains(op.getIdOperation()))
                .toList();

        if (!toAdd.isEmpty()) {
            role.getOperations().addAll(toAdd);
            roleRepository.save(role);
        }
    }

    @Transactional
    @Override
    public void deleteRole(Long idRole) {
        Role role = roleRepository.findById(idRole)
                .orElseThrow(() -> new SecurityDataNotFoundException("Role does not exist"));
        roleRepository.delete(role);
    }

    @Override
    public Role findDefaultRole() {
        return roleRepository.findByName("USER")
                .orElseThrow(() -> new SecurityDataNotFoundException("Default role not found"));
    }

    @Override
    public Role findEntityRoleById(Long idRole) {
        return roleRepository.findById(idRole)
                .orElseThrow(() -> new SecurityDataNotFoundException("Role does not exist"));
    }

}
