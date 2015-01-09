package ch.groovlet.model.dao.mappers;

import ch.groovlet.model.representation.SongList;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sandro on 20.12.2014.
 */
public class SongListMapper implements ResultSetMapper<SongList> {

    @Override
    public SongList map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new SongList(r.getLong("id"), r.getLong("fk_adminId"), r.getString("name"));
    }
}
