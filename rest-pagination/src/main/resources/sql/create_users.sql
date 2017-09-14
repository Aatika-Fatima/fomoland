insert into poll(1, 'What is java'); 
insert into options values(1, 'Programing lang', 1 );
insert into options values(2, 'Object Oriented lang', 1 );
insert into options values(3, 'Procedural lang', 1 );
insert into options values(4, 'rule based lang', 1 );
insert into vote values( 1, 1);
insert into vote values( 2, 1);
insert into vote values( 3, 1);
insert into vote values( 4, 2);
insert into vote values( 5, 2);

commit;

drop table authorities;
drop table users;
create table users (username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);