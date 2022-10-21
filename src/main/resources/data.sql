-- create industries
INSERT INTO industry (title) VALUES ('Musikk'); --1
INSERT INTO industry (title) VALUES ('Film'); --2
INSERT INTO industry (title) VALUES ('Spillutvikling'); --3
INSERT INTO industry (title) VALUES ('Webutvikling'); --4

-- create skills for 'Musikk'
INSERT INTO skill (title, industry_id) VALUES ('Gitar', 1);
INSERT INTO skill (title, industry_id) VALUES ('Trommer', 1);
INSERT INTO skill (title, industry_id) VALUES ('Sanger', 1); --3
INSERT INTO skill (title, industry_id) VALUES ('Produsering', 1);
INSERT INTO skill (title, industry_id) VALUES ('Bass', 1);
INSERT INTO skill (title, industry_id) VALUES ('Piano', 1);
INSERT INTO skill (title, industry_id) VALUES ('Fiolin', 1); --7

-- create skills for 'Film'
INSERT INTO skill (title, industry_id) VALUES ('Regissering', 2); --8
INSERT INTO skill (title, industry_id) VALUES ('Skuespill', 2);
INSERT INTO skill (title, industry_id) VALUES ('Redigering', 2); --10
INSERT INTO skill (title, industry_id) VALUES ('Lyddesign', 2);
INSERT INTO skill (title, industry_id) VALUES ('Filming', 2);
INSERT INTO skill (title, industry_id) VALUES ('Manus', 2);
INSERT INTO skill (title, industry_id) VALUES ('Sminke', 2); --14

-- create skills for 'Spillutvikling'
INSERT INTO skill (title, industry_id) VALUES ('Java', 3); --15
INSERT INTO skill (title, industry_id) VALUES ('.Net', 3);
INSERT INTO skill (title, industry_id) VALUES ('Grafikk', 3);
INSERT INTO skill (title, industry_id) VALUES ('Manus', 3);
INSERT INTO skill (title, industry_id) VALUES ('Database', 3); --19
INSERT INTO skill (title, industry_id) VALUES ('Testing', 3); --20

-- create skills for 'Webutvikling'
INSERT INTO skill (title, industry_id) VALUES ('React', 4); --21
INSERT INTO skill (title, industry_id) VALUES ('Vue', 4);
INSERT INTO skill (title, industry_id) VALUES ('Angular', 4);
INSERT INTO skill (title, industry_id) VALUES ('UX', 4); -- 24
INSERT INTO skill (title, industry_id) VALUES ('CSS', 4);
INSERT INTO skill (title, industry_id) VALUES ('HTML', 4);
INSERT INTO skill (title, industry_id) VALUES ('Git', 4); --27

-- create keywords
INSERT INTO keyword (title) VALUES ('Pop');
INSERT INTO keyword (title) VALUES ('Rock');
INSERT INTO keyword (title) VALUES ('Indie');
INSERT INTO keyword (title) VALUES ('Metall');
INSERT INTO keyword (title) VALUES ('Musikkvideo');
INSERT INTO keyword (title) VALUES ('Musikal');
INSERT INTO keyword (title) VALUES ('Komedie'); --7
INSERT INTO keyword (title) VALUES ('Skrekk');
INSERT INTO keyword (title) VALUES ('Reklame'); --9
INSERT INTO keyword (title) VALUES ('Action');
INSERT INTO keyword (title) VALUES ('Mobilapp'); --11
INSERT INTO keyword (title) VALUES ('Puzzle');
INSERT INTO keyword (title) VALUES ('Adventure');
INSERT INTO keyword (title) VALUES ('Open world');
INSERT INTO keyword (title) VALUES ('Sport');
INSERT INTO keyword (title) VALUES ('Skyting');
INSERT INTO keyword (title) VALUES ('Mutliplayer');
INSERT INTO keyword (title) VALUES ('Singleplayer'); -- 18
INSERT INTO keyword (title) VALUES ('RPG'); --19
INSERT INTO keyword (title) VALUES ('Sosiale medier'); --20
INSERT INTO keyword (title) VALUES ('Nettbutikk');
INSERT INTO keyword (title) VALUES ('Hjemmeside');
INSERT INTO keyword (title) VALUES ('Blogg');

