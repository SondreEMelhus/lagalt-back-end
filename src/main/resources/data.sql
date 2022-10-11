INSERT INTO industry (title) VALUES ('Music'); --1
INSERT INTO industry (title) VALUES ('Film'); --2
INSERT INTO industry (title) VALUES ('Game Development'); --3
INSERT INTO industry (title) VALUES ('Web Development'); --4

insert into project (title, industry_id) values ('bygge legat app', 1);
insert into project (title, industry_id) values ('fotballlag', 2);
insert into project (title, industry_id) values ('larping', 3);
insert into project (title, industry_id) values ('skating', 4);
insert into project (title, industry_id) values ('surfing', 1);
insert into project (title, industry_id) values ('trainspotting', 2);

insert into account (first_name, last_name, username, email, visible) values ('Ulrik', 'Lunde', 'ulrik', 'ulrik@gmail.com', true);          -- 1
insert into account (first_name, last_name, username, email, visible) values ('Trygve', 'Johannessen', 'tj', 'tj@gmail.com', true);
insert into account (first_name, last_name, username, email, visible) values ('Karoline', 'Øijorden', 'karo', 'karo@gmail.com', true);
insert into account (first_name, last_name, username, email, visible) values ('Sondre', 'Melhus', 'sondre', 'sondre@gmail.com', true);
insert into account (first_name, last_name, username, email, visible) values ('Egil', 'Olsen', 'drillo', 'drillo@gmail.com', true);
insert into account (first_name, last_name, username, email, visible) values ('Ole Gunnar', 'Solskjær', 'ole-g', 'ole-g@gmail.com', true);

insert into contributor (account_id, project_id, role) values (1,1,'owner');
insert into contributor (account_id, project_id, role) values (2,1,'member');
insert into contributor (account_id, project_id, role) values (3,1,'member');
insert into contributor (account_id, project_id, role) values (4,1,'member');
insert into contributor (account_id, project_id, role) values (5,2,'owner');
insert into contributor (account_id, project_id, role) values (6,2,'member');

INSERT INTO skill (title) VALUES ('Java');
INSERT INTO skill (title) VALUES ('Design');
INSERT INTO skill (title) VALUES ('Kaffe');

INSERT INTO account_skill ("account_id", "skill_id") VALUES (1,1);
INSERT INTO account_skill ("account_id", "skill_id") VALUES (1,2);

insert into skill_project ("project_id", "skill_id") values (1,1);
insert into skill_project ("project_id", "skill_id") values (1,2);
insert into skill_project ("project_id", "skill_id") values (1,3);