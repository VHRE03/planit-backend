CREATE TABLE users
(
    id            UUID         NOT NULL,
    full_name     VARCHAR(200) NOT NULL,
    username      VARCHAR(50),
    email         VARCHAR(100) NOT NULL,
    password_hash VARCHAR(255),
    provider      VARCHAR(20)  NOT NULL DEFAULT 'LOCAL',
    provider_id   VARCHAR(255),
    created_at    TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at    TIMESTAMPTZ,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE UNIQUE INDEX idx_users_email_active
    ON users (email) WHERE deleted_at IS NULL;

CREATE UNIQUE INDEX idx_users_provider_provider_id
    ON users (provider, provider_id) WHERE provider_id IS NOT NULL;