create table public.user_
(
    id_               bigint                                     not null
        constraint auth_user_pkey
            primary key,
    created_date      timestamp                                  not null,
    modified_date     timestamp                                  not null,
    dob               date                                       not null,
    passwd            varchar(150)                               not null,
    passwd_enc_method varchar(50)                                not null,
    screenname        varchar(100)                               not null,
    status_           smallint     default 1                     not null,
    username          varchar(75)                                not null,
    org_id            bigint       default 0                     not null,
    org_treepath      varchar(260) default ''::character varying not null
);

alter table public.user_
    owner to test_db;

create table public.asset
(
    id_           bigint             not null
        constraint asset_pk
            primary key,
    name_         integer            not null,
    description   varchar(150),
    created_date  timestamp          not null,
    modified_date timestamp          not null,
    org_id        bigint             not null
        constraint asset_resource_id_unq
            unique,
    org_treepath  varchar(260)       not null
        constraint asset_resource_tree_path_unq
            unique,
    status_       smallint default 1 not null
);

alter table public.asset
    owner to test_db;

create table public.organization
(
    id_           bigint            not null
        constraint organization_pk
            primary key,
    name_         varchar(50)       not null,
    code_         varchar(50)       not null,
    created_date  timestamp         not null,
    modified_date timestamp         not null,
    status_       integer default 0 not null,
    description   varchar(150),
    parent_id     bigint            not null,
    sort          integer default 0 not null,
    treepath      varchar(260)      not null
        constraint organization_tree_path_unq
            unique,
    constraint organization_parent_id_sort_unq
        unique (sort, parent_id)
);

alter table public.organization
    owner to test_db;

create table public.usergroup
(
    id_            bigint                                    not null
        constraint user_group_pk
            primary key,
    name_          varchar(50)                               not null,
    description    varchar(150),
    status_        smallint    default 0                     not null,
    created_date   timestamp                                 not null,
    modified_date  timestamp                                 not null,
    org_id         varchar(36) default ''::character varying not null,
    parent_id      bigint      default 0                     not null,
    role_inherited boolean     default true                  not null,
    treepath       integer
);

alter table public.usergroup
    owner to test_db;

create table public.role_
(
    id_         bigint                                    not null
        constraint role_pk
            primary key,
    name_       varchar(100)                              not null,
    description varchar(150),
    status_     smallint    default 1                     not null,
    code_       varchar(50) default ''::character varying not null
        constraint role_code_unq
        unique
);

alter table public.role_
    owner to test_db;

create table public.resourcepermission
(
    id_           bigint                                     not null
        constraint resourcepermission_pk
            primary key,
    resource_type varchar(150) default ''::character varying not null,
    prim_key      bigint       default 0                     not null,
    actionflag    bigint       default 0                     not null,
    scope         smallint     default 1                     not null
);

alter table public.resourcepermission
    owner to test_db;

create table public.resourceaction
(
    id_           bigint                                     not null
        constraint resourceaction_pk
            primary key,
    resource_type varchar(100) default ''::character varying not null,
    action_method varchar(50)                                not null,
    action_bit    bigint       default 0                     not null
);

alter table public.resourceaction
    owner to test_db;

create table public.user_usergroup
(
    user_id      bigint not null,
    usergroup_id bigint not null,
    constraint user_usergroup_pk
        primary key (user_id, usergroup_id)
);

alter table public.user_usergroup
    owner to test_db;

create table public.role_permission
(
    role_id       bigint not null,
    permission_id bigint not null,
    constraint role_permission_pk
        primary key (role_id, permission_id)
);

alter table public.role_permission
    owner to test_db;

create table public.usergroup_role
(
    usergroup_id bigint not null,
    role_id      bigint not null
);

alter table public.usergroup_role
    owner to test_db;

