CREATE TABLE answers (
    id_answer BIGINT NOT NULL AUTO_INCREMENT,
    message TEXT NOT NULL,
    topic_id BIGINT NOT NULL,
    created_at DATETIME NOT NULL,
    user_id BIGINT NOT NULL,
    is_solution BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (id_answer),
    CONSTRAINT fk_answer_topic
        FOREIGN KEY (topic_id)
        REFERENCES topics(id_topic)
        ON DELETE CASCADE,
    CONSTRAINT fk_answer_user
        FOREIGN KEY (user_id)
        REFERENCES users(id_user)
        ON DELETE CASCADE
);
