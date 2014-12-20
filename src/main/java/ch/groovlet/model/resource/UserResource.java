package ch.groovlet.model.resource;

import ch.groovlet.model.dao.UserDAO;
import ch.groovlet.model.representations.User;
import io.dropwizard.auth.Auth;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.sqlobject.Bind;

import javax.annotation.Nonnull;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserDAO userDAO;

    public UserResource(final DBI dbi) {
        userDAO = dbi.onDemand(UserDAO.class);
    }

    @POST
    public Response createUser(final User user) throws URISyntaxException {
        final long newUserId = userDAO.createUser(user.getNickname(), user.getEmail());
        return Response.created(new URI(String.valueOf(newUserId))).build();
    }

    @GET
    @Path("/{id}")
    public Response readUserById(@PathParam("id") final long id /*, @Auth Boolean isAuthenticated*/) {
        final User user = userDAO.readUserById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") final long id ) {
        if (userDAO.readUserById(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        userDAO.deleteUserById(id);
        return Response.noContent().build();
    }
}
