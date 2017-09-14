use aatika;
drop table  if exists userconnection;
insert into interest values(1, 'Music');
insert into interest values(2, 'Dance');
insert into interest values(3, 'Sports'); 
commit;

insert into Contact values( 1, 'aatika','123-456-7890');
insert into Contact values( 2, 'aatika','123-456-7890');
insert into Contact values( 3, 'aatika','123-456-7890');
insert into Contact values( 4, 'aatika','123-456-7890');
insert into Contact values( 5, 'aatika','123-456-7890');
insert into Contact values( 6, 'aatika','123-456-7890');
insert into Contact values( 7, 'aatika','123-456-7890');
insert into Contact values( 8, 'aatika','123-456-7890');
insert into Contact values( 9, 'aatika','123-456-7890');
insert into Contact values( 10, 'aatika','123-456-7890');
insert into Contact values( 11, 'aatika','123-456-7890');
insert into Contact values( 12, 'aatika','123-456-7890');
insert into Contact values( 13, 'aatika','123-456-7890');
insert into Contact values( 14, 'aatika','123-456-7890');
insert into Contact values( 15, 'aatika','123-456-7890');
insert into Contact values( 16, 'aatika','123-456-7890');
insert into Contact values( 17, 'aatika','123-456-7890');
insert into Contact values( 18, 'aatika','123-456-7890');
insert into Contact values( 19, 'aatika','123-456-7890');
insert into Contact values( 20, 'aatika','123-456-7890');
--insert into account(username, password, first_Name, last_Name, enabled)
--values('Aatika Fatima', 'secret', 'Aatika', 'Fatima', true);

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

insert into Social_Media(social_Media_Name, points) values('Facebook', 60);
insert into social_Media(social_Media_Name, points) values('Twitter', 50);
insert into social_Media(social_Media_Name, points) values('Instagram', 40);
insert into social_Media(social_Media_Name, points) values('WhatsApp', 30);
insert into social_Media(social_Media_Name, points) values('Pinterest', 20);
insert into social_Media(social_Media_Name, points) values('LinkedIn', 10);

insert into question(answer,link,notes,option_a, option_b, option_c, option_d, points, question_text, interest_id)
values('Fruit','https://www.google/com','An apple a day','fruit', 'veg', 'tom', 'jacl', 10, 'What is apple', 1);

insert into question(answer,link,notes,option_a, option_b, option_c, option_d, points, question_text, interest_id)
values('Fruit','https://www.mango/com','An Mago a day','fruit', 'veg', 'tom', 'jacl', 10, 'What is Mango', 2);

insert into question(answer,link,notes,option_a, option_b, option_c, option_d, points, question_text, interest_id)
values('Fruit','https://www.mango/com','An Mago a day','fruit', 'veg', 'tom', 'jacl', 10, 'What is Banana', 2);

insert into question(answer,link,notes,option_a, option_b, option_c, option_d, points, question_text, interest_id)
values('Fruit','https://www.mango/com','An Mago a day','fruit', 'veg', 'tom', 'jacl', 10, 'What is PineApple', 2);

insert into question(answer,link,notes,option_a, option_b, option_c, option_d, points, question_text, interest_id)
values('Fruit','https://www.mango/com','An Mago a day','fruit', 'veg', 'tom', 'jacl', 10, 'What is Tomato', 2);

insert into question(answer,link,notes,option_a, option_b, option_c, option_d, points, question_text, interest_id)
values('Fruit','https://www.mango/com','An Mago a day','fruit', 'veg', 'tom', 'jacl', 10, 'What is Beans', 2);

insert into question(answer,link,notes,option_a, option_b, option_c, option_d, points, question_text, interest_id)
values('Fruit','https://www.mango/com','An Mago a day','fruit', 'veg', 'tom', 'jacl', 10, 'What is Carrot', 2);

insert into question(answer,link,notes,option_a, option_b, option_c, option_d, points, question_text, interest_id)
values('Fruit','https://www.mango/com','An Mago a day','fruit', 'veg', 'tom', 'jacl', 10, 'What is duster', 2);

