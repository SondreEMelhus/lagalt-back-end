
insert into project (title) values ('bygge legat app');
insert into project (title) values ('fotballlag');
insert into project (title) values ('larping');
insert into project (title) values ('skating');
insert into project (title) values ('crossfit');

insert into account (first_name, last_name, username, email, visible) values ('Ulrik', 'Lunde', 'ulrik', 'ulrik@gmail.com', true);          -- 1
insert into account (first_name, last_name, username, email, visible) values ('Trygve', 'Johannessen', 'tj', 'tj@gmail.com', true);
insert into account (first_name, last_name, username, email, visible) values ('Karoline', 'Øijorden', 'karo', 'karo@gmail.com', true);
insert into account (first_name, last_name, username, email, visible) values ('Sondre', 'Melhus', 'sondre', 'sondre@gmail.com', true);
insert into account (first_name, last_name, username, email, visible) values ('Egil', 'Olsen', 'drillo', 'drillo@gmail.com', true);
insert into account (first_name, last_name, username, email, visible) values ('Ole Gunnar', 'Solskjær', 'ole-g', 'ole-g@gmail.com', true);

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
