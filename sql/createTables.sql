CREATE TABLE user (
 id bigint(20) NOT NULL AUTO_INCREMENT,
 username varchar(100) NOT NULL UNIQUE,
 password varchar(40) NOT NULL,
 email varchar(100) NOT NULL UNIQUE,
 PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE artist (
 id bigint(20) NOT NULL AUTO_INCREMENT,
 name varchar(100) NOT NULL,
 PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE genre (
 id bigint(20) NOT NULL AUTO_INCREMENT,
 name varchar(100) NOT NULL UNIQUE,
 PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE songlist (
 id bigint(20) NOT NULL AUTO_INCREMENT,
 name varchar(100) NOT NULL,
 PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE song(
 id bigint(20) NOT NULL AUTO_INCREMENT,
 artistId bigint(20) NOT NULL,
 title varchar(100) NOT NULL,
 genre varchar(100) NOT NULL,
 votes bigint(20),
 youtubeString varchar(200) NOT NULL,
 PRIMARY KEY (id),
 CONSTRAINT fk_artistId FOREIGN KEY (artistId) REFERENCES artist (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE favorites (
 id bigint(20) NOT NULL AUTO_INCREMENT,
 userId bigint(20) NOT NULL,
 PRIMARY KEY (id),
 CONSTRAINT fk_userId FOREIGN KEY (userId) REFERENCES user (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE userToSonglist (
 userId bigint(20) NOT NULL,
 songListId bigint(20) NOT NULL,
 CONSTRAINT fk_userId FOREIGN KEY (userId) REFERENCES user (id),
 CONSTRAINT fk_songListId FOREIGN KEY (songListId) REFERENCES songList (id),
 PRIMARY KEY (fk_userId, fk_songListId)
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


INSERT INTO user(id,username, password, email) VALUES(null,"xliquidzz","12345", "s.klaentschi@gmail.com");
INSERT INTO user(id,username, password, email) VALUES(null,"testUser","12345", "test@test.ch");


INSERT INTO artist(id,name) VALUES(null,"test artist");
INSERT INTO artist(id,name) VALUES(null,"artist");
INSERT INTO artist(id,name) VALUES(null,"test artist 2");
INSERT INTO artist(id,name) VALUES(null,"test artist 3");

INSERT INTO song(id, fk_artistId, title, genre, youtubeString, votes) VALUES(null, 16, "test song", "test genre", 'bESQmBFyNMg',0);
INSERT INTO song(id, fk_artistId, title, genre, youtubeString, votes) VALUES(null, 17, "test song", "test genri", 'PGeX6oE-dMM',0);
INSERT INTO song(id, fk_artistId, title, genre, youtubeString, votes) VALUES(null, 18, "test song", "test genre2", 'f4W7uZBaIaE',0);
INSERT INTO song(id, fk_artistId, title, genre, youtubeString, votes) VALUES(null, 19, "test song", "genre", 'nRvA08ALkGU',0);
INSERT INTO song(id, fk_artistId, title, genre, youtubeString, votes) VALUES(null, 20, "test song", "test ge", 'frJKUPjqWgg',0);

INSERT INTO songlist(id, fk_adminId, name) VALUES(null,1,"xliquidzz SongList");
INSERT INTO songlist(id, fk_adminId, name) VALUES(null,3,"test_user SongList");
INSERT INTO songlist(id, fk_adminId, name) VALUES(null,4,"SongList");
