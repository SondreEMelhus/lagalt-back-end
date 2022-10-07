
insert into project (title) values ('bygge legat app');
insert into project (title) values ('fotballlag');
insert into project (title) values ('larping');
insert into project (title) values ('skating');
insert into project (title) values ('crossfit');

insert into account (name, email, visible) values ('Ulrik', 'ulrik@gmail.com', true);          -- 1
insert into account (name, email, visible) values ('Trygve', 'trygve@gmail.com', true);        -- 2
insert into account (name, email, visible) values ('Sondre', 'sondre@gmail.com', true);        -- 3
insert into account (name, email, visible) values ('Karoline', 'karoline@gmail.com', true);    -- 4
insert into account (name, email, visible) values ('Ronaldo', 'ronaldo@gmail.com', true);      -- 5
insert into account (name, email, visible) values ('Messi', 'messi@gmail.com', true);          -- 6

insert into contributor (account_id, project_id, role) values (1,1,'member');
insert into contributor (account_id, project_id, role) values (2,1,'member');
insert into contributor (account_id, project_id, role) values (3,1,'member');
insert into contributor (account_id, project_id, role) values (4,1,'member');
insert into contributor (account_id, project_id, role) values (5,1,'member');
insert into contributor (account_id, project_id, role) values (6,1,'member');
insert into contributor (account_id, project_id, role) values (5,2,'member');
insert into contributor (account_id, project_id, role) values (6,2,'member');

INSERT INTO skill (title) VALUES ('Java');
INSERT INTO skill (title) VALUES ('Design');

INSERT INTO account_skill ("account_id", "skill_id") VALUES (1,1);
INSERT INTO account_skill ("account_id", "skill_id") VALUES (1,2);
