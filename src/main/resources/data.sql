
insert into project (title) values ('bygge legat app');
insert into project (title) values ('fotballlag');
insert into project (title) values ('larping');

insert into user_account (name, email, visible) values ('Ulrik', 'ulrik@gmail.com', true);
insert into user_account (name, email, visible) values ('Trygve', 'trygve@gmail.com', true);
insert into user_account (name, email, visible) values ('Sondre', 'sondre@gmail.com', true);
insert into user_account (name, email, visible) values ('Karoline', 'karoline@gmail.com', true);

INSERT INTO skill (title) VALUES ('Java');
INSERT INTO skill (title) VALUES ('Design');

INSERT INTO user_account_skill ("user_account_id", "skill_id") VALUES (1,1);
INSERT INTO user_account_skill ("user_account_id", "skill_id") VALUES (1,2);

insert into contributor (project_id, role) values (1, 'member');
--insert into contributor (user_acount_id, project_id, role) values (1,1,'member');

