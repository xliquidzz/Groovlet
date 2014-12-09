package ch.groovlet.model.resource;

import ch.groovlet.model.dao.UserDAO;
import ch.groovlet.model.representations.User;
import org.skife.jdbi.v2.DBI;

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

    public UserResource(@Nonnull final DBI dbi) {
        userDAO = dbi.onDemand(UserDAO.class);
    }

    @POST
    public
    @Nonnull
    Response createUser(@Valid @Nonnull final User user) throws URISyntaxException {
        final long newUserId = userDAO.createUser(user.getFirstname(), user.getLastname(), user.getEmail());
        return Response.created(new URI(String.valueOf(newUserId))).build();
    }

    @GET
    @Path("/{id}")
    public
    @Nonnull
    Response readUser(@PathParam("id") @Nonnull final long id) {
        final User user = userDAO.readUserById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }

    @DELETE
    @Path("/{id}")
    public
    @Nonnull
    Response deleteUser(@PathParam("id") @Nonnull final long id) {
        if (userDAO.readUserById(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        userDAO.deleteUserById(id);
        return Response.noContent().build();
    }
}
