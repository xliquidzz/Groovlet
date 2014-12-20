package ch.groovlet.model.dao.mappers;

import ch.groovlet.model.representations.Song;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SongMapper implements ResultSetMapper<Song> {

    public Song map(final int index, final ResultSet r, final StatementContext ctx)
            throws SQLException {
        return new Song(r.getLong("id"), r.getLong("artistId"),
                r.getString("title"), r.getString("genre"), r.getInt("votes"),
                r.getString("youtubeString"));
    }
}
