create table role
(
    id   bigint primary key,
    role varchar(32) not null
);

create table users
(
    id       bigint primary key,
    email    varchar(64) unique not null,
    password varchar(64)        not null,
    role_id  bigint             not null,
    constraint fk_role_id foreign key (role_id) references role (id)
);

create table user_profile
(
    id        bigint primary key,
    user_id   bigint      not null,
    address   varchar(64) not null,
    apartment varchar(32) not null,
    floor     int         not null,
    entrance  int         not null,
    constraint fk_user_id foreign key (user_id) references users (id)
);

create table courier
(
    id      bigint primary key,
    user_id bigint      not null,
    phone   varchar(16) not null,
    constraint fk_user_id foreign key (user_id) references users (id)
);

create table orders
(
    id              bigint primary key,
    status          varchar(16) not null,
    user_profile_id bigint      not null,
    delivery_time   timestamp,
    total_price     int         not null,
    courier_id      bigint      not null,
    constraint fk_user_profile_id foreign key (user_profile_id) references user_profile (id),
    constraint fk_courier_id foreign key (courier_id) references courier (id)
);

create table store
(
    id           bigint primary key,
    flowers_name varchar(64),
    amount       int,
    price        int
);

create table order_store
(
    order_id bigint not null,
    store_id bigint not null,
    constraint fk_order_id foreign key (order_id) references orders (id),
    constraint fk_store_id foreign key (store_id) references store (id)
);
