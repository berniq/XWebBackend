DROP TABLE public."user";

-- Recreate table in updated state
CREATE TABLE IF NOT EXISTS customer
(
    id               UUID DEFAULT gen_random_uuid() NOT NULL
        CONSTRAINT user_pk
            PRIMARY KEY UNIQUE,
    handle           VARCHAR(64)                    NOT NULL UNIQUE,
    username         VARCHAR(64)                    NOT NULL,
    bio              TEXT,
    first_name       VARCHAR(64)                    NOT NULL,
    last_name        VARCHAR(64)                    NOT NULL,
    date_of_birth    DATE                           NOT NULL,
    email            VARCHAR(256)                   NOT NULL UNIQUE,
    additional_links VARCHAR(256)[],
    location         VARCHAR(255)                   NOT NULL
);
