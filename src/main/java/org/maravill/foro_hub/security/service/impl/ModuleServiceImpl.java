package org.maravill.foro_hub.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.maravill.foro_hub.global.dto.ResponsePage;
import org.maravill.foro_hub.security.dto.RequestModule;
import org.maravill.foro_hub.security.dto.ResponseModule;
import org.maravill.foro_hub.security.exception.SecurityDataNotFoundException;
import org.maravill.foro_hub.security.models.Module;
import org.maravill.foro_hub.security.models.Operation;
import org.maravill.foro_hub.security.models.Role;
import org.maravill.foro_hub.security.repository.IModuleRepository;
import org.maravill.foro_hub.security.service.IModuleService;
import org.maravill.foro_hub.security.utils.SecurityMapperService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements IModuleService {

    private final IModuleRepository moduleRepository;
    private final SecurityMapperService securityMapperService;

    @Override
    public ResponsePage<ResponseModule> getAllModules(Pageable pageable) {
        Page<Module> modules = moduleRepository.findAll(pageable);
        return ResponsePage.from(modules.map(securityMapperService::mapToModuleResponse));
    }

    @Override
    public ResponseModule findModuleById(Long idModule) {
        Module module = moduleRepository.findById(idModule).orElseThrow(
                () -> new SecurityDataNotFoundException("Module not found")
        );
        return securityMapperService.mapToModuleResponse(module);
    }

    @Transactional
    @Override
    public ResponseModule saveNewModule(RequestModule requestModule) {
        Module module = securityMapperService.mapToModule(requestModule);
        module.setIdModule(null);
        Module savedModule = moduleRepository.save(module);
        return securityMapperService.mapToModuleResponse(savedModule);
    }

    @Transactional
    @Override
    public ResponseModule updateModule(Long idModule, RequestModule requestModule) {
        Module existingModule = moduleRepository.findById(idModule)
                .orElseThrow(() -> new SecurityDataNotFoundException("Cannot update: Module with ID " + idModule + " not found."));
        existingModule.setModuleName(requestModule.moduleName());
        existingModule.setBasePath(requestModule.basePath());
        Module updatedModule = moduleRepository.save(existingModule);
        return securityMapperService.mapToModuleResponse(updatedModule);
    }

    @Transactional
    @Override
    public void deleteModule(Long idModule) {
        Module existingModule = moduleRepository.findById(idModule)
                .orElseThrow(() -> new SecurityDataNotFoundException("Cannot delete: Module with ID " + idModule + " not found."));

        for (Operation op: existingModule.getOperations()){
            List<Role> roles = op.getRoles();
            if (roles != null){
                for (Role role: op.getRoles()){
                    role.getOperations().remove(op);
                }
            }
        }
        moduleRepository.delete(existingModule);
    }

}
