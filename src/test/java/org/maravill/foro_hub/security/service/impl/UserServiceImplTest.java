package org.maravill.foro_hub.security.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.maravill.foro_hub.global.dto.ResponsePage;
import org.maravill.foro_hub.security.dto.*;
import org.maravill.foro_hub.security.models.Role;
import org.maravill.foro_hub.security.models.User;
import org.maravill.foro_hub.security.repository.IUserRepository;
import org.maravill.foro_hub.security.service.IRoleService;
import org.maravill.foro_hub.security.utils.SecurityMapperService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test User Service")
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private IUserRepository userRepository;
    @Mock
    private IRoleService roleService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private SecurityMapperService securityMapperService;
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private Pageable pageable;

    private final User user = new User(1L, "eduardo", "pass", "edu@example.com");
    private final Role role = new Role(1L, "USER");
    private final RequestUserRegister register = new RequestUserRegister("eduardo", "pass", "pass", "edu@example.com");

    @Test
    @DisplayName("Test register user")
    void registerUserTest() {
        when(userRepository.existsByEmail(register.email())).thenReturn(false);
        when(userRepository.existsByUsername(register.username())).thenReturn(false);
        when(roleService.findDefaultRole()).thenReturn(role);
        when(passwordEncoder.encode("pass")).thenReturn("encoded");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(securityMapperService.mapToUserResponse(any(User.class)))
                .thenReturn(new ResponseUser("eduardo", "edu@example.com", "USER"));

        ResponseUser response = userService.registerOneUser(register);

        assertEquals("eduardo", response.username());
        verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("Test change password")
    void changePasswordTest() {
        ChangePasswordRequest req = new ChangePasswordRequest("old", "new", "new");
        SecurityContext context = mock(SecurityContext.class);
        SecurityContextHolder.setContext(context);
        when(context.getAuthentication()).thenReturn(new UsernamePasswordAuthenticationToken("eduardo", null));
        when(userRepository.findByUsername("eduardo")).thenReturn(Optional.of(user));

        when(passwordEncoder.encode("new")).thenReturn("encoded");

        userService.changePassword(req);

        verify(userRepository).save(user);
        assertEquals("encoded", user.getPassword());
    }

    @Test
    @DisplayName("Test disable user")
    void disableUserTest() {
        RequestDelete req = new RequestDelete("old");
        SecurityContext context = mock(SecurityContext.class);
        SecurityContextHolder.setContext(context);
        when(context.getAuthentication()).thenReturn(new UsernamePasswordAuthenticationToken("eduardo", null));
        when(userRepository.findByUsername("eduardo")).thenReturn(Optional.of(user));

        userService.disableUser(req);

        assertFalse(user.isEnabled());
        verify(userRepository).save(user);
    }

    @Test
    @DisplayName("Test get all users by admin")
    void getAllUsersByAdminTest() {
        Page<User> page = new PageImpl<>(List.of(user));
        when(userRepository.findAll(pageable)).thenReturn(page);
        when(securityMapperService.mapToAdminUserResponse(user))
                .thenReturn(new ResponseAdminUser(1L, "eduardo", "edu@example.com", "USER"));

        ResponsePage<ResponseAdminUser> response = userService.getAllUsersByAdmin(pageable);
        assertEquals(1, response.content().size());
    }

    @Test
    @DisplayName("Test enable user by admin")
    void enableUserByAdminTest() {
        user.setEnabled(false);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.enableUserByAdmin(1L);

        assertTrue(user.isEnabled());
        verify(userRepository).save(user);
    }

    @Test
    @DisplayName("Test delete user by admin")
    void deleteUserByAdminTest() {
        when(userRepository.existsById(1L)).thenReturn(true);

        userService.deleteUserByAdmin(1L);

        verify(userRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Test update user role by admin")
    void updateUserRoleByAdminTest() {
        RequestRole requestRole = new RequestRole(2L, "ADMIN");
        Role newRole = new Role(2L, "ADMIN");
        when(roleService.findEntityRoleById(2L)).thenReturn(newRole);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.updateUserRoleByAdmin(1L, requestRole);

        assertEquals("ADMIN", user.getRole().getName());
        verify(userRepository).save(user);
    }
}
