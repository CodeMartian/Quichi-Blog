
create table comment
(
   id integer AUTO_INCREMENT not null,
   content varchar(500) not null,
   created_date date not null,
   post_id integer not null,
   primary key(id)
);