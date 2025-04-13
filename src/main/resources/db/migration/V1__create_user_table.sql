create table public."user"
(
    id         uuid default gen_random_uuid() not null
        constraint user_pk
            primary key,
    first_name varchar                        not null,
    email      varchar                        not null
);
