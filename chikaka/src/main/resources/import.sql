insert into SystemUser(UserName, Password) Values ('administrator', encode(digest('chikaka', 'sha256'), 'base64'));
insert into SystemUser(UserName, Password) Values ('chedi', encode(digest('wa333', 'sha256'), 'base64'));
insert into SystemUser(UserName, Password) Values ('guest', encode(digest('guest', 'sha256'), 'base64'));

insert into SystemRole(name) values ('Admin');
insert into SystemRole(name) values ('User');
insert into SystemRole(name) values ('Guest');

update SystemUser set Role = (select id from SystemRole where name = 'Admin') where UserName = 'administrator'; 
update SystemUser set Role = (select id from SystemRole where name = 'User' ) where UserName = 'chedi';
update SystemUser set Role = (select id from SystemRole where name = 'Guest') where UserName = 'guest';

insert into Account(avatar, email, firstName, lastName) values ('default.png', 'test@test.com', 'douda', 'chikaka');

update Account set user_id=(select id from SystemUser where UserName='chedi');