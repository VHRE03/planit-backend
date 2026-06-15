package com.vhre.planit.modules.user.dto;

import com.vhre.planit.modules.user.entity.enums.Provider;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Data Transfer Object for creating or registering a new user. " +
                "Supports both local accounts and OAuth2 provider-based accounts."
)
public record UserRequestDTO(
        @Schema(
                description = "Full name of the user.",
                example = "John Doe",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String fullName,

        @Schema(
                description = "Optional username for local accounts. If omitted, the email will be used as the login identifier.",
                example = "johndoe",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED
        )
        String username,

        @Schema(
                description = "Email address of the user. Must be unique across all providers.",
                example = "john.doe@example.com",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String email,

        @Schema(
                description = "Raw password for local accounts. It will be hashed before storage. Leave this null for OAuth2 accounts.",
                example = "Str0ngP@ss!",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String passwordHash,

        @Schema(
                description = "Authentication provider. Defaults to LOCAL if not specified.",
                example = "LOCAL",
                allowableValues = {"LOCAL", "GOOGLE", "GITHUB"},
                defaultValue = "LOCAL"
        )
        Provider provider,

        @Schema(
                description = "Unique identifier from the external OAuth2 provider (e.g., Google 'sub', GitHub 'id'). Required only when provider is not LOCAL.",
                example = "1234567890"
        )
        String providerId
) {
}
