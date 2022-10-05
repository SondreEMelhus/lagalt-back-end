insert into project (title) values ('bygge legat app');
insert into project (title) values ('fotballlag');
insert into project (title) values ('larping');

insert into account (name, email) values ('Ulrik', 'ulrik@gmail.com');          -- 1
insert into account (name, email) values ('Trygve', 'trygve@gmail.com');        -- 2
insert into account (name, email) values ('Sondre', 'sondre@gmail.com');        -- 3
insert into account (name, email) values ('Karoline', 'karoline@gmail.com');    -- 4
insert into account (name, email) values ('Ronaldo', 'ronaldo@gmail.com');      -- 5
insert into account (name, email) values ('Messi', 'messi@gmail.com');          -- 6

insert into contributor (account_id, project_id, role) values (1,1,'member');
insert into contributor (account_id, project_id, role) values (2,1,'member');
insert into contributor (account_id, project_id, role) values (3,1,'member');
insert into contributor (account_id, project_id, role) values (4,1,'member');
insert into contributor (account_id, project_id, role) values (5,1,'member');
insert into contributor (account_id, project_id, role) values (6,1,'member');
insert into contributor (account_id, project_id, role) values (5,2,'member');
insert into contributor (account_id, project_id, role) values (6,2,'member');