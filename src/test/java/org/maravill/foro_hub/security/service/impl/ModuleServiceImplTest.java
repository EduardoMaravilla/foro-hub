package org.maravill.foro_hub.security.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.maravill.foro_hub.global.dto.ResponsePage;
import org.maravill.foro_hub.security.dto.RequestModule;
import org.maravill.foro_hub.security.dto.ResponseModule;
import org.maravill.foro_hub.security.exception.SecurityDataNotFoundException;
import org.maravill.foro_hub.security.models.Module;
import org.maravill.foro_hub.security.models.Operation;
import org.maravill.foro_hub.security.models.Role;
import org.maravill.foro_hub.security.repository.IModuleRepository;
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
@DisplayName("Test Module Service")
class ModuleServiceImplTest {

    @Mock
    private IModuleRepository moduleRepository;

    @Mock
    private SecurityMapperService securityMapperService;

    @InjectMocks
    private ModuleServiceImpl moduleService;

    private Module module;
    private RequestModule requestModule;
    private ResponseModule responseModule;

    @BeforeEach
    void setUp() {
        module = new Module(1L, "Auth", "/auth");
        requestModule = new RequestModule(1L, "Auth", "/auth");
        responseModule = new ResponseModule(1L, "Auth", "/auth");
    }

    @Test
    @DisplayName("Test get all modules paginated")
    void getAllModules() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Module> modulePage = new PageImpl<>(List.of(module));
        when(moduleRepository.findAll(pageable)).thenReturn(modulePage);
        when(securityMapperService.mapToModuleResponse(module)).thenReturn(responseModule);

        ResponsePage<ResponseModule> result = moduleService.getAllModules(pageable);

        assertNotNull(result);
        assertEquals(1, result.content().size());
        verify(moduleRepository).findAll(pageable);
    }

    @Test
    @DisplayName("Test find module by id")
    void findModuleById() {
        when(moduleRepository.findById(1L)).thenReturn(Optional.of(module));
        when(securityMapperService.mapToModuleResponse(module)).thenReturn(responseModule);

        ResponseModule result = moduleService.findModuleById(1L);

        assertNotNull(result);
        assertEquals("Auth", result.moduleName());
        verify(moduleRepository).findById(1L);
    }

    @Test
    @DisplayName("Test find module by id not found")
    void findModuleByIdNotFound() {
        when(moduleRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(SecurityDataNotFoundException.class, () -> moduleService.findModuleById(1L));
    }

    @Test
    @DisplayName("Test save new module")
    void saveNewModule() {
        Module toSave = new Module(null, "Auth", "/auth");
        when(securityMapperService.mapToModule(requestModule)).thenReturn(toSave);
        when(moduleRepository.save(toSave)).thenReturn(module);
        when(securityMapperService.mapToModuleResponse(module)).thenReturn(responseModule);

        ResponseModule result = moduleService.saveNewModule(requestModule);

        assertEquals("Auth", result.moduleName());
        verify(moduleRepository).save(toSave);
    }

    @Test
    @DisplayName("Test update existing module")
    void updateModule() {
        when(moduleRepository.findById(1L)).thenReturn(Optional.of(module));
        when(moduleRepository.save(any(Module.class))).thenReturn(module);
        when(securityMapperService.mapToModuleResponse(any(Module.class))).thenReturn(responseModule);

        ResponseModule result = moduleService.updateModule(1L, requestModule);

        assertEquals("Auth", result.moduleName());
        verify(moduleRepository).save(module);
    }

    @Test
    @DisplayName("Test update module not found")
    void updateModuleNotFound() {
        when(moduleRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(SecurityDataNotFoundException.class, () -> moduleService.updateModule(1L, requestModule));
    }

    @Test
    @DisplayName("Test delete module")
    void deleteModule() {
        Operation operation = new Operation();
        Role role = new Role();
        role.setOperations(new ArrayList<>(List.of(operation)));
        operation.setRoles(new ArrayList<>(List.of(role)));
        module.setOperations(new ArrayList<>(List.of(operation)));

        when(moduleRepository.findById(1L)).thenReturn(Optional.of(module));

        moduleService.deleteModule(1L);

        verify(moduleRepository).delete(module);
    }

    @Test
    @DisplayName("Test delete module not found")
    void deleteModuleNotFound() {
        when(moduleRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(SecurityDataNotFoundException.class, () -> moduleService.deleteModule(1L));
    }
}