insert into question(answer,link,notes,option_a, option_b, option_c, option_d, points, question_text, interest_id)
values('Fruit','https://www.mango/com','An Mago a day','fruit', 'veg', 'tom', 'jacl', 10, 'What is chicken', 2);

insert into question(answer,link,notes,option_a, option_b, option_c, option_d, points, question_text, interest_id)
values('Fruit','https://www.mango/com','An Mago a day','fruit', 'veg', 'tom', 'jacl', 10, 'What is shoes', 2);

insert into question(answer,link,notes,option_a, option_b, option_c, option_d, points, question_text, interest_id)
values('Fruit','https://www.mango/com','An Mago a day','fruit', 'veg', 'tom', 'jacl', 10, 'What is dance', 2);

insert into question(answer,link,notes,option_a, option_b, option_c, option_d, points, question_text, interest_id)
values('Fruit','https://www.mango/com','An Mago a day','fruit', 'veg', 'tom', 'jacl', 10, 'What is carrom', 2);

insert into question(answer,link,notes,option_a, option_b, option_c, option_d, points, question_text, interest_id)
values('Fruit','https://www.mango/com','An Mago a day','fruit', 'veg', 'tom', 'jacl', 10, 'What is toast', 2);

commit;
 
insert into content_feed(description, image, image_url, points, interest_id) 
values('Twinkle Twinkle Little stars how i wonder wat u are ,',null,
'https://images-na.ssl-images-amazon.com/images/I/71YdEILoleL._SL1500_.jpg', 30,1);
 
insert into content_feed(description, image, image_url, points, interest_id) 
values('Twinkle Twinkle Little stars how i wonder wat u are ,',null,
'https://images-eu.ssl-images-amazon.com/images/I/41%2BRndexr3L._AC_UX500_SY400_.jpg', 30,1);
 
insert into content_feed(description, image, image_url, points, interest_id) 
values('Twinkle Twinkle Little stars how i wonder wat u are ,',null,
'https://images-eu.ssl-images-amazon.com/images/I/31VA6HruKYL._AC_UX500_SY400_.jpg', 30,1);
 
insert into content_feed(description, image, image_url, points, interest_id) 
values('Twinkle Twinkle Little stars how i wonder wat u are ,',null,
'https://images-na.ssl-images-amazon.com/images/I/81v3qgpG%2BqL._SL1500_.jpg', 30,1);
 
insert into content_feed(description, image, image_url, points, interest_id) 
values('Twinkle Twinkle Little stars how i wonder wat u are ,',null,
'https://images-na.ssl-images-amazon.com/images/I/61p5xdGL5BL._SL1437_.jpg', 30,1);

 
insert into content_feed(description, image, image_url, points, interest_id) 
values('Twinkle Twinkle Little stars how i wonder wat u are ,',null,
'https://images-na.ssl-images-amazon.com/images/I/41yLoYTQE-L.jpg', 30,1);

insert into content_feed(description, image, image_url, points, interest_id) 
values('Twinkle Twinkle Little stars how i wonder wat u are ,',null,
'https://images-na.ssl-images-amazon.com/images/I/51Qv2VyZpzL.jpg', 30,1);


insert into content_feed(description, image, image_url, points, interest_id) 
values('Twinkle Twinkle Little stars how i wonder wat u are ,',null,
'https://images-na.ssl-images-amazon.com/images/I/51KdTECW6RL.jpg', 30,1);

insert into content_feed(description, image, image_url, points, interest_id) 
values('Twinkle Twinkle Little stars how i wonder wat u are ,',null,
'https://images-na.ssl-images-amazon.com/images/I/41vEnGEBlPL.jpg', 30,1);

insert into content_feed(description, image, image_url, points, interest_id) 
values('Twinkle Twinkle Little stars how i wonder wat u are ,',null,
'https://images-na.ssl-images-amazon.com/images/I/41c7kN32PCL.jpg', 30,1);
 
commit;