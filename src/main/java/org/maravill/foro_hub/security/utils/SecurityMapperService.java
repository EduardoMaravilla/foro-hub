package org.maravill.foro_hub.security.utils;

import org.maravill.foro_hub.security.dto.*;
import org.maravill.foro_hub.security.models.*;
import org.maravill.foro_hub.security.models.Module;
import org.springframework.stereotype.Service;

@Service
public class SecurityMapperService {

    public Module mapToModule(RequestModule requestModule) {
        return new Module(requestModule.idModule(), requestModule.moduleName(), requestModule.basePath());
    }

    public ResponseModule mapToModuleResponse(Module module) {
        return new ResponseModule(module.getIdModule(), module.getModuleName(), module.getBasePath());
    }

    public Operation mapToOperation(RequestOperation requestOperation) {
        return new Operation(
                requestOperation.idOperation(),
                requestOperation.name(),
                HttpMethod.getHttpMethod(requestOperation.httpMethod()),
                requestOperation.path(),
                requestOperation.permitAll(),
                mapToModule(requestOperation.module())
        );
    }

    public ResponseOperation mapToOperationResponse(Operation operation) {
        return new ResponseOperation(
                operation.getIdOperation(),
                operation.getName(),
                operation.getHttpMethod().name(),
                operation.getPath(),
                operation.getPermitAll(),
                mapToModuleResponse(operation.getModule()));
    }

    public ResponseRole mapToRoleResponse(Role role) {
        return new ResponseRole(role.getIdRole(), role.getName());
    }

    public Role mapToRole(RequestRole requestRole) {
        return new Role(requestRole.idRole(), requestRole.name());
    }

    public ResponseUser mapToUserResponse(User save) {
        return new ResponseUser(save.getUsername(), save.getEmail(),save.getRole().getName());
    }

    public ResponseAdminUser mapToAdminUserResponse(User user) {
        return new ResponseAdminUser(user.getIdUser(), user.getUsername(), user.getEmail(), user.getRole().getName());
    }
}
