create table public.user_
(
    id_               bigint             not null
        constraint auth_user_pkey
            primary key,
    created_date      timestamp          not null,
    modified_date     timestamp          not null,
    dob               date               not null,
    passwd            varchar(150)       not null,
    passwd_enc_method varchar(50)        not null,
    screenname        varchar(100)       not null,
    status            smallint default 1 not null,
    username          varchar(75)        not null
);

alter table public.user_
    owner to test_db;

create table public.asset
(
    id_                bigint             not null
        constraint asset_pk
            primary key,
    name_              integer            not null,
    description        varchar(150),
    created_date       timestamp          not null,
    modified_date      timestamp          not null,
    resource_id        varchar(36)        not null
        constraint asset_resource_id_unq
            unique,
    resource_tree_path varchar(250)       not null
        constraint asset_resource_tree_path_unq
            unique,
    status             smallint default 1 not null
);

alter table public.asset
    owner to test_db;

create table public.organization
(
    id_           varchar(36)       not null
        constraint organization_pk
            primary key,
    name_         varchar(50)       not null,
    code_         varchar(50)       not null,
    created_date  timestamp         not null,
    modified_date timestamp         not null,
    status        integer default 0 not null,
    description   varchar(150),
    parent_id     varchar(36)       not null,
    sort          integer default 0 not null,
    tree_path     varchar(260)      not null
        constraint organization_tree_path_unq
            unique,
    constraint organization_parent_id_sort_unq
        unique (sort, parent_id)
);

alter table public.organization
    owner to test_db;

create table public.user_group
(
    id_            bigint                                    not null
        constraint user_group_pk
            primary key,
    name_          varchar(50)                               not null,
    description    varchar(150),
    status         smallint    default 0                     not null,
    created_date   timestamp                                 not null,
    modified_date  timestamp                                 not null,
    org_id         varchar(36) default ''::character varying not null,
    parent_id      bigint      default 0                     not null,
    role_inherited boolean     default true                  not null
);

alter table public.user_group
    owner to test_db;

create table public.role_
(
    id_         varchar(50)  not null
        constraint role_pk
            primary key,
    name_       varchar(100) not null,
    description varchar(150),
    status      smallint default 1
);

alter table public.role_
    owner to test_db;



