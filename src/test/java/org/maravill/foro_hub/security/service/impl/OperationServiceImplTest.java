package org.maravill.foro_hub.security.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.maravill.foro_hub.global.dto.ResponsePage;
import org.maravill.foro_hub.security.dto.RequestModule;
import org.maravill.foro_hub.security.dto.RequestOperation;
import org.maravill.foro_hub.security.dto.ResponseOperation;
import org.maravill.foro_hub.security.models.HttpMethod;
import org.maravill.foro_hub.security.models.Module;
import org.maravill.foro_hub.security.models.Operation;
import org.maravill.foro_hub.security.models.Role;
import org.maravill.foro_hub.security.repository.IModuleRepository;
import org.maravill.foro_hub.security.repository.IOperationRepository;
import org.maravill.foro_hub.security.repository.IRoleRepository;
import org.maravill.foro_hub.security.utils.SecurityMapperService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test Operation Service")
class OperationServiceImplTest {

    @Mock
    private IModuleRepository moduleRepository;

    @Mock
    private IOperationRepository operationRepository;

    @Mock
    private IRoleRepository roleRepository;

    @Mock
    private SecurityMapperService mapper;

    @InjectMocks
    private OperationServiceImpl operationService;

    private Module module;
    private Operation operation;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        module = new Module(1L, "Module", "/api/module");
        operation = new Operation(1L, "GET Users", HttpMethod.GET, "/users", true, module);
        pageable = PageRequest.of(0, 5);
    }

    @Test
    @DisplayName("Test get public operations")
    void getPublicOperations() {
        when(operationRepository.findAllByPermitAllIsTrue()).thenReturn(List.of(operation));
        List<Operation> result = operationService.getPublicOperations();
        assertEquals(1, result.size());
        verify(operationRepository).findAllByPermitAllIsTrue();
    }

    @Test
    @DisplayName("Test find operations by module ID")
    void findOperationsByModuleId() {
        Page<Operation> page = new PageImpl<>(List.of(operation));
        ResponseOperation mapped = new ResponseOperation(1L, "GET Users", "GET", "/users", true, null);

        when(moduleRepository.existsById(1L)).thenReturn(true);
        when(operationRepository.findByModule_IdModule(1L, pageable)).thenReturn(page);
        when(mapper.mapToOperationResponse(operation)).thenReturn(mapped);

        ResponsePage<ResponseOperation> result = operationService.findOperationsByModuleId(1L, pageable);

        assertEquals(1, result.content().size());
    }

    @Test
    @DisplayName("Test get all operations")
    void getAllOperations() {
        Page<Operation> page = new PageImpl<>(List.of(operation));
        when(operationRepository.findAll(pageable)).thenReturn(page);
        when(mapper.mapToOperationResponse(any())).thenReturn(new ResponseOperation(1L, "GET Users", "GET", "/users", true, null));

        ResponsePage<ResponseOperation> result = operationService.getAllOperations(pageable);

        assertEquals(1, result.content().size());
    }

    @Test
    @DisplayName("Test find operation by ID")
    void findOperationById() {
        when(operationRepository.findById(1L)).thenReturn(Optional.of(operation));
        when(mapper.mapToOperationResponse(operation)).thenReturn(new ResponseOperation(1L, "GET Users", "GET", "/users", true, null));

        ResponseOperation result = operationService.findOperationById(1L);
        assertNotNull(result);
    }

    @Test
    @DisplayName("Test save new operation")
    void saveNewOperation() {
        RequestOperation request = new RequestOperation(null, "GET Users", "GET", "/users", true, new RequestModule(1L, "M", "/m"));
        Operation newOp = new Operation(null, "GET Users", HttpMethod.GET, "/users", true, module);

        when(mapper.mapToOperation(request)).thenReturn(newOp);
        when(operationRepository.save(newOp)).thenReturn(operation);
        when(mapper.mapToOperationResponse(operation)).thenReturn(new ResponseOperation(1L, "GET Users", "GET", "/users", true, null));

        ResponseOperation result = operationService.saveNewOperation(request);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Test update operation")
    void updateOperation() {
        RequestOperation request = new RequestOperation(1L, "UPDATED", "POST", "/updated", false, new RequestModule(1L, "M", "/m"));

        when(operationRepository.findById(1L)).thenReturn(Optional.of(operation));
        when(moduleRepository.findById(1L)).thenReturn(Optional.of(module));
        when(mapper.mapToOperationResponse(any())).thenReturn(new ResponseOperation(1L, "GET Users", "GET", "/users", true, null));

        ResponseOperation result = operationService.updateOperation(1L, request);
        assertNotNull(result);
        assertEquals("UPDATED", operation.getName());
        assertEquals("/updated", operation.getPath());
    }

    @Test
    @DisplayName("Test delete operation")
    void deleteOperation() {
        Role role = new Role();
        role.setOperations(new ArrayList<>(List.of(operation)));
        operation.setRoles(new ArrayList<>(List.of(role)));

        when(operationRepository.findById(1L)).thenReturn(Optional.of(operation));

        operationService.deleteOperation(1L);

        verify(operationRepository).delete(operation);
    }

    @Test
    @DisplayName("Test find operations by role ID")
    void findOperationsByRole() {
        Page<Operation> page = new PageImpl<>(List.of(operation));
        when(roleRepository.existsById(1L)).thenReturn(true);
        when(operationRepository.findByRoles_IdRole(1L, pageable)).thenReturn(page);
        when(mapper.mapToOperationResponse(any())).thenReturn(new ResponseOperation(1L, "GET Users", "GET", "/users", true, null));

        ResponsePage<ResponseOperation> result = operationService.findOperationsByRole(1L, pageable);

        assertEquals(1, result.content().size());
    }
}