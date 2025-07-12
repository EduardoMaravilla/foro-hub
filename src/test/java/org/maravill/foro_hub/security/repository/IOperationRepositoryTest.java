package org.maravill.foro_hub.security.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.maravill.foro_hub.security.models.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@DisplayName("Test Operation Repository")
class IOperationRepositoryTest {

    @Autowired
    private IOperationRepository operationRepository;

    @Test
    @DisplayName("Test search public endpoint")
    void findAllByPermitAllIsTrue() {
        List<Operation> operations = operationRepository.findAllByPermitAllIsTrue();
        boolean hasLogin = operations.stream()
                .anyMatch(op -> "LOGIN".equals(op.getName()));
        assertTrue(hasLogin, "Debe existir una operación con nombre LOGIN");
        assertEquals(7, operations.size());
    }

    @Test
    @DisplayName("Test find operations by module id with pagination")
    void findByModule_IdModule() {
        Long testModuleId = 1L;
        Pageable pageable = PageRequest.of(0, 10);

        Page<Operation> page = operationRepository.findByModule_IdModule(testModuleId, pageable);
        assertFalse(page.isEmpty(), "Debe devolver operaciones para el módulo con id " + testModuleId);

        page.forEach(op -> assertEquals(testModuleId, op.getModule().getIdModule()));
    }

    @Test
    @DisplayName("Test find operations by role id with pagination")
    void findByRoles_IdRole() {
        Long testRoleId = 1L;
        Pageable pageable = PageRequest.of(0, 10);

        Page<Operation> page = operationRepository.findByRoles_IdRole(testRoleId, pageable);
        assertFalse(page.isEmpty(), "Debe devolver operaciones para el rol con id " + testRoleId);

        page.forEach(op -> {
            boolean hasRole = op.getRoles().stream()
                    .anyMatch(role -> role.getIdRole().equals(testRoleId));
            assertTrue(hasRole, "La operación debe tener el rol con id " + testRoleId);
        });
    }
}