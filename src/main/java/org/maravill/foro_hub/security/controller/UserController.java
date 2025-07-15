package org.maravill.foro_hub.security.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.maravill.foro_hub.global.dto.ResponsePage;
import org.maravill.foro_hub.security.config.docs.DefaultSecurityApiResponses;
import org.maravill.foro_hub.security.config.docs.HttpStatusCode;
import org.maravill.foro_hub.security.dto.*;
import org.maravill.foro_hub.security.service.IUserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User Controller",
        description = "Endpoints for user registration and management")
public class UserController implements DefaultSecurityApiResponses {

    private final IUserService userService;

    @PostMapping
    @Operation(
            summary = "Register a new user",
            description = "Registers a new user with basic info and assigns a default role.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RequestUserRegister.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = HttpStatusCode.CREATED,
                            description = HttpStatusCode.CREATED_VALUE,
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseUser.class)
                            )
                    )
            }
    )
    public ResponseEntity<ResponseUser> registerUser(@RequestBody @Valid RequestUserRegister requestUserRegister) {
        ResponseUser responseUser = userService.registerOneUser(requestUserRegister);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @PutMapping("/change-password")
    @Operation(
            summary = "Change password",
            description = "Allows an authenticated user to update their password.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ChangePasswordRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = HttpStatusCode.OK,
                            description = HttpStatusCode.OK_VALUE
                    )
            },
            security = {@SecurityRequirement(name = "security-token")}
    )
    public ResponseEntity<Void> changePassword(
            @RequestBody @Valid ChangePasswordRequest changePasswordRequest) {
        userService.changePassword(changePasswordRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/disable")
    @Operation(
            summary = "Disable user account",
            description = "Disables a user account by ID. Requires authentication and appropriate role.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RequestDelete.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = HttpStatusCode.OK,
                            description = HttpStatusCode.OK_VALUE
                    )
            },
            security = {@SecurityRequirement(name = "security-token")}
    )
    public ResponseEntity<Void> disableUser(@RequestBody @Valid RequestDelete requestDelete) {
        userService.disableUser(requestDelete);
        return ResponseEntity.ok().build();
    }

    @Hidden
    @GetMapping
    public ResponseEntity<ResponsePage<ResponseAdminUser>> getAllUsersByAdmin(@PageableDefault(size = 20, sort = {"username"}) Pageable pageable) {
        return ResponseEntity.ok(userService.getAllUsersByAdmin(pageable));
    }

    @Hidden
    @GetMapping("/{idUser}")
    public ResponseEntity<ResponseAdminUser> getUserByIdByAdmin(@PathVariable @Min(1) Long idUser) {
        return ResponseEntity.ok(userService.getUserByIdByAdmin(idUser));
    }

    @Hidden
    @GetMapping("/by-username/{username}")
    public ResponseEntity<ResponseAdminUser> getUserByUsernameByAdmin(@PathVariable @NotBlank String username) {
        return ResponseEntity.ok(userService.findUserByUsernameByAdmin(username));
    }

    @Hidden
    @PutMapping("/{idUser}/enable")
    public ResponseEntity<Void> enableUserByAdmin(@PathVariable @Min(1) Long idUser) {
        userService.enableUserByAdmin(idUser);
        return ResponseEntity.ok().build();
    }

    @Hidden
    @PutMapping("/{idUser}/disable")
    public ResponseEntity<Void> disableUserByAdmin(@PathVariable @Min(1) Long idUser) {
        userService.disableUserByAdmin(idUser);
        return ResponseEntity.ok().build();
    }

    @Hidden
    @DeleteMapping("/{idUser}")
    public ResponseEntity<Void> deleteUserByAdmin(@PathVariable @Min(1) Long idUser) {
        userService.deleteUserByAdmin(idUser);
        return ResponseEntity.noContent().build();
    }

    @Hidden
    @PutMapping("/{idUser}/role")
    public ResponseEntity<Void> updateUserRoles(@PathVariable @Min(1) Long idUser,
                                                @RequestBody RequestRole role) {
        userService.updateUserRoleByAdmin(idUser, role);
        return ResponseEntity.ok().build();
    }
}