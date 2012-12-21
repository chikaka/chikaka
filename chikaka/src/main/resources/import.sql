insert into SystemUser(UserName, Password) Values ('administrator', encode(digest('chikaka', 'sha256'), 'base64'));
insert into SystemUser(UserName, Password) Values ('chedi'        , encode(digest('wa333'  , 'sha256'), 'base64'));
insert into SystemUser(UserName, Password) Values ('emir'         , encode(digest('wa333'  , 'sha256'), 'base64'));
insert into SystemUser(UserName, Password) Values ('adel'         , encode(digest('wa333'  , 'sha256'), 'base64'));
insert into SystemUser(UserName, Password) Values ('khaled'       , encode(digest('wa333'  , 'sha256'), 'base64'));
insert into SystemUser(UserName, Password) Values ('guest'        , encode(digest('guest'  , 'sha256'), 'base64'));

insert into SystemRole(name) values ('Admin');
insert into SystemRole(name) values ('Guest');
insert into SystemRole(name) values ('User' );

update SystemUser set Role = (select id from SystemRole where name = 'Admin') where UserName = 'administrator'; 
update SystemUser set Role = (select id from SystemRole where name = 'User' ) where UserName = 'chedi'        ;
update SystemUser set Role = (select id from SystemRole where name = 'User' ) where UserName = 'emir'         ;
update SystemUser set Role = (select id from SystemRole where name = 'User' ) where UserName = 'adel'         ;
update SystemUser set Role = (select id from SystemRole where name = 'User' ) where UserName = 'khaled'       ;
update SystemUser set Role = (select id from SystemRole where name = 'Guest') where UserName = 'guest'        ;

insert into Account(avatar, email, firstName, lastName) values ('default.png', 'test0@test.com', 'douda0', 'chikaka0');
insert into Account(avatar, email, firstName, lastName) values ('default.png', 'test1@test.com', 'douda1', 'chikaka1');
insert into Account(avatar, email, firstName, lastName) values ('default.png', 'test2@test.com', 'douda2', 'chikaka2');
insert into Account(avatar, email, firstName, lastName) values ('default.png', 'test3@test.com', 'douda3', 'chikaka3');

update Account set user_id=(select id from SystemUser where UserName='chedi' ) where firstName = 'douda0';
update Account set user_id=(select id from SystemUser where UserName='emir'  ) where firstName = 'douda1';
update Account set user_id=(select id from SystemUser where UserName='adel'  ) where firstName = 'douda2';
update Account set user_id=(select id from SystemUser where UserName='khaled') where firstName = 'douda3';


INSERT INTO game ( aiplayerscount , date , humanplayerscount, initialcredit) VALUES (5, now(), 5, 1000);
INSERT INTO game ( aiplayerscount , date , humanplayerscount, initialcredit) VALUES (4, now(), 6, 3000);