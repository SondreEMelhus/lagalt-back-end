INSERT INTO industry (title) VALUES ('Musikk'); --1
INSERT INTO industry (title) VALUES ('Film'); --2
INSERT INTO industry (title) VALUES ('Spillutvikling'); --3
INSERT INTO industry (title) VALUES ('Webutvikling'); --4

insert into project (title, industry_id) values ('bygge legat app', 1);
insert into project (title, industry_id) values ('fotballlag', 2);
insert into project (title, industry_id) values ('larping', 3);
insert into project (title, industry_id) values ('skating', 4);
insert into project (title, industry_id) values ('surfing', 1);
insert into project (title, industry_id) values ('trainspotting', 2);

insert into account (username, visible) values ('ulrik', true);          -- 1
insert into account (username, visible) values ('tj', true);
insert into account (username, visible) values ('Karoline', true);
insert into account (username, visible) values ('Sondre', true);
insert into account (username, visible) values ('drillo', true);
insert into account (username, visible) values ('Ole Gunnar', true);

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

INSERT INTO keyword (title) VALUES ('Rock');
INSERT INTO keyword (title) VALUES ('Comedy');

INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (1,1);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (2,2);

INSERT INTO keyword_project("keyword_id", "project_id") VALUES (1,1);
INSERT INTO keyword_project("keyword_id", "project_id") VALUES (2,2);

INSERT INTO application (account_id, project_id, motivation) VALUES (1,1,'I want to join this project');
INSERT INTO application (account_id, project_id, motivation) VALUES (2,2,'This project seems cool');

INSERT INTO chat ("text", "timestamp", "account_id", "project_id") VALUES ('Hei', '2022-10-12T08:10:10+00:00', 1, 1);

INSERT INTO message_board ("title", "text", "timestamp", "account_id", "project_id") VALUES ('React problemer', 'Kan noen hjelpe meg med React?', '2022-10-12T08:10:10+00:00', 1, 1);
INSERT INTO message_board ("title", "text", "timestamp", "account_id", "project_id") VALUES ('Møte', 'Skal vi ha et møte snart?', '2022-10-12T08:10:10+00:00', 2, 1);

INSERT INTO message ("text", "timestamp", "message_board_id", "account_id") VALUES ('Nei', '2022-10-12T08:10:10+00:00', 1, 4);
INSERT INTO message ("text", "timestamp", "message_board_id", "account_id") VALUES ('Ja', '2022-10-12T08:10:10+00:00', 1, 4);
INSERT INTO message ("text", "timestamp", "message_board_id", "account_id") VALUES ('Ok', '2022-10-12T08:10:10+00:00', 2, 4);
INSERT INTO message ("text", "timestamp", "message_board_id", "account_id") VALUES ('Ja', '2022-10-12T08:10:10+00:00', 2, 3);

INSERT INTO status_update_board ("title", "text", "timestamp", "account_id", "project_id") VALUES ('Version 1', 'Frontend og backend er koblet sammen', '2022-10-12T08:10:10+00:00', 1, 1);
INSERT INTO status_update_board ("title", "text", "timestamp", "account_id", "project_id") VALUES ('Version 2', 'Vi har en fungerende chat', '2022-10-12T08:10:10+00:00', 4, 1);

INSERT INTO status_update ("text", "timestamp", "status_update_board_id", "account_id") VALUES ('Bra jobbet!', '2022-10-12T08:10:10+00:00', 1, 4);
INSERT INTO status_update ("text", "timestamp", "status_update_board_id", "account_id") VALUES ('Bra jobbet!', '2022-10-12T08:10:10+00:00', 2, 4);
INSERT INTO status_update ("text", "timestamp", "status_update_board_id", "account_id") VALUES ('Bra jobbet!', '2022-10-12T08:10:10+00:00', 2, 3);