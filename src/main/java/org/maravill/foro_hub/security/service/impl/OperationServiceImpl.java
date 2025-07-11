package org.maravill.foro_hub.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.maravill.foro_hub.global.dto.ResponsePage;
import org.maravill.foro_hub.security.dto.RequestOperation;
import org.maravill.foro_hub.security.dto.ResponseOperation;
import org.maravill.foro_hub.security.exception.SecurityDataNotFoundException;
import org.maravill.foro_hub.security.models.HttpMethod;
import org.maravill.foro_hub.security.models.Module;
import org.maravill.foro_hub.security.models.Operation;
import org.maravill.foro_hub.security.repository.IModuleRepository;
import org.maravill.foro_hub.security.repository.IOperationRepository;
import org.maravill.foro_hub.security.repository.IRoleRepository;
import org.maravill.foro_hub.security.service.IOperationService;
import org.maravill.foro_hub.security.utils.SecurityMapperService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements IOperationService {

    private final IModuleRepository moduleRepository;
    private final IOperationRepository operationRepository;
    private final IRoleRepository roleRepository;
    private final SecurityMapperService securityMapperService;

    @Override
    public List<Operation> getPublicOperations() {
        return operationRepository.findAllByPermitAllIsTrue();
    }

    @Transactional(readOnly = true)
    @Override
    public ResponsePage<ResponseOperation> findOperationsByModuleId(Long idModule, Pageable pageable) {
        if (!moduleRepository.existsById(idModule)) {
            throw new SecurityDataNotFoundException("Module not found");
        }
        Page<Operation> operations = operationRepository.findByModule_IdModule(idModule, pageable);
        return ResponsePage.from(operations.map(securityMapperService::mapToOperationResponse));
    }

    @Override
    public ResponsePage<ResponseOperation> getAllOperations(Pageable pageable) {
        Page<Operation> pageOperations = operationRepository.findAll(pageable);
        return ResponsePage.from(pageOperations.map(securityMapperService::mapToOperationResponse));
    }

    @Override
    public ResponseOperation findOperationById(Long idOperation) {
        Operation operation = operationRepository.findById(idOperation)
                .orElseThrow(() -> new SecurityDataNotFoundException("Operation not found"));
        return securityMapperService.mapToOperationResponse(operation);
    }

    @Transactional
    @Override
    public ResponseOperation saveNewOperation(RequestOperation requestOperation) {
        Operation operation = securityMapperService.mapToOperation(requestOperation);
        operation.setIdOperation(null);
        Operation saved = operationRepository.save(operation);
        return securityMapperService.mapToOperationResponse(saved);
    }

    @Transactional
    @Override
    public ResponseOperation updateOperation(Long idOperation, RequestOperation requestOperation) {
        Operation operation = operationRepository.findById(idOperation)
                .orElseThrow(() -> new SecurityDataNotFoundException("Operation not found"));

        operation.setName(requestOperation.name());
        operation.setPath(requestOperation.path());

        try {
            operation.setHttpMethod(HttpMethod.getHttpMethod(requestOperation.httpMethod()));
        } catch (IllegalArgumentException _) {
            throw new SecurityDataNotFoundException("Invalid HTTP method: " + requestOperation.httpMethod());
        }

        operation.setPermitAll(requestOperation.permitAll());

        Module module = moduleRepository.findById(requestOperation.module().idModule())
                .orElseThrow(() -> new SecurityDataNotFoundException("Module not found"));
        operation.setModule(module);

        return securityMapperService.mapToOperationResponse(operation);
    }

    @Transactional
    @Override
    public void deleteOperation(Long idOperation) {
        Operation operation = operationRepository.findById(idOperation)
                .orElseThrow(() -> new SecurityDataNotFoundException("Operation not found"));
        if (operation.getRoles() != null) {
            operation.getRoles().forEach(role -> role.getOperations().remove(operation));
        }
        operationRepository.delete(operation);
    }

    @Transactional(readOnly = true)
    @Override
    public ResponsePage<ResponseOperation> findOperationsByRole(Long idRole, Pageable pageable) {
        if (!roleRepository.existsById(idRole)) {
            throw new SecurityDataNotFoundException("Role does not exist");
        }
        Page<Operation> operations = operationRepository.findByRoles_IdRole(idRole, pageable);

        return ResponsePage.from(operations.map(securityMapperService::mapToOperationResponse));
    }


}
