package ch.groovlet.model.dao;

import ch.groovlet.model.dao.mappers.UserMapper;
import ch.groovlet.model.representation.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(UserMapper.class)
public interface UserDAO {

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO user (id, nickname, email) VALUES (NULL, :nickname, :email)")
    long createUser(@Bind("nickname") final String nickname, @Bind("email") final String email);

    @SqlQuery("SELECT * FROM user WHERE id = :id")
    User readUserById(@Bind("id") final long id);

    @SqlQuery("SELECT * FROM user WHERE nickname = :nickname")
    User readUserByNickName(@Bind("nickname") final String nickname);

    @SqlUpdate("DELETE FROM user WHERE id = :id")
    void deleteUserById(@Bind("id") final long id);

    @SqlQuery("SELECT count(*) FROM user WHERE nickname = :nickname AND password = :password")
    int getUser(@Bind("nickname") String nickname, @Bind("password") String password);


}
