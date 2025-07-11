CREATE TABLE topics (
    id_topic BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    created_at DATETIME NOT NULL,
    status_topic VARCHAR(50),
    user_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    PRIMARY KEY (id_topic),
    CONSTRAINT fk_topic_user
        FOREIGN KEY (user_id)
        REFERENCES users(id_user)
        ON DELETE CASCADE,
    CONSTRAINT fk_topic_course
        FOREIGN KEY (course_id)
        REFERENCES courses(id_course)
        ON DELETE CASCADE
);
