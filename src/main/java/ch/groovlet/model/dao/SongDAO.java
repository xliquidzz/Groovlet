package ch.groovlet.model.dao;

import ch.groovlet.model.dao.mappers.SongMapper;
import ch.groovlet.model.representations.Song;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(SongMapper.class)
public interface SongDAO {

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO song (id, artistId, title, genre, votes, youtubeString) VALUES (NULL, :artistId, :title, :genre, :votes, :youtubeString)")
    long createSong(@Bind("id") long id, @Bind("artistId") long artistId,
                    @Bind("title") String title, @Bind("genre") String genre,
                    @Bind("votes") int votes,
                    @Bind("youtubeString") String youtubeString);

    @SqlQuery("SELECT * FROM song WHERE id = :id")
    Song readSongById(@Bind("id") final long id);

    @SqlUpdate("DELETE FROM song WHERE id = :id")
    void deleteSongById(@Bind("id") final long id);

    @SqlUpdate("DELETE FROM song")
    void deleteAll();
}
