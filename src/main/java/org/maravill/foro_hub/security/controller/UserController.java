package org.maravill.foro_hub.security.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.maravill.foro_hub.global.dto.ResponsePage;
import org.maravill.foro_hub.security.dto.*;
import org.maravill.foro_hub.security.service.IUserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping
    public ResponseEntity<ResponseUser> registerUser(@RequestBody @Valid RequestUserRegister requestUserRegister) {
        ResponseUser responseUser = userService.registerOneUser(requestUserRegister);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @PutMapping("/change-password")
    public ResponseEntity<Void> changePassword(
            @RequestBody @Valid ChangePasswordRequest changePasswordRequest) {
        userService.changePassword(changePasswordRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/disable")
    public ResponseEntity<Void> disableUser(@RequestBody @Valid RequestDelete requestDelete) {
        userService.disableUser(requestDelete);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<ResponsePage<ResponseAdminUser>> getAllUsersByAdmin(@PageableDefault(size = 20,sort = {"username"}) Pageable pageable){
        return ResponseEntity.ok(userService.getAllUsersByAdmin(pageable));
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<ResponseAdminUser> getUserByIdByAdmin(@PathVariable @Min(1) Long idUser) {
        return ResponseEntity.ok(userService.getUserByIdByAdmin(idUser));
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<ResponseAdminUser> getUserByUsernameByAdmin(@PathVariable @NotBlank String username) {
        return ResponseEntity.ok(userService.findUserByUsernameByAdmin(username));
    }

    @PutMapping("/{idUser}/enable")
    public ResponseEntity<Void> enableUserByAdmin(@PathVariable @Min(1) Long idUser) {
        userService.enableUserByAdmin(idUser);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{idUser}/disable")
    public ResponseEntity<Void> disableUserByAdmin(@PathVariable @Min(1) Long idUser) {
        userService.disableUserByAdmin(idUser);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<Void> deleteUserByAdmin(@PathVariable @Min(1) Long idUser) {
        userService.deleteUserByAdmin(idUser);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idUser}/role")
    public ResponseEntity<Void> updateUserRoles(@PathVariable @Min(1) Long idUser,
                                                @RequestBody RequestRole role) {
        userService.updateUserRoleByAdmin(idUser,role);
        return ResponseEntity.ok().build();
    }
}