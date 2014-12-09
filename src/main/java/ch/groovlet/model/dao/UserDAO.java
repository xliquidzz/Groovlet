package ch.groovlet.model.dao;

import ch.groovlet.model.dao.mappers.UserMapper;
import ch.groovlet.model.representations.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface UserDAO {

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO user (id, firstname, lastname, email) VALUES (NULL, :firstname, :lastname, :email)")
    @Nullable
    long createUser(@Bind("firstname") @Nonnull final String firstname,
                    @Bind("lastname") @Nonnull final String lastname,
                    @Bind("email") @Nonnull final String email);

    @Mapper(UserMapper.class)
    @SqlQuery("SELECT * FROM user WHERE id = :id")
    @Nullable
    User readUserById(@Bind("id") @Nonnull final long id);

    @SqlUpdate("DELETE FROM user WHERE id = :id")
    void deleteUserById(@Bind("id") @Nonnull final long id);
}
