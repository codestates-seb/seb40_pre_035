INSERT INTO Account(account_id, createdAt, modifiedAt, email, nickname, password, profile, role) VALUES('1', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'test1@test.com', 'testNickname1', '{bcrypt}$2a$10$cBR6WPkscv66SOrXwOJ6jOH7akwby7R0p5reALf/cLhiNEDyPqnpa', 'test/path', 'USER');
INSERT INTO ACCOUNT(account_id, createdAt, modifiedAt, email, nickname, password, profile, role) VALUES('2', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'login@gmail.com', 'logi', '{bcrypt}$2a$10$.84GwAUKxYN/hfzrlmCj0.W2273UHiXl5bfvF2Fnmk.I2bU6N..VW', 'test/path', 'USER');


INSERT INTO Question(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('101', '2022-10-14 17:13:10', '2022-10-14 18:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');
INSERT INTO Question(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('102', '2022-10-14 17:14:10', '2022-10-14 18:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');
INSERT INTO Question(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('103', '2022-10-14 17:15:10', '2022-10-14 18:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');
INSERT INTO Question(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('104', '2022-10-14 17:16:10', '2022-10-14 18:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');
INSERT INTO Question(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('105', '2022-10-14 17:17:10', '2022-10-14 18:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');
INSERT INTO Question(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('106', '2022-10-14 17:18:10', '2022-10-14 18:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');
INSERT INTO Question(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('107', '2022-10-14 17:19:10', '2022-10-14 18:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');
INSERT INTO Question(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('108', '2022-10-14 17:20:10', '2022-10-14 18:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');
INSERT INTO Question(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('109', '2022-10-14 17:21:10', '2022-10-14 18:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');
INSERT INTO Question(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('110', '2022-10-14 17:22:10', '2022-10-14 18:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');
INSERT INTO Question(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('111', '2022-10-14 17:23:10', '2022-10-14 18:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');
INSERT INTO Question(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('112', '2022-10-14 17:24:10', '2022-10-14 18:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');
INSERT INTO Question(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('113', '2022-10-14 17:25:10', '2022-10-14 18:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');
INSERT INTO Question(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('114', '2022-10-14 17:26:10', '2022-10-14 18:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');
INSERT INTO Question(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('115', '2022-10-14 17:27:10', '2022-10-14 18:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');
INSERT INTO Question(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('116', '2022-10-14 17:28:10', '2022-10-14 18:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');
INSERT INTO Question(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('117', '2022-10-14 17:29:10', '2022-10-14 18:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');
INSERT INTO Question(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('118', '2022-10-14 17:30:10', '2022-10-14 18:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');
INSERT INTO Question(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('119', '2022-10-14 17:31:10', '2022-10-14 18:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');
INSERT INTO Question(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('120', '2022-10-14 17:32:10', '2022-10-14 18:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');
INSERT INTO Question(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('121', '2022-10-14 17:33:10', '2022-10-14 18:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');

INSERT INTO Answer(answer_id, createdAt, modifiedAt, content, selected, totalVote, question_id) VALUES('1001', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'testcontent', false, '10', '101');
INSERT INTO Answer(answer_id, createdAt, modifiedAt, content, selected, totalVote, question_id) VALUES('1002', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'testcontent', false, '10', '101');
INSERT INTO Answer(answer_id, createdAt, modifiedAt, content, selected, totalVote, question_id) VALUES('1003', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'testcontent', false, '10', '101');
INSERT INTO Answer(answer_id, createdAt, modifiedAt, content, selected, totalVote, question_id) VALUES('1004', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'testcontent', false, '10', '101');
INSERT INTO Answer(answer_id, createdAt, modifiedAt, content, selected, totalVote, question_id) VALUES('1005', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'testcontent', true, '10', '101');

insert into QuestionVote(questionVote_id, createdAt, modifiedAt, state, account_id, question_id) values('2001', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'UP', '1', '101');
insert into QuestionVote(questionVote_id, createdAt, modifiedAt, state, account_id, question_id) values('2002', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'UP', '1', '101');
insert into QuestionVote(questionVote_id, createdAt, modifiedAt, state, account_id, question_id) values('2003', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'DOWN', '1', '101');
insert into QuestionVote(questionVote_id, createdAt, modifiedAt, state, account_id, question_id) values('2004', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'DOWN', '1', '101');
insert into QuestionVote(questionVote_id, createdAt, modifiedAt, state, account_id, question_id) values('2005', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'DOWN', '1', '101');
