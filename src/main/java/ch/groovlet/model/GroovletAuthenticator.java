package ch.groovlet.model;

import ch.groovlet.model.dao.UserDAO;
import ch.groovlet.model.representation.User;
import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import org.skife.jdbi.v2.DBI;

/**
 * Created by sandro on 19.12.2014.
 */
public class GroovletAuthenticator implements Authenticator<BasicCredentials, User> {

    private final UserDAO userDao;

    public GroovletAuthenticator(DBI jdbi) {
        userDao = jdbi.onDemand(UserDAO.class);
    }

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        boolean validUser = (userDao.getUser(credentials.getUsername(), credentials.getPassword()) == 1);
        if (validUser) {
            return Optional.of(userDao.readUserByNickName(credentials.getUsername()));
        }
        return Optional.absent();
    }
}
