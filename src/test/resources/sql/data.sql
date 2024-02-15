INSERT INTO templates(id, tittle, template, dateOfCreation)
VALUES (1, 'TOPIC1', 'SomeText1 %name, %message', CURRENT_DATE + CURRENT_TIME);
INSERT INTO templates(id, tittle, template, dateOfCreation)
VALUES (2, 'TOPIC2', 'SomeText2 %name, %message', CURRENT_DATE + CURRENT_TIME);
INSERT INTO templates(id, tittle, template, dateOfCreation)
VALUES (3, 'TOPIC3', 'SomeText3 %name, %message', CURRENT_DATE + CURRENT_TIME);

INSERT INTO groups(id, name, dateOfCreation) VALUES (1, 'USER', CURRENT_DATE + CURRENT_TIME);
INSERT INTO groups(id, name, dateOfCreation) VALUES (2, 'ADMIN', CURRENT_DATE + CURRENT_TIME);
INSERT INTO groups(id, name, dateOfCreation) VALUES (3, 'CLIENT', CURRENT_DATE + CURRENT_TIME);

INSERT INTO users(id, fullname, email, groupId, dateOfCreation)
VALUES (1, 'User1', 'user1@mail.com', 1, CURRENT_DATE + CURRENT_TIME);
INSERT INTO users(id, fullname, email, groupId, dateOfCreation)
VALUES (2, 'User2', 'user2@mail.com', 1, CURRENT_DATE + CURRENT_TIME);
INSERT INTO users(id, fullname, email, groupId, dateOfCreation)
VALUES (3, 'User3', 'user3@mail.com', 2, CURRENT_DATE + CURRENT_TIME);
INSERT INTO users(id, fullname, email, groupId, dateOfCreation)
VALUES (4, 'User4', 'user4@mail.com', 2, CURRENT_DATE + CURRENT_TIME);
INSERT INTO users(id, fullname, email, groupId, dateOfCreation)
VALUES (5, 'User5', 'user5@mail.com', 3, CURRENT_DATE + CURRENT_TIME);
INSERT INTO users(id, fullname, email, groupId, dateOfCreation)
VALUES (6, 'User6', 'user6@mail.com', 3, CURRENT_DATE + CURRENT_TIME);

INSERT INTO mails (id, uniquemessage, groupuser, templateid, file, typefile, data)
VALUES (11, '0000001', 1, 3, '1010101010101001', 'PDF', '{"name":"testName1","message":"testMessage1"}');
INSERT INTO mails (id, uniquemessage, groupuser, templateid, file, typefile, data)
VALUES (12, '0000002', 1, 2, '1001100101010011', 'PNG', '{"name":"testName2","message":"testMessage2"}');
INSERT INTO mails (id, uniquemessage, groupuser, templateid, file, typefile, data)
VALUES (13, '00000003', 2, 1, '1000011101011001', 'PDF', '{"name":"testName3","message":"testMessage3"}');
INSERT INTO mails (id, uniquemessage, groupuser, templateid, file, typefile, data)
VALUES (14, '00000004', 2, 3, '0011100011000011', 'PNG', '{"name":"testName4","message":"testMessage4"}');
INSERT INTO mails (id, uniquemessage, groupuser, templateid, file, typefile, data)
VALUES (15, '00000005', 3, 2, '0011110000011010', 'PDF', '{"name":"testName5","message":"testMessage5"}');
INSERT INTO mails (id, uniquemessage, groupuser, templateid, file, typefile, data)
VALUES (16, '00000006', 3, 1, '11000010111100101', 'PNG', '{"name":"testName6","message":"testMessage6"}');

INSERT INTO errors(id, message, datetime, status, idMail)
VALUES (11, 'test message1', CURRENT_DATE + CURRENT_TIME, '001', 13);
INSERT INTO errors(id, message, datetime, status, idMail)
VALUES (12, 'test message2', CURRENT_DATE + CURRENT_TIME, '002', 13);
INSERT INTO errors(id, message, datetime, status, idMail)
VALUES (13, 'test message3', CURRENT_DATE + CURRENT_TIME, '003', 13);