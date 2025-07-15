package org.maravill.foro_hub.security.controller;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.maravill.foro_hub.global.dto.ResponsePage;
import org.maravill.foro_hub.security.dto.RequestOperation;
import org.maravill.foro_hub.security.dto.RequestRole;
import org.maravill.foro_hub.security.dto.ResponseOperation;
import org.maravill.foro_hub.security.dto.ResponseRole;
import org.maravill.foro_hub.security.service.IOperationService;
import org.maravill.foro_hub.security.service.IRoleService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Hidden
@Validated
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final IRoleService roleService;
    private final IOperationService operationService;

    @GetMapping
    public ResponseEntity<ResponsePage<ResponseRole>> getAllRoles(@PageableDefault(size = 20,sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok(roleService.getAllRoles(pageable));
    }

    @GetMapping("/{idRole}")
    public ResponseEntity<ResponseRole> getRoleById(@PathVariable @Min(1) Long idRole) {
        return ResponseEntity.ok(roleService.findRoleById(idRole));
    }

    @GetMapping("/{idRole}/operations")
    public ResponseEntity<ResponsePage<ResponseOperation>> getOperationsByRole(@PathVariable @Min(1) Long idRole, @PageableDefault(size = 20,sort = {"name"}) Pageable pageable){
        return ResponseEntity.ok(operationService.findOperationsByRole(idRole,pageable));
    }

    @PostMapping
    public ResponseEntity<ResponseRole> createRole(
            @Valid @RequestBody RequestRole requestRole,
            UriComponentsBuilder uriBuilder) {
        ResponseRole created = roleService.saveNewRole(requestRole);
        URI uri = uriBuilder.path("/roles/{idRole}").buildAndExpand(created.idRole()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{idRole}")
    public ResponseEntity<ResponseRole> updateRole(
            @PathVariable @Min(1) Long idRole,
            @Valid @RequestBody RequestRole requestRole) {
        return ResponseEntity.ok(roleService.updateRole(idRole, requestRole));
    }

    @PutMapping("/{idRole}/operations")
    public ResponseEntity<Void> addOperation(@PathVariable @Min(1) Long idRole,@RequestBody List<RequestOperation> operations){
        roleService.addOperations(idRole,operations);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idRole}")
    public ResponseEntity<Void> deleteRole(@PathVariable @Min(1) Long idRole) {
        roleService.deleteRole(idRole);
        return ResponseEntity.noContent().build();
    }
}

