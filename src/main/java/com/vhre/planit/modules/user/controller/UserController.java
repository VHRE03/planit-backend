package com.vhre.planit.modules.user.controller;

import com.vhre.planit.modules.user.dto.UserRequestDTO;
import com.vhre.planit.modules.user.dto.UserResponseDTO;
import com.vhre.planit.modules.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(
        name = "Users",
        description = "Endpoints for managing users"
)
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/form", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> createViaJson(@Valid @RequestBody UserRequestDTO requestDTO) {
        return new ResponseEntity<>(userService.create(requestDTO), HttpStatus.CREATED);
    }

    @PostMapping(value = "/form", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<UserResponseDTO> createViaForm(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                            schema = @Schema(implementation = UserRequestDTO.class)
                    )
            )
            @ModelAttribute @Valid UserRequestDTO requestDTO
    ) {
        return new ResponseEntity<>(userService.create(requestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Fetches the full details of a user by its UUID.")
    @ApiResponse(responseCode = "200", description = "User found.")
    @ApiResponse(responseCode = "404", description = "User not found.")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping
    @Operation(summary = "List all users", description = "Retrieves a complete catalog of all registered users in the system.")
    @ApiResponse(responseCode = "200", description = "User list retrieved successfully.")
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing user", description = "Updates fields like full name, email, or provider information.")
    @ApiResponse(responseCode = "200", description = "User updated successfully.")
    @ApiResponse(responseCode = "404", description = "User not found.")
    @ApiResponse(responseCode = "409", description = "Update conflicts with an existing user (e.g., duplicate email or provider ID).")
    public ResponseEntity<UserResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody UserRequestDTO requestDTO) {
        return ResponseEntity.ok(userService.update(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Soft-delete a user", description = "Archives the user account, marking it as deleted while preserving data integrity.")
    @ApiResponse(responseCode = "204", description = "User successfully archived (No Content).")
    @ApiResponse(responseCode = "404", description = "User not found.")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}