-- add keywords to industry
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (1,1);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (2,1);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (3,1);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (4,1);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (5,1);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (5,2);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (6,1);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (6,2);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (7,2);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (8,2);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (9,2);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (10,2);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (11,2);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (11,3);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (12,3);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (13,3);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (14,3);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (15,3);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (16,3);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (17,3);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (18,3);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (19,3);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (20,4);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (21,4);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (22,4);
INSERT INTO keyword_industry("keyword_id", "industry_id") VALUES (23,4);

-- create projects
insert into project (title, industry_id, status, description) values ('Ordspill', 3, 'Startet', 'Ordspill er et mobilspill inspirert av kryssord.');
insert into project (title, industry_id, status, description) values ('Treningsapp', 4, 'Planlegges', 'Jeg ønsker å utvikle en treningsapp som hjelper deg å lage et treningsprogram og hvor du kan loggføre utviklingen din. Jeg ønsker også at man skal kunne følge andre og se hva de trener.');
insert into project (title, industry_id, status, description) values ('Konsert', 1, 'Planlegges', 'Vi er en gruppe som planlegger en rekke med konserter og ser etter flere bandmedlemmer.');
insert into project (title, industry_id, status, description) values ('Kortfilm', 2, 'Startet', 'Vi holder på å lage en skrekk-komedie kortfilm og trenger flere folk.');
insert into project (title, industry_id, status, description) values ('Boyband', 1, 'Planlegges', 'Vi er to gutter som ser etter andre som vil danne et boyband med oss.');
insert into project (title, industry_id, status, description) values ('Alumini', 4, 'Startet', 'I dette prosjektet lager vi et sosialt medie for at tidligere studenter av en skole kan holde kontakten.');
insert into project (title, industry_id, status, description) values ('Reklame for treningsklær', 2, 'Planlegges', 'Vi trenger flere folk til å lage en reklamefilm.');

-- add skills to projects
-- project 1 'Ordspill'
insert into skill_project ("project_id", "skill_id") values (1,17);
insert into skill_project ("project_id", "skill_id") values (1,21);
-- project 2 'Treningsapp'
insert into skill_project ("project_id", "skill_id") values (2,25);
insert into skill_project ("project_id", "skill_id") values (2,27);
insert into skill_project ("project_id", "skill_id") values (2,24);
-- project 3 'Konsert'
insert into skill_project ("project_id", "skill_id") values (3,1);
insert into skill_project ("project_id", "skill_id") values (3,2);
insert into skill_project ("project_id", "skill_id") values (3,3);
-- project 4 'Kortfilm'
insert into skill_project ("project_id", "skill_id") values (4,10);
insert into skill_project ("project_id", "skill_id") values (4,12);
insert into skill_project ("project_id", "skill_id") values (4,14);
-- project 5 'The room 2'
insert into skill_project ("project_id", "skill_id") values (5,3);
insert into skill_project ("project_id", "skill_id") values (5,4);
insert into skill_project ("project_id", "skill_id") values (5,5);
-- project 6 'Alumini'
insert into skill_project ("project_id", "skill_id") values (6,27);
insert into skill_project ("project_id", "skill_id") values (6,23);
-- project 7 'Reklame for treningsklær'
insert into skill_project ("project_id", "skill_id") values (7,10);
insert into skill_project ("project_id", "skill_id") values (7,11);

