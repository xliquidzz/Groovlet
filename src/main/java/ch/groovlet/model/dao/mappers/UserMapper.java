package ch.groovlet.model.dao.mappers;

import ch.groovlet.model.representation.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ResultSetMapper<User> {

    public User map(final int index, final ResultSet r, final StatementContext ctx)
            throws SQLException {
        return new User(r.getLong("id"), r.getString("nickname"), r.getString("email"));
    }
}
