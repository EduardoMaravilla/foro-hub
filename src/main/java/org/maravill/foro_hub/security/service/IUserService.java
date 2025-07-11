package org.maravill.foro_hub.security.service;

import org.maravill.foro_hub.global.dto.ResponsePage;
import org.maravill.foro_hub.security.dto.*;
import org.maravill.foro_hub.security.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface IUserService {

    User findUserByUsername(String username);

    ResponseUser registerOneUser(RequestUserRegister requestUserRegister);

    void changePassword(ChangePasswordRequest changePasswordRequest);

    void disableUser( RequestDelete requestDelete);

    ResponsePage<ResponseAdminUser> getAllUsersByAdmin(Pageable pageable);

    ResponseAdminUser getUserByIdByAdmin(Long idUser);

    ResponseAdminUser findUserByUsernameByAdmin(String username);

    void enableUserByAdmin(Long idUser);

    void disableUserByAdmin(Long idUser);

    void deleteUserByAdmin(Long idUser);

    void updateUserRoleByAdmin(Long idUser, RequestRole role);

    String extractUsernameOfSecurityContext();

    Collection<? extends GrantedAuthority> findAuthoritiesByUser(User user);
}
