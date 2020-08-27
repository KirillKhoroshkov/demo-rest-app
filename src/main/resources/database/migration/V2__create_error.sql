
create table error (
    id serial primary key,
    message text not null,
    created timestamptz not null default current_timestamp
);