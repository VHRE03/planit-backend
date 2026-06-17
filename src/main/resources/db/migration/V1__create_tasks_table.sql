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