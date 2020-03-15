
USE ocm;

CREATE TABLE SURVEY (
    SurveyID        int(10) NOT NULL AUTO_INCREMENT,
    SurveyName      varchar(20) NOT NULL,
    DateCreated     timestamp default current_timestamp,
    IsLegacy        TINYINT(1) NOT NULL,
    AvgPCM          int(10),
    OrgId			int(10),
    CONSTRAINT surveyid_pk PRIMARY KEY (SurveyID)
);

CREATE TABLE ORG(
	OrgID			int(10) NOT NULL AUTO_INCREMENT,
    CONSTRAINT orgid_PK PRIMARY KEY (OrgID)
);

CREATE TABLE PERSON(
    UserID          int(10) NOT NULL AUTO_INCREMENT,
    FirstName       varchar(50) NOT NULL,
    LastName        varchar(50) NOT NULL,
    Email           varchar(50) NOT NULL,
    Department      varchar(50),
    Pass            varchar(50) NOT NULL,
    Access_Level    TINYINT(1) NOT NULL,
    Assess_Status   TINYINT(1) NOT NULL,
    Pass_Flag       TINYINT(1) NOT NULL,
    OrgId			int(10) NOT NULL,
    CONSTRAINT userid_PK PRIMARY KEY (UserID)
);

CREATE TABLE PERSON_SURVEY (
    SurveyID        int(10),
    UserID          int(10),
    PCM             int(10),
    CONSTRAINT surveyid_fk FOREIGN KEY (SurveyID) REFERENCES SURVEY(SurveyID) ON DELETE CASCADE,
    CONSTRAINT userid_fk FOREIGN KEY (UserID) REFERENCES PERSON(UserID) ON DELETE CASCADE
);

CREATE TABLE FACTOR (
	IDFAC			int(10) NOT NULL,
    FactorID        int(10) NOT NULL AUTO_INCREMENT,
    SurveyID        int(10),
    Details         varchar(255),
    AvgFactorPCM    int(10),
    CONSTRAINT factorid_pk PRIMARY KEY (FactorID),
    CONSTRAINT fk_survey_id FOREIGN KEY (SurveyID) REFERENCES SURVEY(SurveyID) ON DELETE CASCADE
);

CREATE TABLE QUESTION (
	IDFAC			int(10) NOT NULL,
	SurveyID		int(10),
    QuestionID      int(10) NOT NULL AUTO_INCREMENT,
    FactorID        int(10),
    Details         varchar(255),
    CONSTRAINT assess_questionid_pk PRIMARY KEY (QuestionID),
    CONSTRAINT assess_factor_fk FOREIGN KEY (FactorID) REFERENCES FACTOR(FactorID) ON DELETE CASCADE
);

CREATE TABLE FACTOR_ANSWER (
    FactorAnswerID  int(10) NOT NULL AUTO_INCREMENT,
    UserID          int(10),
    SurveyID        int(10),
    FactorRank      int(10),
    FactorPCM       int(10),
    FactorID        int(10),
    CONSTRAINT factoranswerid_pk PRIMARY KEY (FactorAnswerID),
    CONSTRAINT fk_userid FOREIGN KEY (UserID) REFERENCES PERSON(UserID) ON DELETE CASCADE,
    CONSTRAINT fk_surveyid FOREIGN KEY (SurveyID) REFERENCES SURVEY(SurveyID) ON DELETE CASCADE,
    CONSTRAINT fk_factorid FOREIGN KEY (FactorID) REFERENCES FACTOR(FactorID) ON DELETE CASCADE
);

CREATE TABLE QUESTION_ANSWER (
    QuestionAnswerID    int(10) NOT NULL AUTO_INCREMENT,
    UserID              int(10),
    SurveyID            int(10),
    Score               double(10,2),
    QuestionID          int(10),
    OrgId				int(10) NOT NULL,
    CONSTRAINT questionid_pk PRIMARY KEY (QuestionAnswerID),
    CONSTRAINT user_id_fk FOREIGN KEY (UserID) REFERENCES PERSON(UserID) ON DELETE CASCADE,
    CONSTRAINT survey_id_fk FOREIGN KEY (SurveyID) REFERENCES SURVEY(SurveyID) ON DELETE CASCADE,
    CONSTRAINT question_id_fk FOREIGN KEY (QuestionID) REFERENCES QUESTION(QuestionID) ON DELETE CASCADE
);

CREATE TABLE FACTOR_DEFAULT (
    FactorDefaultID     int(10) NOT NULL,
    Details             varchar(255),
    CONSTRAINT def_factorid_pk PRIMARY KEY (FactorDefaultID)
);

