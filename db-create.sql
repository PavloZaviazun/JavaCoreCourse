create table p8db.teams
(
    id   int auto_increment,
    name varchar(10) not null,
    constraint teams_id_uindex
        unique (id)
);

alter table p8db.teams
    add primary key (id);

create table p8db.users
(
    id    int auto_increment,
    login varchar(10) not null,
    constraint users_id_uindex
        unique (id),
    constraint users_login_uindex
        unique (login)
);

alter table p8db.users
    add primary key (id);

create table p8db.users_teams
(
    user_id int null,
    team_id int null,
    constraint users_teams_teams_id_fk
        foreign key (team_id) references p8db.teams (id)
            on delete cascade,
    constraint users_teams_users_id_fk
        foreign key (user_id) references p8db.users (id)
            on delete cascade
);