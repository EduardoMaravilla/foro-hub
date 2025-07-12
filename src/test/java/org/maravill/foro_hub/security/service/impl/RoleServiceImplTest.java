package org.maravill.foro_hub.security.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.maravill.foro_hub.global.dto.ResponsePage;
import org.maravill.foro_hub.security.dto.RequestOperation;
import org.maravill.foro_hub.security.dto.RequestRole;
import org.maravill.foro_hub.security.dto.ResponseRole;
import org.maravill.foro_hub.security.models.Operation;
import org.maravill.foro_hub.security.models.Role;
import org.maravill.foro_hub.security.repository.IRoleRepository;
import org.maravill.foro_hub.security.utils.SecurityMapperService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test Role Service")
class RoleServiceImplTest {

    @Mock
    private IRoleRepository roleRepository;
    @Mock
    private SecurityMapperService mapper;

    @InjectMocks
    private RoleServiceImpl service;

    private Role role;
    private RequestRole requestRole;
    private ResponseRole responseRole;

    @BeforeEach
    void setUp() {
        role = new Role(1L, "ADMIN");
        requestRole = new RequestRole(1L, "ADMIN");
        responseRole = new ResponseRole(1L, "ADMIN");
    }

    @Test
    @DisplayName("Test get all roles")
    void getAllRoles() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Role> page = new PageImpl<>(List.of(role));
        when(roleRepository.findAll(pageable)).thenReturn(page);
        when(mapper.mapToRoleResponse(role)).thenReturn(responseRole);

        ResponsePage<ResponseRole> result = service.getAllRoles(pageable);

        assertEquals(1, result.content().size());
        assertEquals("ADMIN", result.content().getFirst().name());
    }

    @Test
    @DisplayName("Test find role by ID")
    void findRoleById() {
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(mapper.mapToRoleResponse(role)).thenReturn(responseRole);

        ResponseRole result = service.findRoleById(1L);

        assertEquals("ADMIN", result.name());
    }

    @Test
    @DisplayName("Test save new role")
    void saveNewRole() {
        when(mapper.mapToRole(requestRole)).thenReturn(role);
        when(roleRepository.save(role)).thenReturn(role);
        when(mapper.mapToRoleResponse(role)).thenReturn(responseRole);

        ResponseRole result = service.saveNewRole(requestRole);

        assertEquals("ADMIN", result.name());
    }

    @Test
    @DisplayName("Test update role")
    void updateRole() {
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(roleRepository.save(role)).thenReturn(role);
        when(mapper.mapToRoleResponse(role)).thenReturn(responseRole);

        ResponseRole result = service.updateRole(1L, requestRole);

        assertEquals("ADMIN", result.name());
    }

    @Test
    @DisplayName("Test add operations to role")
    void addOperations() {
        Operation op = new Operation();
        op.setIdOperation(2L);
        role.setOperations(new ArrayList<>(List.of(new Operation(1L, "OP", null, "/op", false, null))));

        RequestOperation reqOp = new RequestOperation(2L, "NEW", "GET", "/new", true, null);

        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(mapper.mapToOperation(reqOp)).thenReturn(op);

        service.addOperations(1L, List.of(reqOp));

        assertTrue(role.getOperations().contains(op));
        verify(roleRepository).save(role);
    }

    @Test
    @DisplayName("Test delete role")
    void deleteRole() {
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));

        service.deleteRole(1L);

        verify(roleRepository).delete(role);
    }

    @Test
    @DisplayName("Test find default role")
    void findDefaultRole() {
        when(roleRepository.findByName("USER")).thenReturn(Optional.of(role));

        Role result = service.findDefaultRole();

        assertEquals("ADMIN", result.getName());
    }

    @Test
    @DisplayName("Test find entity role by ID")
    void findEntityRoleById() {
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));

        Role result = service.findEntityRoleById(1L);

        assertEquals("ADMIN", result.getName());
    }
}