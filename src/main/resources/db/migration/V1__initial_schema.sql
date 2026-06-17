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

CREATE TABLE tasks
(
    id          UUID         NOT NULL,
    title       VARCHAR(100) NOT NULL,
    description TEXT,
    status      VARCHAR(50)  NOT NULL DEFAULT 'PENDING',
    priority    VARCHAR(50)  NOT NULL DEFAULT 'LOW',
    due_date    DATE,
    user_id     UUID         NOT NULL,
    created_at  TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at  TIMESTAMPTZ,
    CONSTRAINT pk_tasks PRIMARY KEY (id),
    CONSTRAINT fk_tasks_on_user FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE INDEX idx_tasks_user_active
    ON tasks (user_id) WHERE deleted_at IS NULL;