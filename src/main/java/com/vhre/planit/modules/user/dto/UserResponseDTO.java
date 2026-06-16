package com.vhre.planit.modules.user.dto;

import com.vhre.planit.core.utils.auditable_dto.AuditableResponseDTO;
import com.vhre.planit.modules.user.entity.enums.Provider;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Data Transfer Object representing a user in API responses. Contains all user details including provider information.")
public class UserResponseDTO extends AuditableResponseDTO {

    @Schema(
            description = "Unique identifier of the user.",
            example = "550e8400-e29b-41d4-a716-446655440000"
    )
    private UUID id;

    @Schema(
            description = "Full name of the user",
            example = "John Doe"
    )
    private String fullName;

    @Schema(
            description = "Username for local accounts. May be null for OAuth2 users.",
            example = "johndoe"
    )
    private String username;

    @Schema(
            description = "Email address of the user. Unique across all providers.",
            example = "john.doe@example.com"
    )
    private String email;

    @Schema(
            description = "Hashed password for local accounts. Always empty or null in responses for security reasons.",
            example = "Str0ngP@ss!"
    )
    private String passwordHash;

    @Schema(
            description = "Authentication provider for this user account.",
            example = "LOCAL",
            allowableValues = {"LOCAL", "GOOGLE", "GITHUB"}
    )
    private Provider provider;

    @Schema(
            description = "Unique identifier from the external OAuth2 provider (e.g., Google 'sub', GitHub 'id'). " +
                    "Null for local accounts.",
            example = "1234567890"
    )
    private String providerId;
}
