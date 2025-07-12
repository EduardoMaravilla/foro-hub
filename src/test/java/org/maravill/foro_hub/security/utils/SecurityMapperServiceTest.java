package org.maravill.foro_hub.security.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.maravill.foro_hub.security.dto.*;
import org.maravill.foro_hub.security.models.*;
import org.maravill.foro_hub.security.models.Module;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Security Mapper Service")
class SecurityMapperServiceTest {

    private final SecurityMapperService mapper = new SecurityMapperService();

    @Test
    @DisplayName("Test RequestModule to Module")
    void mapToModule() {
        RequestModule dto = new RequestModule(1L, "Auth", "/auth");
        Module result = mapper.mapToModule(dto);

        assertEquals(dto.moduleName(), result.getModuleName());
        assertEquals(dto.basePath(), result.getBasePath());
        assertEquals(dto.idModule(), result.getIdModule());
    }

    @Test
    @DisplayName("Test Module to ResponseModule")
    void mapToModuleResponse() {
        Module module = new Module(2L, "User", "/users");
        ResponseModule result = mapper.mapToModuleResponse(module);

        assertEquals(module.getIdModule(), result.idModule());
        assertEquals(module.getModuleName(), result.moduleName());
        assertEquals(module.getBasePath(), result.basePath());
    }

    @Test
    @DisplayName("Test RequestOperation to Operation")
    void mapToOperation() {
        RequestModule requestModule = new RequestModule(3L, "Course", "/courses");
        RequestOperation dto = new RequestOperation(
                10L,
                "CREATE_COURSE",
                "POST",
                "/courses",
                false,
                requestModule
        );

        Operation result = mapper.mapToOperation(dto);

        assertEquals(dto.idOperation(), result.getIdOperation());
        assertEquals(dto.name(), result.getName());
        assertEquals(HttpMethod.POST, result.getHttpMethod());
        assertEquals(dto.path(), result.getPath());
        assertEquals(dto.permitAll(), result.getPermitAll());
        assertEquals(dto.module().moduleName(), result.getModule().getModuleName());
    }

    @Test
    @DisplayName("Test Operation to ResponseOperation")
    void mapToOperationResponse() {
        Module module = new Module(5L, "Role", "/roles");
        Operation operation = new Operation(20L, "DELETE_ROLE", HttpMethod.DELETE, "/roles/{id}", false, module);

        ResponseOperation result = mapper.mapToOperationResponse(operation);

        assertEquals(operation.getIdOperation(), result.idOperation());
        assertEquals("DELETE_ROLE", result.name());
        assertEquals("DELETE", result.httpMethod());
        assertEquals("/roles/{id}", result.path());
        assertEquals(false, result.permitAll());
        assertEquals(module.getModuleName(), result.module().moduleName());
    }

    @Test
    @DisplayName("Test Role to ResponseRole")
    void mapToRoleResponse() {
        Role role = new Role(99L, "ADMIN");
        ResponseRole result = mapper.mapToRoleResponse(role);

        assertEquals(99L, result.idRole());
        assertEquals("ADMIN", result.name());
    }

    @Test
    @DisplayName("Test RequestRole to Role")
    void mapToRole() {
        RequestRole dto = new RequestRole(50L, "USER");
        Role result = mapper.mapToRole(dto);

        assertEquals(dto.idRole(), result.getIdRole());
        assertEquals(dto.name(), result.getName());
    }

    @Test
    @DisplayName("Test User to ResponseUser")
    void mapToUserResponse() {
        Role role = new Role(1L, "USER");
        User user = new User();
        user.setUsername("john_doe");
        user.setEmail("john@example.com");
        user.setRole(role);

        ResponseUser result = mapper.mapToUserResponse(user);

        assertEquals("john_doe", result.username());
        assertEquals("john@example.com", result.email());
        assertEquals("USER", result.roleName());
    }

    @Test
    @DisplayName("Test User to ResponseAdminUser")
    void mapToAdminUserResponse() {
        Role role = new Role(2L, "ADMIN");
        User user = new User();
        user.setIdUser(100L);
        user.setUsername("admin_user");
        user.setEmail("admin@example.com");
        user.setRole(role);

        ResponseAdminUser result = mapper.mapToAdminUserResponse(user);

        assertEquals(100L, result.idUser());
        assertEquals("admin_user", result.username());
        assertEquals("admin@example.com", result.email());
        assertEquals("ADMIN", result.roleName());
    }
}