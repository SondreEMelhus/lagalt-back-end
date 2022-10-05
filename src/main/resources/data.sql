INSERT INTO project (name) VALUES ('spille cs');
INSERT INTO project (name) VALUES ('fotballlag');
INSERT INTO project (name) VALUES ('larping');

INSERT INTO user_account (name, email) VALUES ('Ulrik', 'ulrik@gmail.com');
INSERT INTO user_account (name, email) VALUES ('Trygve', 'trygve@gmail.com');

INSERT INTO skill (title) VALUES ('Java');
INSERT INTO skill (title) VALUES ('Design');

INSERT INTO user_account_skill ("user_account_id", "skill_id") VALUES (1,1);
INSERT INTO user_account_skill ("user_account_id", "skill_id") VALUES (1,2);