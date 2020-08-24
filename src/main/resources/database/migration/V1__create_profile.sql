
create table profile (
    id serial primary key,
    name varchar(255) not null,
    email varchar(255) not null,
    age int not null,
    created timestamptz not null default current_timestamp,
    constraint age_constraint check ( age > 0 )
);

create unique index case_insensitive_unique_email_in_profile on profile (lower(email));