-- add keyword to projects
-- -- project 1 'Ordspill'
INSERT INTO keyword_project("project_id", "keyword_id") VALUES (1,11);
INSERT INTO keyword_project("project_id", "keyword_id") VALUES (1,12);
INSERT INTO keyword_project("project_id", "keyword_id") VALUES (1,17);
-- project 2 'Treningsapp'
INSERT INTO keyword_project("project_id", "keyword_id") VALUES (2,11);
INSERT INTO keyword_project("project_id", "keyword_id") VALUES (2,20);
-- project 3 'Konsert'
INSERT INTO keyword_project("project_id", "keyword_id") VALUES (3,2);
INSERT INTO keyword_project("project_id", "keyword_id") VALUES (3,3);
-- project 4 'Kortfilm'
INSERT INTO keyword_project("project_id", "keyword_id") VALUES (4,7);
INSERT INTO keyword_project("project_id", "keyword_id") VALUES (4,8);
-- project 5 'The room 2'
INSERT INTO keyword_project("project_id", "keyword_id") VALUES (5,1);
-- project 6 'Alumini'
INSERT INTO keyword_project("project_id", "keyword_id") VALUES (6,20);
-- project 7 'Reklame for treningsklær'
INSERT INTO keyword_project ("project_id", "keyword_id") values (7,9);
INSERT INTO keyword_project ("project_id", "keyword_id") values (7,10);

-- create accounts
insert into account (username, visible, description) values ('Ulrik', true, 'Jeg er webutvikler og ser etter nye prosjekter jeg kan bidra til.');
insert into account (username, visible, description) values ('Trygve', false, 'Jeg er interessert i spillutvikling og webutvikling.');
insert into account (username, visible, description) values ('Karoline', true, 'Jeg er webutvikler og ser etter nye prosjekter jeg kan bidra til.');
insert into account (username, visible, description) values ('Sondre', true, 'Jeg er interessert i spillutvikling og webutvikling.');
insert into account (username, visible, description) values ('Mette', true, 'Jeg er webutvikler og ser etter nye prosjekter jeg kan bidra til.');
insert into account (username, visible, description) values ('Synnøve', true, 'Jeg er interessert i spillutvikling og webutvikling.');

-- add skills to accounts
INSERT INTO account_skill ("account_id", "skill_id") VALUES (1,3);
INSERT INTO account_skill ("account_id", "skill_id") VALUES (1,17);
INSERT INTO account_skill ("account_id", "skill_id") VALUES (1,24);
INSERT INTO account_skill ("account_id", "skill_id") VALUES (1,25);
INSERT INTO account_skill ("account_id", "skill_id") VALUES (2,3);
INSERT INTO account_skill ("account_id", "skill_id") VALUES (2,22);
INSERT INTO account_skill ("account_id", "skill_id") VALUES (2,16);
INSERT INTO account_skill ("account_id", "skill_id") VALUES (5,26);
INSERT INTO account_skill ("account_id", "skill_id") VALUES (5,27);
INSERT INTO account_skill ("account_id", "skill_id") VALUES (5,24);
INSERT INTO account_skill ("account_id", "skill_id") VALUES (6,26);
INSERT INTO account_skill ("account_id", "skill_id") VALUES (6,27);
INSERT INTO account_skill ("account_id", "skill_id") VALUES (6,25);
INSERT INTO account_skill ("account_id", "skill_id") VALUES (6,12);
INSERT INTO account_skill ("account_id", "skill_id") VALUES (6,20);

-- create contributors to project
insert into contributor (account_id, project_id, "role") values (1,1,'owner'); -- 1
insert into contributor (account_id, project_id, "role") values (2,1,'member');
insert into contributor (account_id, project_id, "role") values (3,1,'member');
insert into contributor (account_id, project_id, "role") values (4,1,'member');
insert into contributor (account_id, project_id, "role") values (5,2,'owner'); -- 2
insert into contributor (account_id, project_id, "role") values (6,2,'member');
insert into contributor (account_id, project_id, "role") values (5,2,'owner');
insert into contributor (account_id, project_id, "role") values (3,3,'owner'); --3
insert into contributor (account_id, project_id, "role") values (4,4,'owner'); --4
insert into contributor (account_id, project_id, "role") values (1,5,'owner'); -- 5
insert into contributor (account_id, project_id, "role") values (4,5,'member');
insert into contributor (account_id, project_id, "role") values (4,6,'owner'); --6
insert into contributor (account_id, project_id, "role") values (1,7,'owner'); -- 7
-- create applications
INSERT INTO application (account_id, project_id, motivation) VALUES (5,1,'Jeg ønsker å bidra til dette prosjektet fordi det virker spennende.');
INSERT INTO application (account_id, project_id, motivation) VALUES (6,1,'Jeg ønsker å bidra til dette prosjektet fordi det virker spennende.');

