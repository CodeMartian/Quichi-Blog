create table BLOG_POST
(
   id integer IDENTITY(1,1) not null,
   title varchar(255) not null,
   description varchar(255) not null,
   created_date datetime not null,
   primary key(id)
);