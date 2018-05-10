DROP TABLE IF EXISTS admin;

DROP TABLE IF EXISTS awards;

DROP TABLE IF EXISTS clas;

DROP TABLE IF EXISTS college;

DROP TABLE IF EXISTS COMMENT;

DROP TABLE IF EXISTS contest;

DROP TABLE IF EXISTS major;

DROP TABLE IF EXISTS rater;

DROP TABLE IF EXISTS rater_competition;

DROP TABLE IF EXISTS student;

DROP TABLE IF EXISTS student_competition;

DROP TABLE IF EXISTS works;

/*==============================================================*/
/* Table: admin                                                 */
/*==============================================================*/
CREATE TABLE admin
(
   aid                  INT NOT NULL AUTO_INCREMENT,
   account              VARCHAR(20),
   NAME             VARCHAR(20),
   PASSWORD             VARCHAR(50),
   POWER                INT DEFAULT 2 COMMENT '权限 ，1:超管 2:普管',
   PRIMARY KEY (aid)
);

/*==============================================================*/
/* Table: awards                                                */
/*==============================================================*/
CREATE TABLE awards
(
   award_id             BIGINT NOT NULL AUTO_INCREMENT,
   contest_id           BIGINT,
   team_id              VARCHAR(100),
   work_id              BIGINT,
   prize                INT,
   score                FLOAT,
   TIMESTAMP            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (award_id)
);

/*==============================================================*/
/* Table: clas                                                  */
/*==============================================================*/
CREATE TABLE clas
(
   cla_id               INT NOT NULL AUTO_INCREMENT,
   cla_name             VARCHAR(20),
   major_id             INT,
   PRIMARY KEY (cla_id)
);

/*==============================================================*/
/* Table: college                                               */
/*==============================================================*/
CREATE TABLE college
(
   col_id               INT NOT NULL AUTO_INCREMENT,
   col_name             VARCHAR(30),
   PRIMARY KEY (col_id)
);

/*==============================================================*/
/* Table: comment                                               */
/*==============================================================*/
CREATE TABLE COMMENT
(
   com_id               BIGINT NOT NULL AUTO_INCREMENT,
   work_id              BIGINT,
   rater_id             INT,
   content              TEXT,
   score                FLOAT,
   contest_id           BIGINT,
   team_id              VARCHAR(100),
   TIMESTAMP            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   STATUS               INT DEFAULT 0 COMMENT '审批状态 0：未审批，1：已审批',
   PRIMARY KEY (com_id)
);

/*==============================================================*/
/* Table: contest                                               */
/*==============================================================*/
CREATE TABLE contest
(
   cid                  BIGINT NOT NULL AUTO_INCREMENT,
   title                VARCHAR(200),
   summary              VARCHAR(4096),
   end_date             DATE,
   start_date           DATE,
   enroll_start_date    DATE,
   enroll_end_date      DATE,
   works_end_date       DATE,
   TIMESTAMP            DATETIME  ,
   score_start_date     DATE,
   score_end_date       DATE,
   STATUS               INT,
   extend_json          VARCHAR(1000),
   score_status         INTEGER DEFAULT 0,
   PRIMARY KEY (cid)
);

/*==============================================================*/
/* Table: major                                                 */
/*==============================================================*/
CREATE TABLE major
(
   maj_id               INT NOT NULL AUTO_INCREMENT,
   maj_name             VARCHAR(30),
   college_id           INT,
   PRIMARY KEY (maj_id)
);

/*==============================================================*/
/* Table: rater                                                 */
/*==============================================================*/
CREATE TABLE rater
(
   rid                  INT NOT NULL AUTO_INCREMENT,
   rat_name             VARCHAR(20),
   rat_account          VARCHAR(20),
   rat_password         VARCHAR(50),
   PRIMARY KEY (rid)
);

/*==============================================================*/
/* Table: rater_competition                                     */
/*==============================================================*/
CREATE TABLE rater_competition
(
   rc_id                BIGINT NOT NULL AUTO_INCREMENT,
   rater_id             INT,
   contest_id           BIGINT,
   PRIMARY KEY (rc_id)
);

/*==============================================================*/
/* Table: student                                               */
/*==============================================================*/
CREATE TABLE student
(
   sid                  BIGINT NOT NULL AUTO_INCREMENT,
   stu_id               VARCHAR(20) NOT NULL,
   stu_name             VARCHAR(20),
   stu_gender           INT,
   stu_col_id           INT,
   stu_maj_id           INT,
   stu_cla_id           INT,
   stu_password         VARCHAR(50),
   PRIMARY KEY (sid, stu_id)
);

/*==============================================================*/
/* Table: student_competition                                   */
/*==============================================================*/
CREATE TABLE student_competition
(
   sc_id                BIGINT NOT NULL AUTO_INCREMENT,
   student_id           VARCHAR(20),
   student_n            INT,
   contest_id           BIGINT,
   team_id              VARCHAR(100) BINARY,
   TIMESTAMP            DATETIME,
   workCommit           INT,
   PRIMARY KEY (sc_id)
);

/*==============================================================*/
/* Table: works                                                 */
/*==============================================================*/
CREATE TABLE works
(
   w_id                 BIGINT NOT NULL AUTO_INCREMENT,
   team_id              VARCHAR(100),
   contest_id           BIGINT,
   CODE                 TEXT,
   image                VARCHAR(1024),
   thesis               VARCHAR(1000),
   score                FLOAT,
   TIMESTAMP            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   COMMIT               INT,
   PRIMARY KEY (w_id)
);
