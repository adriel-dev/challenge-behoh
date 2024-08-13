CREATE TABLE tb_user (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE tb_event (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    capacity INT NOT NULL,
    start_date_time TIMESTAMP NOT NULL,
    end_date_time TIMESTAMP NOT NULL
);

CREATE TABLE tb_event_user (
    event_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    check_in_date_time TIMESTAMP,
    reservation_date_time TIMESTAMP,
    confirmed BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (event_id, user_id),
    CONSTRAINT fk_event FOREIGN KEY (event_id) REFERENCES tb_event(id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES tb_user(id)
);
