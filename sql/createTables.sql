CREATE TABLE user (
 id bigint(20) NOT NULL AUTO_INCREMENT,
 nickname varchar(100) NOT NULL,
 password varchar(40) NOT NULL,
 email varchar(100) NOT NULL,
 PRIMARY KEY (id),
 UNIQUE KEY nickname (nickname),
 UNIQUE KEY email (email)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE artist (
 id bigint(20) NOT NULL AUTO_INCREMENT,
 name varchar(100) NOT NULL,
 PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE songlist (
 id bigint(20) NOT NULL AUTO_INCREMENT,
 fk_adminId bigint(20) NOT NULL,
 name varchar(100) NOT NULL,
 PRIMARY KEY (id),
 KEY fk_adminId (fk_adminId),
 CONSTRAINT fk_adminId FOREIGN KEY (fk_adminId) REFERENCES user (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE song(
 id bigint(20) NOT NULL AUTO_INCREMENT,
 fk_artistId bigint(20) NOT NULL,
 title varchar(100) NOT NULL,
 genre varchar(100) NOT NULL,
 youtubeString varchar(200) NOT NULL,
 votes bigint(20),
 PRIMARY KEY (id),
 KEY fk_artistId (fk_artistId),
 CONSTRAINT fk_artistId FOREIGN KEY (fk_artistId) REFERENCES artist (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE userToSonglist (
 id bigint(20) NOT NULL AUTO_INCREMENT,
 fk_userId bigint(20) NOT NULL,
 fk_songlistId bigint(20) NOT NULL,
 PRIMARY KEY (id),
 KEY fk_userId (fk_userId),
 KEY fk_songlistId (fk_songlistId),
 CONSTRAINT fk_userId FOREIGN KEY (fk_userId) REFERENCES user (id),
 CONSTRAINT fk_songlistId FOREIGN KEY (fk_songlistId) REFERENCES songlist (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE songToSonglist(
 id bigint(20) NOT NULL AUTO_INCREMENT,
 _fk_songId bigint(20) NOT NULL,
 _fk_songlistId bigint(20) NOT NULL,
 PRIMARY KEY (id),
 KEY _fk_songId (_fk_songId),
 KEY _fk_songlistId (_fk_songlistId),
 CONSTRAINT _fk_songId FOREIGN KEY (_fk_songId) REFERENCES song (id),
 CONSTRAINT _fk_songlistId FOREIGN KEY (_fk_songlistId) REFERENCES songlist (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE userToSong(
 id bigint(20) NOT NULL AUTO_INCREMENT,
 fk_userId_ bigint(20) NOT NULL,
 fk_songId_ bigint(20) NOT NULL,
 PRIMARY KEY (id),
 KEY fk_userId_ (fk_userId_),
 KEY fk_songId_ (fk_songId_),
 CONSTRAINT fk_userId_ FOREIGN KEY (fk_userId_) REFERENCES user (id),
 CONSTRAINT fk_songId_ FOREIGN KEY (fk_songId_) REFERENCES song (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


INSERT INTO user(id,nickname,password, email) VALUES(null,"xliquidzz","12345", "test@test.ch");
INSERT INTO user(id,nickname,password, email) VALUES(null,"test_user","12345", "test@test2.ch");
INSERT INTO user(id,nickname,password, email) VALUES(null,"tu","12345", "test2@test2.ch");

INSERT INTO artist(id,name) VALUES(null,"test artist");
INSERT INTO artist(id,name) VALUES(null,"artist");
INSERT INTO artist(id,name) VALUES(null,"test artist 2");
INSERT INTO artist(id,name) VALUES(null,"test artist 3");

INSERT INTO song(id, fk_artistId, title, genre, youtubeString, votes) VALUES(null, 1, "test song", "test genre", 'bESQmBFyNMg',0);
INSERT INTO song(id, fk_artistId, title, genre, youtubeString, votes) VALUES(null, 3, "test song", "test genri", 'PGeX6oE-dMM',0);
INSERT INTO song(id, fk_artistId, title, genre, youtubeString, votes) VALUES(null, 2, "test song", "test genre2", 'f4W7uZBaIaE',0);
INSERT INTO song(id, fk_artistId, title, genre, youtubeString, votes) VALUES(null, 1, "test song", "genre", 'nRvA08ALkGU',0);
INSERT INTO song(id, fk_artistId, title, genre, youtubeString, votes) VALUES(null, 3, "test song", "test ge", 'frJKUPjqWgg',0);