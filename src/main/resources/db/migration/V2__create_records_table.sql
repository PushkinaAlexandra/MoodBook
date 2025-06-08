CREATE TABLE records (
    id SERIAL PRIMARY KEY,
    create_at TIMESTAMP WITHOUT TIME ZONE DEFAULT (now()),
    mood VARCHAR(255) NOT NULL,
    extra_mood VARCHAR(255),
    reason TEXT,
    advice_id BIGINT,
    CONSTRAINT fk_advice_id FOREIGN KEY (advice_id) REFERENCES advices(id)
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);