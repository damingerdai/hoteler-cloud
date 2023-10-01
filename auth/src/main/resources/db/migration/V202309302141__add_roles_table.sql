CREATE TABLE IF NOT EXISTS  roles (
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

INSERT INTO roles (id, name, description, create_dt, create_user, update_dt, update_user) VALUES(1, 'admin', 'Admin', now(), 'system', now(), 'system');
INSERT INTO roles (id, name, description, create_dt, create_user, update_dt, update_user) VALUES(2, 'manager', 'Manager', now(), 'system', now(), 'system');
INSERT INTO roles (id, name, description, create_dt, create_user, update_dt, update_user) VALUES(3, 'users', 'Standard User', now(), 'system', now(), 'system');

CREATE TABLE IF NOT EXISTS user_roles (
    id bigint not null auto_increment,
    user_id int not null,
    role_id bigint not null,
    create_dt timestamp not null,
    create_user varchar(50) not null,
    deleted_at timestamp default null,
    foreign key (user_id) references users(id) on delete cascade on update cascade,
    foreign key (role_id) references roles(id) on delete cascade on update cascade,
    PRIMARY KEY (id)
);

INSERT INTO user_roles (user_id, role_id, create_dt, create_user) SELECT u.id, r.id, now(), 'system' FROM users u JOIN roles r ON r.id = '3';
INSERT INTO user_roles (user_id, role_id, create_dt, create_user) SELECT u.id, r.id, now(), 'system' FROM users u JOIN roles r ON r.id = '3' ORDER BY u.create_dt LIMIT 1;

