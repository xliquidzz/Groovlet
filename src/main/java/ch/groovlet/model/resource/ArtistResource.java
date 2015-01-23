package ch.groovlet.model.resource;

import ch.groovlet.model.App;
import ch.groovlet.model.representation.Artist;
import ch.groovlet.model.service.ArtistService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/artist")
public class ArtistResource {

    private final ArtistService artistService;

    public ArtistResource() {
        this.artistService = App.getService(ArtistService.class);
    }

    @GET
    public Response getAll() {
        final List<Artist> allArtists = artistService.readAll();
        return Response.ok(allArtists).build();
    }

    @POST
    public Response create(final Artist artist) throws URISyntaxException {
        final long newArtistId = artistService.create(artist.getName());
        return Response.created(new URI(String.valueOf(newArtistId))).build();
    }

    @GET
    @Path("/{id}")
    public Response read(@PathParam("id") final long id) {
        final Artist artist = artistService.readById(id);
        if (artist == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(artist).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") final long id, final Artist artist) {
        if (artistService.readById(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        artistService.updateById(id, artist.getName());
        return Response.ok(new Artist(id, artist.getName())).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") final long id) {
        if (artistService.readById(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        artistService.deleteById(id);
        return Response.noContent().build();
    }
}