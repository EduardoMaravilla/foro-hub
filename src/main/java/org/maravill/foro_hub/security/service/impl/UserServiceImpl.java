package org.maravill.foro_hub.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.maravill.foro_hub.global.dto.ResponsePage;
import org.maravill.foro_hub.security.dto.*;
import org.maravill.foro_hub.security.exception.SecurityDataNotFoundException;
import org.maravill.foro_hub.security.exception.SecurityInvalidDataException;
import org.maravill.foro_hub.security.exception.SecurityInvalidPasswordException;
import org.maravill.foro_hub.security.models.Role;
import org.maravill.foro_hub.security.models.User;
import org.maravill.foro_hub.security.repository.IUserRepository;
import org.maravill.foro_hub.security.service.IRoleService;
import org.maravill.foro_hub.security.service.IUserService;
import org.maravill.foro_hub.security.utils.SecurityMapperService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IRoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final SecurityMapperService securityMapperService;
    private final AuthenticationManager authenticationManager;

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new SecurityDataNotFoundException("User not found with username: " + username));
    }

    @Transactional
    @Override
    public ResponseUser registerOneUser(RequestUserRegister requestUserRegister) {
        validatePassword(requestUserRegister.password(), requestUserRegister.repeatPassword());
        if (userRepository.existsByEmail(requestUserRegister.email())) {
            throw new SecurityInvalidDataException("Email already registered");
        }
        if (userRepository.existsByUsername(requestUserRegister.username())) {
            throw new SecurityInvalidDataException("Username already registered");
        }
        User user = new User(
                null,
                requestUserRegister.username(),
                passwordEncoder.encode(requestUserRegister.password()),
                requestUserRegister.email()
        );
        user.setEnabled(true);
        Role role = roleService.findDefaultRole();
        user.setRole(role);
        User savedUser = userRepository.save(user);
        return securityMapperService.mapToUserResponse(savedUser);
    }

    @Transactional
    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) {

        String username = extractUsernameOfSecurityContext();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new SecurityDataNotFoundException("User not found when trying to change password"));
        authenticateCurrentUser(user.getUsername(), changePasswordRequest.oldPassword());
        validatePassword(changePasswordRequest.password(), changePasswordRequest.repeatPassword());
        user.setPassword(passwordEncoder.encode(changePasswordRequest.password()));
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void disableUser(RequestDelete requestDelete) {
        String username = extractUsernameOfSecurityContext();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new SecurityDataNotFoundException("User not found when trying to disable account"));
        authenticateCurrentUser(user.getUsername(), requestDelete.password());
        user.setEnabled(false);
        userRepository.save(user);
    }

    @Override
    public ResponsePage<ResponseAdminUser> getAllUsersByAdmin(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return ResponsePage.from(users.map(securityMapperService::mapToAdminUserResponse));
    }

    @Override
    public ResponseAdminUser getUserByIdByAdmin(Long idUser) {
        return userRepository.findById(idUser).map(securityMapperService::mapToAdminUserResponse)
                .orElseThrow(() -> new SecurityDataNotFoundException("User not found when trying to disable account"));
    }

    @Override
    public ResponseAdminUser findUserByUsernameByAdmin(String username) {
        return securityMapperService.mapToAdminUserResponse(findUserByUsername(username));
    }

    @Transactional
    @Override
    public void enableUserByAdmin(Long idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new SecurityDataNotFoundException("User not found"));
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void disableUserByAdmin(Long idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new SecurityDataNotFoundException("User not found"));
        user.setEnabled(false);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUserByAdmin(Long idUser) {
        if (!userRepository.existsById(idUser)){
            throw new SecurityDataNotFoundException("User not found");
        }
        userRepository.deleteById(idUser);
    }

    @Transactional
    @Override
    public void updateUserRoleByAdmin(Long idUser, RequestRole role) {
        Role updateRole = roleService.findEntityRoleById(role.idRole());
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new SecurityDataNotFoundException("User not found"));
        user.setRole(updateRole);
        userRepository.save(user);
    }

    private void validatePassword(String password, String repeatPassword) {
        if (!password.equals(repeatPassword)) {
            throw new SecurityInvalidPasswordException("Passwords do not match");
        }
    }

    @Override
    public String extractUsernameOfSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof User user) {
            return user.getUsername();
        } else if (principal instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        } else if (principal instanceof String username) {
            return username;
        }
        throw new IllegalStateException("Cannot extract username from security context");
    }

    @Transactional
    @Override
    public Collection<? extends GrantedAuthority> findAuthoritiesByUser(User user) {
        try {
            return userRepository.findByUsername(user.getUsername())
                    .orElseThrow(() -> new SecurityDataNotFoundException("User not found")).getAuthorities();
        }catch (Exception _){
            throw new IllegalStateException("Cannot extract username from security context");
        }
    }

    private void authenticateCurrentUser(String username, String password) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authentication);
    }
}
