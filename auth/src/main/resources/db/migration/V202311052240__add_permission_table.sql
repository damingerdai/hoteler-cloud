CREATE TABLE IF NOT EXISTS permissions (
    id bigint not null auto_increment,
    name varchar(100) not null,
    description text not null,
    create_dt timestamp not null,
    create_user varchar(50) not null,
    update_dt timestamp not null,
    update_user varchar(50) not null,
    deleted_at timestamp default null,
    PRIMARY KEY (id)
);

INSERT INTO permissions (name, description, create_dt, create_user, update_dt, update_user) VALUES
 ('manage_dashboard', 'Dashboard Management', now(), 'system', now(), 'system'),
 ('manage_customer', 'Customer Management', now(), 'system', now(), 'system'),
 ('manage_room', 'Room Management', now(), 'system', now(), 'system'),
 ('manage_user', 'User Management', now(), 'system', now(), 'system');

 CREATE TABLE IF NOT EXISTS role_permissions
 (
      id                bigint not null auto_increment
     , role_id          bigint NOT NULL
     , permission_id    bigint NOT NULL
     , created_at       timestamp NOT NULL
     , updated_at       timestamp
     , deleted_at       timestamp default null,
     foreign key (role_id) references roles(id) on delete cascade on update cascade,
     foreign key (permission_id) references permissions(id) on delete cascade on update cascade,
     PRIMARY KEY (id)
 );

 INSERT INTO role_permissions (role_id, permission_id, created_at, updated_at )
 SELECT r.id, p.id, now(), now() FROM permissions p INNER JOIN roles r ON r.name = 'users' AND p.name in ('manage_dashboard', 'manage_customer', 'manage_room');

 INSERT INTO role_permissions (role_id, permission_id, created_at, updated_at )
 SELECT r.id, p.id, now(), now() FROM permissions p INNER JOIN roles r ON r.name = 'manager' AND p.name in ('manage_dashboard', 'manage_user', 'manage_room');