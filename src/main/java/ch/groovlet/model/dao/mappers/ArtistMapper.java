package ch.groovlet.model.dao.mappers;

import ch.groovlet.model.representations.Artist;
import ch.groovlet.model.representations.Song;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sandro on 20.12.2014.
 */
public class ArtistMapper implements ResultSetMapper<Artist> {

    @Override
    public Artist map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Artist(r.getLong("id"), r.getString("name"));
    }
}
