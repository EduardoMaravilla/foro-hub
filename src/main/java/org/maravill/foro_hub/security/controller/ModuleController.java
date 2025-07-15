package org.maravill.foro_hub.security.controller;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.maravill.foro_hub.global.dto.ResponsePage;
import org.maravill.foro_hub.security.dto.RequestModule;
import org.maravill.foro_hub.security.dto.ResponseModule;
import org.maravill.foro_hub.security.dto.ResponseOperation;
import org.maravill.foro_hub.security.service.IModuleService;
import org.maravill.foro_hub.security.service.IOperationService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Hidden
@Validated
@RestController
@RequestMapping("/modules")
@RequiredArgsConstructor
public class ModuleController {

    private final IModuleService moduleService;
    private final IOperationService operationService;

    @GetMapping
    public ResponseEntity<ResponsePage<ResponseModule>> findAll(@PageableDefault(size = 20,sort = {"moduleName"}) Pageable pageable) {
        return ResponseEntity.ok(moduleService.getAllModules(pageable));
    }

    @GetMapping("/{idModule}")
    public ResponseEntity<ResponseModule> findById(@PathVariable @Min(1) Long idModule) {
        return ResponseEntity.ok(moduleService.findModuleById(idModule));
    }

    @GetMapping("/{idModule}/operations")
    public ResponseEntity<ResponsePage<ResponseOperation>> findOperationsByModuleId(@PathVariable @Min(1) Long idModule,@PageableDefault(size = 20,sort = {"name"}) Pageable pageable){
        return ResponseEntity.ok(operationService.findOperationsByModuleId(idModule,pageable));
    }

    @PostMapping
    public ResponseEntity<ResponseModule> create(
            @Valid @RequestBody RequestModule requestModule,
            UriComponentsBuilder uriBuilder) {

        ResponseModule created = moduleService.saveNewModule(requestModule);
        URI uri = uriBuilder.path("/modules/{idModule}").buildAndExpand(created.idModule()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{idModule}")
    public ResponseEntity<ResponseModule> update(
            @PathVariable @Min(1) Long idModule,
            @Valid @RequestBody RequestModule requestModule) {

        return ResponseEntity.ok(moduleService.updateModule(idModule, requestModule));
    }

    @DeleteMapping("/{idModule}")
    public ResponseEntity<Void> delete(@PathVariable @Min(1) Long idModule) {
        moduleService.deleteModule(idModule);
        return ResponseEntity.noContent().build();
    }
}

