create database chat_servlet default character set='utf8';

use chat_servlet

CREATE TABLE users (
  id bigint NOT NULL PRIMARY KEY auto_increment,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  fullname VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  thumbnail VARCHAR(255) NOT NULL,
  status BOOLEAN NOT NULL,
  otp VARCHAR(255) NOT NULL,
  createddate TIMESTAMP NULL,
  createdby VARCHAR(255) NULL,
  modifieddate TIMESTAMP NULL,
  modifiedby VARCHAR(255) NULL
);

CREATE TABLE messengers (
  id bigint NOT NULL PRIMARY KEY auto_increment,
  content TEXT NOT NULL,
  userid bigint NOT NULL,
  typereceiver int NOT NULL,
  isfile BOOLEAN NOT NULL,
  remove BOOLEAN NOT NULL,
  createddate TIMESTAMP NULL,
  createdby VARCHAR(255) NULL,
  modifieddate TIMESTAMP NULL,
  modifiedby VARCHAR(255) NULL
);

CREATE TABLE receivers (
  id bigint NOT NULL PRIMARY KEY auto_increment,
  userid bigint NOT NULL,
  messengerid bigint NOT NULL,
  createddate TIMESTAMP NULL,
  createdby VARCHAR(255) NULL
);

CREATE TABLE files (
  id bigint NOT NULL PRIMARY KEY auto_increment,
  filename VARCHAR(255) NOT NULL,
  path TEXT NOT NULL,
  messengerid bigint NOT NULL,
  createddate TIMESTAMP NULL,
  createdby VARCHAR(255) NULL
);

CREATE TABLE groups (
  id bigint NOT NULL PRIMARY KEY auto_increment,
  namegroup VARCHAR(255) NOT NULL,
  adminid bigint NOT NULL,
  messengerid bigint NOT NULL,
  createddate TIMESTAMP NULL,
  createdby VARCHAR(255) NULL,
  modifieddate TIMESTAMP NULL,
  modifiedby VARCHAR(255) NULL
);

CREATE TABLE members (
  id bigint NOT NULL PRIMARY KEY auto_increment,
  userid bigint NOT NULL,
  groupid bigint NOT NULL,
  createddate TIMESTAMP NULL,
  createdby VARCHAR(255) NULL
);

CREATE TABLE friends (
  id bigint NOT NULL PRIMARY KEY auto_increment,
  user_id bigint NOT NULL,
  friend_id bigint NOT NULL,
  friend_name VARCHAR(255) NOT NULL,
  friend_avt VARCHAR(255) NOT NULL,
);

/* mối quan hệ */

ALTER TABLE messengers ADD CONSTRAINT fk_messengers_users FOREIGN KEY (userid) REFERENCES users(id);
ALTER TABLE receivers ADD CONSTRAINT fk_receivers_users FOREIGN KEY (userid) REFERENCES users(id);
ALTER TABLE members ADD CONSTRAINT fk_members_users FOREIGN KEY (userid) REFERENCES users(id);
ALTER TABLE requests ADD CONSTRAINT fk_requests_users FOREIGN KEY (userid) REFERENCES users(id);
ALTER TABLE groups ADD CONSTRAINT fk_groups_users FOREIGN KEY (adminid) REFERENCES users(id);

ALTER TABLE friends ADD CONSTRAINT fk_friends_requests FOREIGN KEY (requestid) REFERENCES requests(id);

ALTER TABLE members ADD CONSTRAINT fk_members_groups FOREIGN KEY (groupid) REFERENCES groups(id);

ALTER TABLE groups ADD CONSTRAINT fk_groups_messengers FOREIGN KEY (messengerid) REFERENCES messengers(id);
ALTER TABLE receivers ADD CONSTRAINT fk_receivers_messengers FOREIGN KEY (messengerid) REFERENCES messengers(id);

ALTER TABLE files ADD CONSTRAINT fk_files_messengers FOREIGN KEY (messengerid) REFERENCES messengers(id);

ALTER TABLE chat_servlet.friends ADD CONSTRAINT fk_friend_users FOREIGN KEY (user_id) REFERENCES users(id);











