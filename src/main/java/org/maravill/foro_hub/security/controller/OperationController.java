package org.maravill.foro_hub.security.controller;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.maravill.foro_hub.global.dto.ResponsePage;
import org.maravill.foro_hub.security.dto.RequestOperation;
import org.maravill.foro_hub.security.dto.ResponseOperation;
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
@RequestMapping("/operations")
@RequiredArgsConstructor
public class OperationController {

    private final IOperationService operationService;

    @GetMapping
    public ResponseEntity<ResponsePage<ResponseOperation>> findAll(@PageableDefault(size = 20 , sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok(operationService.getAllOperations(pageable));
    }

    @GetMapping("/{idOperation}")
    public ResponseEntity<ResponseOperation> findById(@PathVariable @Min(1) Long idOperation) {
        return ResponseEntity.ok(operationService.findOperationById(idOperation));
    }

    @PostMapping
    public ResponseEntity<ResponseOperation> create(
            @Valid @RequestBody RequestOperation requestOperation,
            UriComponentsBuilder uriBuilder) {

        ResponseOperation created = operationService.saveNewOperation(requestOperation);
        URI uri = uriBuilder.path("/operations/{idOperation}").buildAndExpand(created.idOperation()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{idOperation}")
    public ResponseEntity<ResponseOperation> update(
            @PathVariable @Min(1) Long idOperation,
            @Valid @RequestBody RequestOperation requestOperation) {

        return ResponseEntity.ok(operationService.updateOperation(idOperation, requestOperation));
    }

    @DeleteMapping("/{idOperation}")
    public ResponseEntity<Void> delete(@PathVariable @Min(1) Long idOperation) {
        operationService.deleteOperation(idOperation);
        return ResponseEntity.noContent().build();
    }
}
