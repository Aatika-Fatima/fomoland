delete from authorities; 
delete from users; 
commit;

insert into users (username, password, enabled) values ('user1', 'user1', true);
insert into users (username, password, enabled) values ('user2', 'user2', true);
insert into users (username, password, enabled) values ('user3', 'user3', true);

insert into authorities (username, authority) values('user1', 'ROLE_USER');
insert into authorities (username, authority) values('user2', 'ROLE_USER');
insert into authorities (username, authority) values('user3', 'ROLE_USER');

insert into poll values(1, 'What is java'); 
insert into options values(1, 'Programing lang', 1 );
insert into options values(2, 'Object Oriented lang', 1 );
insert into options values(3, 'Procedural lang', 1 );
insert into options values(4, 'Rule Based lang', 1 );
insert into vote values( 1, 1);
insert into vote values( 2, 1);
insert into vote values( 3, 1);
insert into vote values( 4, 2);
insert into vote values( 5, 2);

commit;