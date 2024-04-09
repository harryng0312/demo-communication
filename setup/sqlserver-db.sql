use test_db;
go

create table dbo.user_(
    id_               bigint             not null
        constraint auth_user_pkey
            primary key,
    created_date      datetime          not null,
    modified_date     datetime          not null,
    dob               date               not null,
    passwd            varchar(150)       not null,
    passwd_enc_method varchar(50)        not null,
    screenname        varchar(100)       not null,
    status__            smallint default 1 not null,
    username          varchar(75)        not null
);


create table dbo.asset(
    id_                bigint             not null
        constraint asset_pk
            primary key,
    name_              integer            not null,
    description        varchar(150),
    created_date       datetime          not null,
    modified_date      datetime          not null,
    resource_id        varchar(36)        not null
        constraint asset_resource_id_unq
            unique,
    resource_tree_path varchar(250)       not null
        constraint asset_resource_tree_path_unq
            unique,
    status_             smallint default 1 not null
);


create table dbo.organization
(
    id_           varchar(36)       not null
        constraint organization_pk
            primary key,
    name_         varchar(50)       not null,
    code_         varchar(50)       not null,
    created_date  datetime         not null,
    modified_date datetime         not null,
    status_        integer default 0 not null,
    description   varchar(150),
    parent_id     varchar(36)       not null,
    sort          integer default 0 not null,
    tree_path     varchar(260)      not null
        constraint organization_tree_path_unq
            unique,
    constraint organization_parent_id_sort_unq
        unique (sort, parent_id)
);


create table dbo.user_group
(
    id_            bigint                                    not null
        constraint user_group_pk
            primary key,
    name_          varchar(50)                               not null,
    description    varchar(150),
    status_         smallint    default 0                     not null,
    created_date   datetime                                 not null,
    modified_date  datetime                                 not null,
    org_id         varchar(36) default '' not null,
    parent_id      bigint      default 0                     not null,
    role_inherited bit     default 1                  not null
);

create table dbo.role_
(
    id_         varchar(50)  not null
        constraint role_pk
            primary key,
    name_       varchar(100) not null,
    description varchar(150),
    status_      smallint default 1
);


