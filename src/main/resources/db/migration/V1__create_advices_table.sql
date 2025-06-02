CREATE TABLE advices (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    mood VARCHAR(255)
);