-- create chats
INSERT INTO chat ("text", "timestamp", "username", "project_id") VALUES ('Jeg får ikke kjørt Spring Boot', '2022-10-14T10:10:10+00:00', 'Karoline', 1);
INSERT INTO chat ("text", "timestamp", "username", "project_id") VALUES ('Tror du må fikse noe i annotasjonene', '2022-10-14T10:11:10+00:00', 'Sondre', 1);
INSERT INTO chat ("text", "timestamp", "username", "project_id") VALUES ('Det fungerte', '2022-10-14T10:20:10+00:00', 'Karoline', 1);
INSERT INTO chat ("text", "timestamp", "username", "project_id") VALUES ('Supert', '2022-10-14T10:25:10+00:00', 'Sondre', 1);
INSERT INTO chat ("text", "timestamp", "username", "project_id") VALUES ('Skal vi bruke React eller Angular?', '2022-10-14T10:25:10+00:00', 'Mette', 2);
INSERT INTO chat ("text", "timestamp", "username", "project_id") VALUES ('Jeg liker best React', '2022-10-14T10:30:10+00:00', 'Synnøve', 2);

--create message boards
INSERT INTO message_board ("title", "text", "timestamp", "username", "project_id") VALUES ('React problemer', 'Kan noen hjelpe meg med React?', '2022-10-12T08:10:10+00:00', 'Ulrik', 1);
INSERT INTO message_board ("title", "text", "timestamp", "username", "project_id") VALUES ('Møte', 'Kan vi ha et møte snart?', '2022-10-12T11:10:10+00:00', 'Trygve', 1);

--create messages
INSERT INTO message ("text", "timestamp", "message_board_id", "username") VALUES ('Ja, jeg kan hjelpe', '2022-10-12T08:12:10+00:00', 1, 'Sondre');
INSERT INTO message ("text", "timestamp", "message_board_id", "username") VALUES ('Ja. Om en halvtime?', '2022-10-12T11:10:10+00:00', 2, 'Sondre');
INSERT INTO message ("text", "timestamp", "message_board_id", "username") VALUES ('Ja', '2022-10-12T01:11:10+00:00', 2, 'Ulrik');
INSERT INTO message ("text", "timestamp", "message_board_id", "username") VALUES ('Det passer', '2022-10-12T11:11:10+00:00', 2, 'Karoline');

--create status update boards
INSERT INTO status_update_board ("title", "text", "timestamp", "username", "project_id") VALUES ('Version 1', 'Frontend og backend er koblet sammen', '2022-09-12T08:10:10+00:00', 'Sondre', 1);
INSERT INTO status_update_board ("title", "text", "timestamp", "username", "project_id") VALUES ('Version 2', 'Vi har en fungerende chat', '2022-10-12T08:10:10+00:00', 'Sondre', 1);

-- create status updates
INSERT INTO status_update ("text", "timestamp", "status_update_board_id", "username") VALUES ('Bra jobbet!', '2022-09-12T09:00:10+00:00', 1, 'Karoline');
INSERT INTO status_update ("text", "timestamp", "status_update_board_id", "username") VALUES ('Bra jobbet!', '2022-10-12T09:00:10+00:00', 2, 'Trygve');
INSERT INTO status_update ("text", "timestamp", "status_update_board_id", "username") VALUES ('Bra jobbet!', '2022-10-12T09:20:10+00:00', 2, 'Ulrik');