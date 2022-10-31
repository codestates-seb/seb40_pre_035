INSERT INTO Account(account_id, createdAt, modifiedAt, email, nickname, password, profile, role) VALUES('1', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'test1@test.com', 'testNickname1', '{bcrypt}$2a$10$cBR6WPkscv66SOrXwOJ6jOH7akwby7R0p5reALf/cLhiNEDyPqnpa', 'test/path', 'USER');

INSERT INTO QUESTION(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('101', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');
INSERT INTO QUESTION(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('102', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');
INSERT INTO QUESTION(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('103', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');
INSERT INTO QUESTION(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('104', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');
INSERT INTO QUESTION(question_id, createdAt, modifiedAt, content, title, account_id) VALUES('105', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'testContenttestContenttestContenttestContenttestContent', 'testTitle', '1');

INSERT INTO Answer(answer_id, createdAt, modifiedAt, content, totalVote, question_id) VALUES('1001', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'testcontent', '10', '101');
INSERT INTO Answer(answer_id, createdAt, modifiedAt, content, totalVote, question_id) VALUES('1002', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'testcontent', '10', '101');
INSERT INTO Answer(answer_id, createdAt, modifiedAt, content, totalVote, question_id) VALUES('1003', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'testcontent', '10', '101');
INSERT INTO Answer(answer_id, createdAt, modifiedAt, content, totalVote, question_id) VALUES('1004', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'testcontent', '10', '101');
INSERT INTO Answer(answer_id, createdAt, modifiedAt, content, totalVote, question_id) VALUES('1005', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'testcontent', '10', '101');

insert into QuestionVote(questionVote_id, createdAt, modifiedAt, state, account_id, question_id) values('2001', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'UP', '1', '101');
insert into QuestionVote(questionVote_id, createdAt, modifiedAt, state, account_id, question_id) values('2002', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'UP', '1', '101');
insert into QuestionVote(questionVote_id, createdAt, modifiedAt, state, account_id, question_id) values('2003', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'DOWN', '1', '101');
insert into QuestionVote(questionVote_id, createdAt, modifiedAt, state, account_id, question_id) values('2004', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'DOWN', '1', '101');
insert into QuestionVote(questionVote_id, createdAt, modifiedAt, state, account_id, question_id) values('2005', '2022-10-14 17:13:10', '2022-10-14 17:13:10', 'DOWN', '1', '101');
