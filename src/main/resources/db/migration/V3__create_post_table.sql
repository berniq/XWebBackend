CREATE TABLE IF NOT EXISTS post
(
    id      UUID DEFAULT gen_random_uuid() NOT NULL
        CONSTRAINT post_pk
            PRIMARY KEY,
    content VARCHAR(1024)                  NOT NULL,
    user_id  UUID                           NOT NULL
        CONSTRAINT table_name_customer_id_fk
            REFERENCES "customer"
);