CREATE TABLE QUESTION_DEFAULT (
    QuestionDefaultID   int(10) NOT NULL,
    FactorDefaultID     int(10),
    Details             varchar(255),
    CONSTRAINT def_questionid_pk PRIMARY KEY (QuestionDefaultID),
    CONSTRAINT def_factor_fk FOREIGN KEY (FactorDefaultID) REFERENCES FACTOR_DEFAULT(FactorDefaultID) ON DELETE CASCADE
);


INSERT INTO Person (UserID, FirstName, LastName, Email, Department, Pass, Access_Level, Assess_Status, 
Pass_Flag, OrgID) VALUES ('1', 'Root', 'Administrator', 'admin@root.ca', 'default', 'password', '1', '0', '1', '1');

INSERT INTO Person (UserID, FirstName, LastName, Email, Department, Pass, Access_Level, Assess_Status, 
Pass_Flag, OrgID) VALUES ('2', 'User1', 'Test1', 'tu@gmail.com', 'default', 'password', '2', '0', '1', '1');


INSERT INTO Org (OrgID) VALUES ('1');


INSERT INTO FACTOR_DEFAULT (FactorDefaultID, Details) VALUES ('1', 'How career development decisions are made');
INSERT INTO FACTOR_DEFAULT (FactorDefaultID, Details) VALUES ('2', 'Does the organization encourage creativity');
INSERT INTO FACTOR_DEFAULT (FactorDefaultID, Details) VALUES ('3', 'How flexible the organization is in the way one works');
INSERT INTO FACTOR_DEFAULT (FactorDefaultID, Details) VALUES ('4', 'Is a balanced work and personal life valued here');
INSERT INTO FACTOR_DEFAULT (FactorDefaultID, Details) VALUES ('5', 'How business decisions are made');
INSERT INTO FACTOR_DEFAULT (FactorDefaultID, Details) VALUES ('6', 'The compensation meets my requirements');
INSERT INTO FACTOR_DEFAULT (FactorDefaultID, Details) VALUES ('7', 'How leaders behave is important to this organization');

INSERT INTO QUESTION_DEFAULT (QuestionDefaultID, FactorDefaultID, Details) VALUES ('1', '1', 'Do you believe decisions on your career development are easily accessible, or made by your immediate manager?');
INSERT INTO QUESTION_DEFAULT (QuestionDefaultID, FactorDefaultID, Details) VALUES ('2', '1', 'Do you feel you have sufficient input on your career decisions?');
INSERT INTO QUESTION_DEFAULT (QuestionDefaultID, FactorDefaultID, Details) VALUES ('3', '2', 'Do you feel creativity is encouraged while not penalizing mistakes in that pursuit?');
INSERT INTO QUESTION_DEFAULT (QuestionDefaultID, FactorDefaultID, Details) VALUES ('4', '3', 'Do you feel you are given the opportunity to set goals independently or collaboratively?');
INSERT INTO QUESTION_DEFAULT (QuestionDefaultID, FactorDefaultID, Details) VALUES ('5', '3', 'Does the organization actively promote collaboration?');
INSERT INTO QUESTION_DEFAULT (QuestionDefaultID, FactorDefaultID, Details) VALUES ('6', '4', 'Do you feel the organization promotes a balanced work and personal life operating style?');
INSERT INTO QUESTION_DEFAULT (QuestionDefaultID, FactorDefaultID, Details) VALUES ('7', '4', 'Does the organization provide sufficient time off for important personal events?');
INSERT INTO QUESTION_DEFAULT (QuestionDefaultID, FactorDefaultID, Details) VALUES ('8', '4', 'Does the organization provide sufficient opportunities for recreation?');
INSERT INTO QUESTION_DEFAULT (QuestionDefaultID, FactorDefaultID, Details) VALUES ('9', '5', 'Do you feel business decisions are made transparently?');
INSERT INTO QUESTION_DEFAULT (QuestionDefaultID, FactorDefaultID, Details) VALUES ('10', '6', 'Do you feel you are provided adequate compensation and benefits for your role?');
INSERT INTO QUESTION_DEFAULT (QuestionDefaultID, FactorDefaultID, Details) VALUES ('11', '6', 'Do you as an individual find the compensation to be competitive to similar organizations?');
INSERT INTO QUESTION_DEFAULT (QuestionDefaultID, FactorDefaultID, Details) VALUES ('12', '6', 'Are you being financially recognized relative to how you believe you are contributing?');
INSERT INTO QUESTION_DEFAULT (QuestionDefaultID, FactorDefaultID, Details) VALUES ('13', '7', 'Do you feel there is clear and preferred leadership style?');
INSERT INTO QUESTION_DEFAULT (QuestionDefaultID, FactorDefaultID, Details) VALUES ('14', '7', 'Do you feel the leadership style is one that promotes accessibility?');