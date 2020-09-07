drop table user_roles;
drop table users;
drop table roles;

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');


drop table survey_users;
drop table survey_answers;
drop table survey_questions;
drop table surveys;
drop table survey_user_answers;
drop table survey_user_responses;

delete from survey_users;
delete from survey_answers;
delete from survey_questions;
delete from surveys;
delete from survey_user_answers;
delete from survey_user_responses;


select * from surveys;
select * from survey_questions;
select * from survey_answers;
select * from survey_users;
select * from survey_user_responses;
select * from survey_user_answers;